package com.web.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {

    private static final long serialVersionUID = 1L;

    public SecurityUser(com.web.domain.User user) {
        super(user.getId(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
    }
}
