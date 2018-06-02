package com.github.qwchat.ceres.backend.domain;

/**
 * User info domain
 *
 * @author yunkai(xiany @ uuzu.com)
 * <p>
 * date 2018/6/2
 * @since 0.0.1
 */
public class UserInfoDomain {

    private String id;
    private String nickname;
    private String password;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
