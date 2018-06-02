package com.github.qwchat.ceres.backend;

import com.github.qwchat.ceres.backend.domain.UserInfoDomain;
import com.github.qwchat.ceres.backend.repository.UserInfoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
	    return new ProtobufHttpMessageConverter();
    }

    @Bean
    UserInfoRepository userInfoRepository() {
        Map<String, UserInfoDomain> userInfoMap = new HashMap<>(15);
        UserInfoRepository userInfoRepository = new UserInfoRepository(userInfoMap);

        UserInfoDomain demoUser1 = new UserInfoDomain();
        demoUser1.setId("12345678");
        demoUser1.setEmail("770743608@qq.com");
        demoUser1.setNickname("howl");
        demoUser1.setPassword("123");

        UserInfoDomain demoUser2 = new UserInfoDomain();
        demoUser2.setId("87654321");
        demoUser2.setEmail("qguduemc@163.com");
        demoUser2.setNickname("yunkai");
        demoUser2.setPassword("123");

        userInfoMap.put(demoUser1.getId(), demoUser1);
        userInfoMap.put(demoUser2.getId(), demoUser2);

	    return userInfoRepository;
    }
}
