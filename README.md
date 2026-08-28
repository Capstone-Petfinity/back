# 🐾 Petfinity - Backend API Server

> **반려동물 맞춤형 질환 진단 및 케어 플랫폼**  
> *Pet + Infinity: 반려동물과의 건강한 삶과 무한한 행복을 위한 AI 기반 헬스케어 서비스*

---

## 🔗 Project Links
- **팀 레포지토리(Organization):** [Capstone-Petfinity 바로가기](https://github.com/Capstone-Petfinity)
- **프론트엔드 레포지토리:** [Petfinity-FE](https://github.com/Capstone-Petfinity/Petfinity-FE)
- **AI 서빙 레포지토리:** [AI-API](https://github.com/Capstone-Petfinity/AI-API)

---

## 🛠 Tech Stack
- **Language & Framework:** Java 17, Spring Boot, Spring Data JPA
- **Database:** MySQL
- **Security & Network:** AES Encryption, REST API (Flask AI Server Interworking)
- **Build & Tools:** Gradle, Postman, Git

---

## 🌟 주요 기능 및 아키텍처 개요

[ Frontend (App/Web) ]
│
▼ (HTTPS / REST API)
[ Spring Boot Backend Server ] ──── (REST API) ────▶ [ AI Serving Server (Flask) ]
│                                                      │
▼                                                      ▼
[ MySQL Database ]                                    [ Disease Diagnosis Model ]


### 1. 사용자 맞춤형 듀얼 인증 및 권한 체계
- **보호자(Parent) / 수의사(Vet)** 유형별 회원가입 및 비즈니스 로직 분리
- **AES 암호화**를 적용한 민감 데이터(비밀번호, 개인정보) 전송 및 보안 강화
- UUID 기반의 세션 및 유저 식별자 관리

### 2. 다중 반려동물 프로필 & 진단 이력 아카이빙
- 다중 반려동물 등록(이름, 생년월일, 품종, 성별 등) 및 개별 건강 이력 매핑
- AI 진단 결과(진단 질환명, 의심 확률, 증상 내용) 히스토리 누적 관리

### 3. AI 연동 질환 진단 파이프라인
- 클라이언트로부터 업로드된 이미지 및 진단 부위 정보를 수신
- **Spring Boot $\rightarrow$ Flask AI Server** 간 REST 통신을 통해 AI 모델 추론 요청
- 반환된 진단 결과(질환명, 확률) 파싱 후 DB 저장 및 클라이언트 응답

### 4. 병원 조회 및 진료 예약 시스템
- 지역(시/군/구) 기반 제휴 동물병원 정보 조회
- 보호자-반려동물-병원을 연결하는 진료 예약 API 구축

---

## 📡 REST API Specifications

| 도메인 | 메서드 | 엔드포인트 | 설명 |
| :--- | :---: | :--- | :--- |
| **Auth** | `POST` | `/user/signup/parent` | 보호자 회원가입 (AES 암호화) |
| | `POST` | `/user/signup/vet` | 수의사 회원가입 (AES 암호화) |
| | `POST` | `/user/signup/parent/idduplicate` | 보호자 아이디 중복 확인 |
| | `POST` | `/user/signup/vet/idduplicate` | 수의사 아이디 중복 확인 |
| | `POST` | `/user/login` | 로그인 및 세션 검증 |
| | `POST` | `/user/logout` | 로그아웃 및 세션 만료 |
| **User & Info** | `POST` | `/user/info/parent` | 보호자 프로필 조회 |
| | `POST` | `/user/info/vet` | 수의사 정보 조회 |
| | `POST` | `/user/info/pet` | 반려동물 정보 조회 |
| | `POST` | `/user/parent/registerpet` | 신규 반려동물 정보 등록 |
| **Hospital** | `GET` | `/address/city` | 지역별 시/군/구 목록 조회 |
| | `POST` | `/info/hospital` | 병원 상세 정보 조회 |
| | `POST` | `/info/hospital/list` | 제휴 병원 목록 조회 |
| | `POST` | `/user/reservation` | 병원 진료 예약 신청 |
| | `POST` | `/info/reservation` | 예약 상세 내역 확인 |
| **AI Diagnosis** | `POST` | `/user/diagnosis` | 이미지 기반 AI 질환 진단 요청 (AI Server 연동) |
| | `POST` | `/user/diagnosislist` | 사용자별 진단 이력 목록 조회 |
| | `POST` | `/user/infodiagnosis` | 특정 진단 결과 상세 조회 |

---

## 💡 백엔드 핵심 설계 및 기술적 의사결정

### 1. 계층화된 커스텀 예외(Exception) 핸들링 체계 구축
- 입력 검증(`Null*Exception`, `Invalid*Exception`), 리소스 식별(`NotExistException`), 비즈니스 중복(`Duplicate*Exception`), 세션 상태(`LoginStatusException`)를 세분화하여 설계
- 컨트롤러 계층 전반에서 일관된 에러 응답 규격을 유지하여 프론트엔드와의 협업 생산성 향상

### 2. 이기종 서버(Spring Boot - Flask) 간 통신 안정화
- Spring Boot 서버가 중계자(Gateway) 역할을 수행하며 AI 추론 서버의 장애나 지연이 클라이언트에게 직접 전파되지 않도록 설계
- AI 서버의 JSON 응답 구조(질환명, 일치 확률, 식별자)를 백엔드 DTO로 안전하게 매핑 및 영속화

### 3. UUID 기반 식별자 전략
- Auto-Increment ID 노출로 인한 비즈니스 데이터 유출 위험을 방지하기 위해 사용자, 반려동물, 병원 등의 주요 엔티티에 UUID 식별자를 도입하여 보안성 강화
