package com.github.qwchat.ceres.backend.repository;


import com.github.qwchat.ceres.backend.domain.UserInfoDomain;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * User repository, data in mem
 *
 * @author yunkai(xiany @ uuzu.com)
 * <p>
 * date 2018/6/2
 * @since 0.0.1
 */
public class UserInfoRepository {

    private Map<String, UserInfoDomain> userInfoMap;

    public UserInfoRepository(Map<String, UserInfoDomain> userInfoMap) {
        this.userInfoMap = userInfoMap;
    }

    public UserInfoDomain findById(String id) {
        return userInfoMap.get(id);
    }

    public String insert(UserInfoDomain userInfoDomain) {
        String userId = UUID.randomUUID().toString().replace("-", "");
        userInfoDomain.setId(userId);
        userInfoMap.put(userId, userInfoDomain);
        return userId;
    }

    public Optional<UserInfoDomain> foundByNickNameAndPassword(String nickname, String password) {
        return userInfoMap.values().stream().filter(n -> nickname.equals(n.getNickname()))
                .filter(n -> password.equals(n.getPassword())).findAny();
    }
}
