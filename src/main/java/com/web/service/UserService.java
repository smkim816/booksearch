package com.web.service;

import com.web.config.SecurityUser;
import com.web.domain.User;
import com.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = Optional.of(userRepository.findById(username));
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(username + " 사용자 없음!");
        } else {
            User user = optional.get();
            return new SecurityUser(user);
        }
    }
}
