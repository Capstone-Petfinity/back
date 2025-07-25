**Header**
- String auth;

# SignupParentApiController

## signupParent(AES)
#### /user/signup/parent: POST

**Body**
- String id; 8자 이상, 알파벳/숫자만, 공백/NULL x
- String pw; 알파벳/숫자만, 공백/NULL x
- String name; 공백/NULL x
- String phone_number; 숫자만, 공백/NULL x
- String city;

### possible error
auth(400): 권한 없음

invalid(401)
- InvalidIdException(): 유효하지 않은 아이디
- InvalidPhoneNumberException(): 유효하지 않은 전화번호
- InvalidPwException(): 유효하지 않은 비밀번호
- InvalidNameException(): 유효하지 않은 이름

duplicate(402)
- DuplicateIdException(): 중복된 아이디
- DuplicatePhoneNumberException(): 중복된 전화번호

null(403)
- NullNameException(): 입력되지 않은 이름
- NullPwException():  입력되지 않은 비밀번호
- NullCityException(): 입력되지 않은 도시



# SignupVetApiController

## signupVet(AES)
#### /user/signup/vet: POST

**Body**
- String id; 5자, 숫자만, 공백/NULL x
- String pw; 알파벳/숫자만, 공백/NULL x
- String name; 공백/NULL x
- String phone_number; 숫자만, 공백/NULL x

### possible error
auth(400): 권한 없음

invalid(401)
- InvalidIdException(): 유효하지 않는 아이디
- InvalidPhoneNumberException(): 유효하지 않는 전화번호
- InvalidPwException(): 유효하지 않는 비밀번호
- InvalidNameException(): 유효하지 않는 이름

duplicate(402)
- DuplicateIdException: 중복된 아이디

null(403)
- NullNameException: 입력되지 않은 이름
- NullPwException: 입력되지 않은 이름



# IdDuplicateApiController

## parentDuplicateId
#### /user/signup/parent/idduplicate: POST

**Body**
- String id; 8자 이상, 알파벳/숫자만, 공백/NULL x

### possible error
auth(400): 권한 없음

invalid(401)
- InvalidIdException: 유효하지 않은 아이디

duplicate(402)
- DuplicateIdException: 중복된 아이디


## vetDuplicateId
#### /user/signup/vet/idduplicate: POST

**Body**
- String id; 5자, 숫자만, 공백/NULL x

### possible error
auth(400): 권한 없음

invalid(401)
- InvalidIdException: 유효하지 않은 아이디

duplicate(402)
- DuplicateIdException: 중복된 아이디



# LoginApiController

## Login(AES)
#### /user/login: POST

**Body**
- String id;
- String pw;

### possible error
auth(400): 권한 없음

null(403)
- NullIdException: 입력되지 않은 아이디
- NullPwException: 입력되지 않은 비밀번호

exist(404)
- NotExistException: 존재하지 않는 회원

incorrect(405)
- IncorrectPwException: 일치하지 않는 비밀번호

loginStatus(406)
- LoginStatusException: 로그인 상태



# LogoutApiController

## Logout
#### /user/logout: POST

**Body**
- String uuid;
- Boolean isParent;

### possible error
auth(400): 권한 없음

fail(404)
- FailLogoutException(): 로그아웃 실패(uuid 에러, isParent 에러 통합)



# InfoApiController

## infoParent
#### /user/info/parent: POST

**Body**
- String uuid;

### possible error
auth(400): 권한 없음

null(403)
- NullUuidException: 입력되지 않은 uuid

invalid(401)
- InvalidUuidException: 유효하지 않은 uuid

exist(404)
- NotExistException: 존재하지 않는 회원

loginStatus(406)
- NotLoginStatusException: 로그아웃 상태


## infoPet
#### /user/info/pet: POST

**Body**
- String uuid;

### possible error
auth(400): 권한 없음

null(403)
- NullUuidException: 입력되지 않은 uuid

invalid(401)
- InvalidUuidException: 유효하지 않은 uuid

exist(404)
- NotExistException: 존재하지 않는 회원

loginStatus(406)
- NotLoginStatusException: 로그아웃 상태


## infoVet
#### /user/info/vet: POST

**Body**
- String uuid;

### possible error
auth(400): 권한 없음

null(403)
- NullUuidException: 입력되지 않은 uuid

invalid(401)
- InvalidUuidException: 유효하지 않은 uuid

exist(404)
- NotExistException: 존재하지 않는 회원

loginStatus(406)
- NotLoginStatusException: 로그아웃 상태


## infoHospital
#### /info/hospital: POST

**Body**
- String uuid;

### possible error
auth(400): 권한 없음

null(403)
- NullUuidException: 입력되지 않은 uuid

invalid(401)
- InvalidUuidException: 유효하지 않은 uuid

exist(404)
- NotExistException: 존재하지 않는 병원


## infoReservation
#### /info/reservation: POST

**Body**
- String uuid;

### possible error
auth(400): 권한 없음

null(403)
- NullUuidException: 입력되지 않은 uuid

invalid(401)
- InvalidUuidException: 유효하지 않은 uuid

exist(404)
- NotExistException: 존재하지 않는 예약



# AddressApiController

## returnCityList
#### /address/city: GET



# RegisterPetApiController

## registerPet
#### /user/parent/registerpet: POST

**Body**
- String name;
- Localdate birth;
- String gender;
- String kind;
- String parentUuid;

### possible error
auth(400): 권한 없음

null(403)
- NullNameException: 입력되지 않은 이름
- NullPetGenderException: 입력되지 않은 성별
- NullPetKindException: 입력되지 않은 품종
- NullUuidException: 입력되지 않은 uuid

invalid(401)
- InvalidUuidException: 유효하지 않은 uuid

exist(404)
- NotExistException: 존재하지 않는 회원

loginStatus(406)
- NotLoginStatusException: 로그아웃 상태



# HospitalListApiController

## hospitalList
#### /info/hospital/list: POST

**Body**
- String uuid;

### possible error
auth(400): 권한 없음

null(403)
- NullUuidException: 입력되지 않은 uuid

invalid(401)
- InvalidUuidException: 유효하지 않은 uuid

exist(404)
- NotExistException: 존재하지 않는 회원

loginStatus(406)
- NotLoginStatusException: 로그아웃 상태



# ReservationApiController

## reservation
#### /user/reservation : POST

**Body**
- String userUuid;
- String petUuid;
- String hospitalUuid;
- String reservationDate;

### possible error
auth(400): 권한 없음

null(403)
- NullUuidException: 입력되지 않은 uuid(parent, pet, hospital)

invalid(401)
- InvalidUuidException: 유효하지 않은 uuid(parent, pet, hospital)

exist(404)
- NotExistException: 존재하지 않는 회원, 반려동물, 병원

loginStatus(406)
- NotLoginStatusException: 로그아웃 상태



# DiagnosisApiController

## diagnosis
#### /user/diagnosis : POST
#### http://203.250.148.132:5000/diagnosis : POST

**Param(toAi)**
- auth 없음
- String user_uuid; 
- String disease_area; 
- String type; 
- String position; 
- String disease; 
- String img_url;

**Param(toFront)**
- String user_uuid; 
- String disease_name; 
- String percent; 
- String content; 
- String insert_id;

### possible error
auth(400): 권한 없음

error(401): 에러 발생


## diagnosisList
### /user/diagnosislist : POST

**Body**
- String uuid;

### possible error
auth(400): 권한 없음

exist(404)
- NotExistException: 존재하지 않는 회원

loginStatus(406)
- NotLoginStatusException: 로그아웃 상태


## infoDiagnosis
### /user/infodiagnosis : POST

**Body**
- String uuid;

### possible error
auth(400): 권한 없음

null(403)
- NullUuidException: 입력되지 않은 uuid

invalid(401)
- InvalidUuidException: 유효하지 않은 uuid

exist(404)
- NotExistException: 존재하지 않는 진단
