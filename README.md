스프링 부트를 활용한 일정 관리 프로젝트입니다.  

기능
1. 일정 생성,조회,수정,삭제
2. 댓글 저장,조회,수정,삭제
3. 일정 목록 조회시 일정별 댓글 개수 조회, 일정 상세 조회 시, 해당 일정에 작성된 모든 댓글 목록 함께 조회(작성일 기준 오름차순)
4. 댓글에 답글 작성가능(최대 1개), 답글 조회시 댓글 하위에 함꼐조회

기술 스택
1. Java 17
2. spring boot 3.4.5
3. MySQL

API 명세서
![image](https://github.com/user-attachments/assets/0dc8c320-6f90-4d6a-a180-0a60f2e5680f)
ERD
![image](https://github.com/user-attachments/assets/49cf137d-4d41-4181-82d1-9b9018c03ae8)

API동작

일정생성
![일정생성](https://github.com/user-attachments/assets/39ebb6bc-5b44-483c-99cc-4db9a5ee4f45)
일정목록조회
![일정목록조회](https://github.com/user-attachments/assets/6a11ca09-a5e2-49b8-bf5a-e192676e7852)
일정상세조회
![단일일정상세조회](https://github.com/user-attachments/assets/e88d24e1-0d7c-4aba-b30e-c5a4977ef82a)
존재하지 않는 일정 조회
![존재하지 않는 일정 조회](https://github.com/user-attachments/assets/dd0ab3c7-3dd9-4d11-bd04-f89dd21cdd14)
일정삭제
![일정삭제](https://github.com/user-attachments/assets/9370f56e-bcf3-472a-96ee-d7f621561e51)
작성자ID일치하지않음
![작성자ID일치하지않음](https://github.com/user-attachments/assets/7f96fd09-07a5-4a77-a1d0-21bbbf8441aa)
작성자ID입력하지않음
![작성자 ID 미입력시](https://github.com/user-attachments/assets/b1e0d132-b413-49a1-9867-0b772a58cf61)
댓글등록
![댓글등록](https://github.com/user-attachments/assets/dae6648d-a94b-469e-87cb-0c6621473353)
댓글조회
![댓글조회](https://github.com/user-attachments/assets/eecda1db-8b40-43fd-983e-5583832ecf12)
댓글수정
![댓글수정](https://github.com/user-attachments/assets/0bbbc2f6-36c1-4ee3-9fe2-3547b0d0c05c)
댓글삭제
![댓글삭제](https://github.com/user-attachments/assets/86fab1e0-74ae-49d2-a8ca-35db57c71676)
대댓글등록
![답글등록](https://github.com/user-attachments/assets/68e6cafe-0139-4c6c-8439-e00223378eb8)
존재하지않는댓글삭제
![존재하지않는댓글](https://github.com/user-attachments/assets/c0b8a2d2-7ce6-40da-bac5-8be565631b25)




