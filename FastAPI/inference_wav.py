import os
import io
import re
import vad
import librosa
import argparse
import numpy as np
import soundfile as sf
from pydub import AudioSegment
from espnet2.bin.asr_inference import Speech2Text
    
def get_plain_text(results):
    text = str()
    for result in results:
        text += f" {result['text'].strip()}"
        text = text.strip()
        
    return text.strip()

def update_max_duration(pre, post):
    if pre > post:
        return pre
    else:
        return post

def non_kor_filter(text):
    kor = re.compile("[^ |가-힣]+")
    result = kor.sub('', text)
    return result

def split_segment(wav_path):
    interval = 0.0
    print(wav_path)
    
    while True:
        max_duration = 0.0
        vad_results = vad.split_chunk(wav_path,
                                      vad_degree=2,
                                      segment_len=15.0, # 15초 내외
                                      th_voice=0.9,
                                      th_silence=0.9-interval)
        segments = vad_results['segments']
        
        for segment in segments:
            duration = len(segment) / 16000.0 / 2.0
            max_duration = update_max_duration(duration, max_duration)

        if max_duration < 16.0:
            break
        else:
            interval += 0.05
    
    return vad_results

def get_chunklist(vad_results):
    outputs = list()
    seg_idx = 0
    segments = vad_results['segments']
    timestamps = vad_results['timestamps']

    for i in range(0, len(timestamps), 2):
        start, end = timestamps[i:i+2]
        token = start[0]
        duration = round((end[1] - start[1]), 2)

        if token == 'Silence':
            outputs.append(duration)
            continue

        if token == 'Voice':
            voice_segment = segments[seg_idx]
            outputs.append([voice_segment, duration])
            seg_idx += 1
            
    return outputs

def chunks_to_model(model, chunks):
    results = list()
    for input_data in chunks:
        chunk_result = dict()
        if type(input_data) is float:
            chunk_result['raw'] = ' '
            chunk_result['text'] = chunk_result['raw']
            chunk_result['second'] = input_data
            chunk_result['char'] = list()

            timeinfo = dict()
            timeinfo['value'] = chunk_result['text']
            timeinfo['start'] = 0.00
            timeinfo['end'] = chunk_result['second']

            chunk_result['char'].append(timeinfo)
        else:
            audio, duration = input_data

            audiosegment = AudioSegment.from_raw(io.BytesIO(audio),
                                                 sample_width=2,
                                                 frame_rate=16000,
                                                 channels=1)
            samples = audiosegment.get_array_of_samples()
            signal_raw = np.array(samples).astype(np.float32)
            signal_data = librosa.util.normalize(signal_raw)

            # STT Inference
            nbests = model(signal_data)
            out_text, *_ = nbests[0]

            chunk_result['raw'] = out_text
            chunk_result['text'] = non_kor_filter(out_text).strip()
            chunk_result['second'] = duration

        results.append(chunk_result)

    return results

def inference(model, wavpath):
    vad_results = split_segment(wavpath)
    chunks = get_chunklist(vad_results)
    results = chunks_to_model(model, chunks)
    predict = get_plain_text(results)

    return predict
    
def main():
    parser = argparse.ArgumentParser(description='inference single wav format file')
    parser.add_argument("--wav_path", dest="wav_path", action="store")
    parser.add_argument("--asr_config", dest="asr_config", action="store")
    parser.add_argument("--asr_model", dest="asr_model", action="store")
    parser.add_argument("--lm_config", dest="lm_config", action="store")
    parser.add_argument("--lm_model", dest="lm_model", action="store")
    
    args = parser.parse_args()
    
    print(args.asr_config)
    print(args.asr_model)
    print(args.lm_config)
    print(args.lm_model)
    
    speech2text = Speech2Text(
        asr_train_config=args.asr_config,
        asr_model_file=args.asr_model,
        lm_train_config=args.lm_config,
        lm_file=args.lm_model,
        device="cpu",
        maxlenratio=0.8,
        minlenratio=0.4,
        beam_size=10,
        ctc_weight=0.4,
        lm_weight=0.6,
        penalty=0.0,
        nbest=1
    )

    result = inference(speech2text, args.wav_path)
    print(result)  # STT 결과 출력

if __name__ == "__main__":
    main()
