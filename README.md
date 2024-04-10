# ParentApiController

## /user

### signupParent
#### /signup/parent: POST

String auth;

String id;

String pw;

String name;

String phone_number;

String city;

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

### signupVet
#### /signup/vet: POST


# AddressApiController

## /address/city: POST

String auth;
