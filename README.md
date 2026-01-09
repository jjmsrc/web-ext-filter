# 플로우 개발 과제

---
## SaaS 부문 개발자 채용과제 - 파일 확장자 차단


### 설명
파일 확장자에 따라 특정 형식의 파일을 첨부하거나 전송하지 못하도록 제한하는 설정을 관리할 수 있는 페이지 개발

### 화면
<img width="646" height="522" alt="image" src="https://github.com/user-attachments/assets/28940d66-b56e-4008-bf6a-28e3acea98d1" />

### API

- 모든 확장자 조회
  ``` 
    - URL: /exts
    - METHOD: GET
    - RESPONSE BODY: [ 
        { id: 식별 아이디, name: 확장자 명, fixed: 고정 확장자 여부, checked: 체크 여부 }, 
        ...
      ]
  ```

- 고정 확장자 체크
  ```
    - URL: /exts/{id}
    - METHOD: Patch
    - RESPONSE BODY: 
        { id: 식별 아이디, name: 확장자 명, fixed: 고정 확장자 여부, checked: 체크 여부 }
  ```
  
- 커스텀 확장자 등록
  ```
    - URL: /exts
    - METHOD: POST
    - REQUEST BODY: { name: 확장자 명 }
    - RESPONSE BODY: 
        { id: 식별 아이디, name: 확장자 명, fixed: 고정 확장자 여부, checked: 체크 여부 }
  ```
  
- 커스텀 확장자 삭제(체크 해제)
  ``` 
    - URL: /exts/{id}
    - METHOD: DELETE
  ```

### TODO

- [x] 설정 화면 구현
- [x] 확장자 등록, 삭제 API 구현
- [x] API 연동
- [x] 기능 테스트 및 배포

### 고려한 점

- 중복해서 같은 확장자를 등록하는 경우

  - 중복된 확장자 등록 알림을 띄우도록 예외 처리

- 짧은 시간에 연속적으로 체크를 계속 해제하거나 체크한 경우

  - 1초 이내에 5번 이상 클릭할 경우 알림을 띄우도록 처리

- 고정 확장자를 추가하거나 없애는 경우

  - DB에서 고정 확장자 목록을 가져오도록 처리
