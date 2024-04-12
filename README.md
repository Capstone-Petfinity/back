# ParentApiController

## /user

## signupParent
#### /signup/parent: POST
**Header**
- String auth;

**Body**
- String id; 8자 이상, 알파벳/숫자만, 공백/NULL x
- String pw; 알파벳/숫자만, 공백/NULL x
- String name; 공백/NULL x
- String phone_number; 숫자만, 공백/NULL x
- String city;

## duplicate Id
#### /signup/parent/id: POST
**Header**
- String auth;

**Body**
- String id; 8자 이상, 알파벳/숫자만, 공백/NULL x

## signupVet
#### /signup/vet: POST
**Header**
- String auth;

**Body**
- String id; 5자, 숫자만, 공백/NULL x
- String pw; 알파벳/숫자만, 공백/NULL x
- String name; 공백/NULL x

## Login
#### /login: POST
**Header**
- String auth;

**Body**
- String id;
- String pw; 

## possible error

auth(400): 권한 없음

validate(401)
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

# AddressApiController

## /address/city: GET
**Header**
- String auth;
