# ceres
20180602 score record project with yunkai

## ����.proto �Զ����ɿ�ƽ̨���������

> �ĵ����У�ȱ��Լ����ȱ���Զ�������

### �ĵ�����

ǰ��˷���Ŀ���ģʽ��ǰ�˺ͺ�˿��Բ��п���

�ŵ㣺
1. �������ܼ��ٵȴ��롰��ͨ������������Ч�ʡ�

ȱ�㣺
1. �ĵ�������룬��Ҫ����Աר��ά�����������ʧ��
2. ���ź�£�������

### ���µĿ�������

1. ���󷽳����󣬲�Ʒ������Ʒԭ�͡�
2. ��˸���ԭ������Rest API��
3. �����confluence��дAPI�ĵ����ĵ����У���
4. ��˸����ĵ�����API�ı��빤����ǰ��ͨ��Mock�ķ�ʽ����ͬʱ��ʼ����������
5. ����䶯ʱ�ظ�2-4�Ĳ���

### ��֮��Ч��Լ��

> �ĵ��ͱ����һ��̽��

#### Swagger

```java
@ApiOperation("��Ȩ��̬������Ⱥ��")
@PostMapping("authCreate")
@ApiImplicitParam(name = "adCrownApiReq", value = "�������", required = true, dataType = "AdCrownApiReq", paramType = "body")
public Result<String> authCreate(@ReqeuestBody AdCrownApiReq<AuthCrownFeature> crownApiReq) {
    // do somthing
}
```

 *�����Եģ���ƽ̨�󶨵�, �������еġ��������˹�����*

#### .proto


```proto
# common.proto
message CommonResult {
    bool status = 1;
    string message = 2;
}

# user.proto
syntax = "proto3"

option java_package = "com.github.qwchat.ceres.backend.dto"
option java_multiple_files = true;

package user;
import "common.proto"

message UserInfo {
    string nickname = 1;
    string email = 2;
    string userId = 3;
}

message LoginReqeust {
    required string nickname = 1;
    required string password = 2;
}

message LoginResponse {
    CommonResult result = 1;
    UserInfo userInfo = 2;
}

service User {
    # ��½
    rpc Login (LoginRequest) returns (LoginResponse);
}

```

*�ӿڵ������뷴������Ϣ�嶼һĿ��Ȼ��ƽ̨�޹صģ��ĵ�����*

#### ���

[Java APi](https://developers.google.com/protocol-buffers/docs/reference/java-generated)
[Javascript API](https://developers.google.com/protocol-buffers/docs/reference/javascript-generated)
[Thirdparty Pluing List](https://github.com/google/protobuf/blob/master/docs/third_party.md)

#### ��˴����Զ�����

```java
//protoc --proto_path=src --java_out=build/gen src/foo.proto

 dto/req/LoginRequest
 dto/rsp/LoginResponse
 controller/UserCon.login(LoginRequest): LoginResponse

```

#### Js�����Զ�����
```ts
<!-- services/user.ts -->
import webapi from "../webapi"
export interface CommonResult {
    status: boolean;
    message: string;
}
export interface UserInfo {
    nickname: string;
    email: string;
    userId: string;
}
...

export function Login(payload: Partial<LoginRequest>) {
    // ·������һ���������ɣ��������ļ���.������/�ӿ�����
    return webapi<LoginRequest>('user.User/Login', payload);
}

// webapi,�����������
```

#### ��׿�˴�������

#### IOS�˴�������

#### �汾����
api/v1/user.proto
api/v2/user.proto