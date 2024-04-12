**Header**
- String auth;

# Parent/VetApiController

## /user

## signupParent
#### /signup/parent: POST

**Body**
- String id; 8자 이상, 알파벳/숫자만, 공백/NULL x
- String pw; 알파벳/숫자만, 공백/NULL x
- String name; 공백/NULL x
- String phone_number; 숫자만, 공백/NULL x
- String city;

## duplicateId
#### /signup/parent/id: POST

**Body**
- String id; 8자 이상, 알파벳/숫자만, 공백/NULL x

## signupVet
#### /signup/vet: POST

**Body**
- String id; 5자, 숫자만, 공백/NULL x
- String pw; 알파벳/숫자만, 공백/NULL x
- String name; 공백/NULL x

### possible error

auth(400): 권한 없음

invalid(401)
- InvalidIdException(): 유효하지 않는 아이디
- InvalidPhoneNumberException(): 유효하지 않는 전화번호
- InvalidPwException(): 유효하지 않는 비밀번호
- InvalidNameException(): 유효하지 않는 이름

duplicate(402)
- DuplicateIdException(): 아이디 중복
- DuplicatePhoneNumberException(): 전화번호 중복

null(403)
- NullNameException(): 이름 공백
- NullPwException():  비밀번호 공백
- NullCityException(): 도시 공백

## Login
#### /login: POST

**Body**
- String id;
- String pw;

### possible error

## Logout
#### /logout: Get

**Body**
- String uuid;
- Boolean isParent;

### possible error

auth(400): 권한 없음

fail(404)
- FailLogoutException(): 로그아웃 실패(uuid 에러, isParent 에러 통합)

# AddressApiController

## /address/city: GET
**Header**
- String auth;
