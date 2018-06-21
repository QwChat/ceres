# ceres
20180602 score record project with yunkai

## 基于.proto 自动生成跨平台的样板代码

> 文档先行，缺乏约束，缺乏自动化工具

### 文档先行

前后端分离的开发模式，前端和后端可以并行开发

优点：
1. 理论上能减少等待与“沟通”，提升工作效率。

缺点：
1. 文档脱离编码，需要程序员专门维护，容易年久失修
2. 大桥合拢总有误差

### 大致的开发流程

1. 需求方出需求，产品给出产品原型。
2. 后端根据原型提炼Rest API。
3. 后端在confluence书写API文档（文档先行）。
4. 后端根据文档着手API的编码工作，前端通过Mock的方式几乎同时开始开发工作。
5. 需求变动时重复2-4的步骤

### 行之有效的约束

> 文档和编码合一的探索

#### Swagger

```java
@ApiOperation("授权生态创建人群包")
@PostMapping("authCreate")
@ApiImplicitParam(name = "adCrownApiReq", value = "请求参数", required = true, dataType = "AdCrownApiReq", paramType = "body")
public Result<String> authCreate(@ReqeuestBody AdCrownApiReq<AuthCrownFeature> crownApiReq) {
    // do somthing
}
```

 *侵入性的，与平台绑定的, 代码先行的。且增加了工作量*

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
    # 登陆
    rpc Login (LoginRequest) returns (LoginResponse);
}

```

*接口的请求与反馈的消息体都一目了然，平台无关的，文档先行*

#### 插件

[Java APi](https://developers.google.com/protocol-buffers/docs/reference/java-generated)
[Javascript API](https://developers.google.com/protocol-buffers/docs/reference/javascript-generated)
[Thirdparty Pluing List](https://github.com/google/protobuf/blob/master/docs/third_party.md)

#### 后端代码自动生成

```java
//protoc --proto_path=src --java_out=build/gen src/foo.proto

 dto/req/LoginRequest
 dto/rsp/LoginResponse
 controller/UserCon.login(LoginRequest): LoginResponse

```

#### Js代码自动生成
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
    // 路径根据一定规则生成，这里是文件名.服务名/接口名称
    return webapi<LoginRequest>('user.User/Login', payload);
}

// webapi,网络请求代码
```

#### 安卓端代码生成

#### IOS端代码生成

#### 版本控制
api/v1/user.proto
api/v2/user.proto