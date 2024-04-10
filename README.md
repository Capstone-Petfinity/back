# ParentApiController

## /user

### signupParent
#### /signup/parent: POST
**Header**
- String auth;

**Body**
- String id;
- String pw;
- String name;
- String phone_number;
- String city;

### signupVet
#### /signup/vet: POST
**Header**
- String auth;

**Body**
- String id;
- String pw;
- String name;

#### possible error
null
- NullNameException(): 이름 공백
- NullPwException():  비밀번호 공백
- NullCityException(): 도시 공백

validate
- InvalidIdException(): 유효하지 않는 아이디
- InvalidPhoneNumberException(): 유효하지 않는 전화번호
- InvalidPwException(): 유효하지 않는 비밀번호

duplicate
- DuplicateIdException(): 아이디 중복
- DuplicatePhoneNumberException(): 전화번호 중복

# AddressApiController

## /address/city: GET
**Header**
- String auth;
