package com.hcc.entities;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
    private Long id;
    private String authority;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Authority(String authority) {
        this.authority = authority;
    }

    public Authority(String authority, User user) {
        this.authority = authority;
        this.user = user;
    }
}
