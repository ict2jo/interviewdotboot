import cv2
import mediapipe as mp
import json
import argparse

def video_pose_detection(video_path):
    # MediaPipe Pose 초기화
    mp_pose = mp.solutions.pose
    pose = mp_pose.Pose(static_image_mode=False, model_complexity=0, min_detection_confidence=0.5, min_tracking_confidence=0.5)

    # 비디오 캡처 초기화
    cap = cv2.VideoCapture(video_path)

    if not cap.isOpened():
        raise ValueError(f"비디오 파일을 열 수 없습니다: {video_path}")

    # 어깨 움직임 임계값 정의
    movement_threshold = 0.01  # 필요에 따라 이 값을 조정하세요

    # 어깨 움직임과 카운트를 추적할 변수 초기화
    prev_shoulder_y = None
    shoulder_movement_count = 0

    # 프레임 크기를 줄여 처리 속도를 높임
    resize_factor = 0.1  # 필요에 따라 조정하세요

    while cap.isOpened():
        ret, frame = cap.read()
        if not ret:
            break

        # 프레임 크기 줄이기
        frame = cv2.resize(frame, (0, 0), fx=resize_factor, fy=resize_factor)

        # 프레임을 RGB로 변환
        rgb_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

        # 포즈 랜드마크를 찾기 위해 프레임 처리
        results = pose.process(rgb_frame)

        if results.pose_landmarks:
            # 왼쪽과 오른쪽 어깨의 y좌표 가져오기
            left_shoulder_y = results.pose_landmarks.landmark[mp_pose.PoseLandmark.LEFT_SHOULDER].y
            right_shoulder_y = results.pose_landmarks.landmark[mp_pose.PoseLandmark.RIGHT_SHOULDER].y

            # 어깨의 평균 y좌표 계산
            avg_shoulder_y = (left_shoulder_y + right_shoulder_y) / 2

            # 첫 번째 프레임인 경우 이전 어깨 y좌표 초기화
            if prev_shoulder_y is None:
                prev_shoulder_y = avg_shoulder_y

            # 움직임 계산
            movement = abs(avg_shoulder_y - prev_shoulder_y)

            # 움직임이 임계값을 초과하는지 확인
            if movement > movement_threshold:
                shoulder_movement_count += 1

            # 이전 어깨 y좌표 업데이트
            prev_shoulder_y = avg_shoulder_y

    # 리소스 해제
    cap.release()
    pose.close()

    # JSON 형식으로 반환
    return json.dumps({'shoulder_movement_count': shoulder_movement_count})

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Process video for shoulder movement detection.')
    parser.add_argument('--video_path', type=str, help='Path to the video file')
    args = parser.parse_args()

    if args.video_path:
        try:
            result = video_pose_detection(args.video_path)
            print(result)
        except ValueError as e:
            print(f"오류 발생: {e}")
    else:
        print("오류발생했습니당")
