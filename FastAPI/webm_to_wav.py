import sys
import subprocess
import os

def extract_audio_from_webm(input_file, output_file, sample_rate=16000):
    # ffmpeg 명령어 구성
    command = [
        '/usr/bin/ffmpeg',  # 시스템에 설치된 ffmpeg 실행 파일의 전체 경로
        '-i', input_file,  # 입력 파일
        '-vn',   # 비디오 스트림 무시
        '-c:a', 'pcm_s16le',  # WAV 코덱 사용 (PCM 16-bit little-endian)
        '-ar', str(sample_rate),  # 샘플링 속도 설정
        output_file  # 출력 파일
    ]

    try:
        # ffmpeg 명령어 실행
        subprocess.run(command, check=True)
        print(f"오디오 추출 완료: {output_file}")
    except subprocess.CalledProcessError as e:
        print(f"오류 발생: {e}")
    except FileNotFoundError as e:
        print(f"ffmpeg 실행 파일을 찾을 수 없습니다: {e}")

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("입력 파일명을 제공해야 합니다.")
        sys.exit(1)
    
    input_filename = sys.argv[1]
    input_webm = os.path.join("video", input_filename)
    output_audio = '/home/stt_Espnet/egs2/kinterview1/asr1/wav/'+input_filename+'.wav'
    
    extract_audio_from_webm(input_webm, output_audio, sample_rate=16000)
