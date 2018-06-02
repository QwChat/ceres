package com.github.qwchat.ceres.backend.controll;

import com.github.qwchat.ceres.backend.domain.UserInfoDomain;
import com.github.qwchat.ceres.backend.dto.*;
import com.github.qwchat.ceres.backend.repository.UserInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * User controll
 *
 * @author yunkai(xiany @ uuzu.com)
 * <p>
 * date 2018/6/2
 * @since 0.0.1
 */
@RestController
@RequestMapping(value = "/api/user.user/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserCon {

    @Resource
    private UserInfoRepository userInfoRepository;

    @PostMapping(value = "register")
    public RegisterResponse regist(@RequestBody RegisterRequest registerRequest) {
        UserInfoDomain userInfoDomain = convert(registerRequest);
        String userId = userInfoRepository.insert(userInfoDomain);

        return RegisterResponse.newBuilder().setResult(ok()).setUserId(userId).build();
    }

    @PostMapping(value = "login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        Optional<UserInfoDomain> optional = userInfoRepository.foundByNickNameAndPassword(loginRequest.getNickname(),
                loginRequest.getPassword());


        if (optional.isPresent()) {
            return LoginResponse.newBuilder().setResult(ok())
                    .setUserInfo(optional.map(this::convert).get()).build();
        }
        return LoginResponse.newBuilder().setResult(fail()).build();
    }



    private UserInfoDomain convert(RegisterRequest registerRequest) {
        UserInfoDomain info = new UserInfoDomain();
        BeanUtils.copyProperties(registerRequest, info);
        return info;
    }

    private UserInfo convert(UserInfoDomain domain) {
        return UserInfo.newBuilder().setUserId(domain.getId())
                .setEmail(domain.getEmail()).setNickname(domain.getNickname()).build();
    }

    private CommonResult ok() {
        return CommonResult.newBuilder().setMessage("SUCCESS").setStatus(true).build();
    }

    private CommonResult fail() {
        return CommonResult.newBuilder().setMessage("FAIL").setStatus(true).build();
    }
}
