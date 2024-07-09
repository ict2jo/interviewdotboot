import subprocess
from fastapi import FastAPI, HTTPException, UploadFile, File, Form
from fastapi.responses import FileResponse
import shutil
import os
import uuid
import torch
import json
from transformers import BertModel
from kobert_tokenizer import KoBERTTokenizer
import gluonnlp as nlp
from torch.utils.data import DataLoader
from BERTModel import BERTClassifier, BERTDataset, predict, load_model
from pydantic import BaseModel
from fastapi.middleware.cors import CORSMiddleware

# KoBERT 모델과 토크나이저 불러오기
tokenizer = KoBERTTokenizer.from_pretrained('skt/kobert-base-v1')
bertmodel = BertModel.from_pretrained('skt/kobert-base-v1', return_dict=False)
vocab = nlp.vocab.BERTVocab.from_sentencepiece(tokenizer.vocab_file, padding_token='[PAD]')

# GPU 사용 시
device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")

# 학습된 모델 불러오기
model_path = './train_model.pt'
model = load_model(model_path, device)

# FastAPI 애플리케이션 초기화
app = FastAPI()

# 허용할 origin 목록 설정
origins = [
    "*",
]

# CORS 미들웨어 추가
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["GET", "POST"],
    allow_headers=["*"],
)

class STTResult(BaseModel):
    text: str
    pose_results: str
    sentiment: str
    q_idx: int 
    question: str 
    uuid_filename: str

class VideoShow(BaseModel):
    uuid_video : str

# web 파일을 WAV 파일로 변환 함수
def convert_webm_to_wav(webm_path):
    output_audio = os.path.join("/home/stt_Espnet/egs2/kinterview1/asr1/wav", os.path.splitext(os.path.basename(webm_path))[0] + ".wav")
    ffmpeg_path = find_ffmpeg()
    if not ffmpeg_path:
        raise Exception("ffmpeg 실행 파일을 찾을 수 없습니다.")

    command = [
        ffmpeg_path,
        '-i', webm_path,
        '-vn',
        '-c:a', 'pcm_s16le',
        '-ar', '16000',
        output_audio
    ]

    try:
        subprocess.run(command, check=True)
        print(f"오디오 추출 완료: {output_audio}")
        return output_audio
    except subprocess.CalledProcessError as e:
        print(f"오류 발생: {e}")
        raise Exception(f"ffmpeg 명령어 실행 중 오류 발생: {e}")

def find_ffmpeg():
    ffmpeg_path = '/usr/bin/ffmpeg'
    if not os.path.exists(ffmpeg_path):
        return None
    return ffmpeg_path

def extract_stt_result(output):
    lines = output.split('\n')
    for line in lines:
        if line.startswith('/home/stt_Espnet/'):
            return line.strip()
    return ""

def video_pose_detection(video_path):
    result = subprocess.run(
        ['python3', 'webcam.py', '--video_path', video_path],
        capture_output=True,
        text=True
    )

    stdout = result.stdout.strip()
    if not stdout:
        raise ValueError("No data returned from webcam.py")

    pose_data = json.loads(stdout)
    shoulder_movement_count = pose_data.get('shoulder_movement_count', 0)
    
    if shoulder_movement_count < 50:
        pose_evaluation = "자세가 좋습니다"
    elif 50 <= shoulder_movement_count <= 100:
        pose_evaluation = "자세가 보통입니다"
    else:
        pose_evaluation = "자세가 안좋습니다"

    return pose_evaluation

def perform_stt(wav_path, asr_config, asr_model, lm_config, lm_model):
    stt_result = subprocess.run(
        [
            'python3', 'inference_wav.py',
            '--wav_path', wav_path,
            '--asr_config', asr_config,
            '--asr_model', asr_model,
            '--lm_config', lm_config,
            '--lm_model', lm_model
        ],
        capture_output=True, text=True
    )

    if stt_result.returncode != 0:
        raise Exception(f"inference_wav.py failed: {stt_result.stderr}")

    return stt_result.stdout

@app.post("/video/", response_model=STTResult)
async def video_upload(file: UploadFile = File(...), q_idx: int = Form(...), question: str = Form(...)):
    try:
        print(f"파일명: {file.filename}")

        # 고유 파일명 생성
        file_extension = os.path.splitext(file.filename)[1]
        uuid_filename = f"{uuid.uuid4()}{file_extension}"
        upload_path = os.path.join("video", uuid_filename)
        print(f"저장 경로: {upload_path}")

        # 비동기 파일 저장
        with open(upload_path, "wb") as buffer:
            shutil.copyfileobj(file.file, buffer)

        print(f"파일 저장 완료: {upload_path}")

        # 이후 처리를 위한 함수 호출
        wav_path = convert_webm_to_wav(upload_path)
        print(f"WAV 파일 경로: {wav_path}")

        pose_evaluation = video_pose_detection(upload_path)
        print(f"포즈 검출 결과: {pose_evaluation}")

        stt_text = perform_stt(
            wav_path,
            '/home/stt_Espnet/egs2/kinterview1/asr1/exp/asr_train_asr_conformer_raw_kr_bpe1772_max_epoch150/config.yaml',
            '/home/stt_Espnet/egs2/kinterview1/asr1/exp/asr_train_asr_conformer_raw_kr_bpe1772_max_epoch150/valid.acc.ave_10best.pth',
            '/home/stt_Espnet/egs2/kinterview1/asr1/exp/lm_train_lm_transformer_kr_bpe1772_max_epoch30/config.yaml',
            '/home/stt_Espnet/egs2/kinterview1/asr1/exp/lm_train_lm_transformer_kr_bpe1772_max_epoch30/valid.loss.ave_10best.pth'
        )

        stt_result_text = stt_text.split(".wav\n", 1)[1].strip()
        print(f"STT분석 결과 : {stt_result_text}")
        sentiment_prediction = predict(stt_result_text, model, tokenizer, vocab, device)
        sentiment = ["부정", "중립", "긍정"][sentiment_prediction]
        print(f"감정 분석 결과: {sentiment}")
        print(f"질문 : {question}")
        print(f"q_idx : {q_idx}")

        return STTResult(text=stt_result_text, pose_results=pose_evaluation, sentiment=sentiment, q_idx=q_idx, question=question, uuid_filename=uuid_filename)

    except Exception as e:
        print(f"오류 발생: {e}")
        raise HTTPException(status_code=500, detail=str(e))

@app.get("/video_get/{uuid_name}", response_class=FileResponse)
async def get_video(uuid_name: str):
    video_path = os.path.join("video", uuid_name)
    if not os.path.exists(video_path):
        raise HTTPException(status_code=404, detail="Video not found")
    return video_path

if __name__ == "__main__":
    import uvicorn
    uvicorn.run("app:app", host="0.0.0.0", port=8010)
