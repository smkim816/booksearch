package com.web;

import com.web.domain.Keyword;
import com.web.domain.User;
import com.web.domain.enums.Role;
import com.web.repository.KeywordRepository;
import com.web.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookJpaTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void jpaTest() {
        User user1 = new User();
        user1.setId("member");
        user1.setPassword(passwordEncoder.encode("member123"));
        user1.setRole(Role.ROLE_MANAGER);
        userRepository.save(user1);

        User user2 = new User();
        user2.setId("admin");
        user2.setPassword(passwordEncoder.encode("admin123"));
        user2.setRole(Role.ROLE_ADMIN);
        userRepository.save(user2);

        for (int i = 1; i <= 6; i++) {
            Keyword keyword = new Keyword();
            keyword.setText("미움" + i);
            keyword.setDateNow();
            keyword.setUser(user1);
            keywordRepository.save(keyword);
        }

        for (int i = 1; i <= 9; i++) {
            Keyword keyword = new Keyword();
            keyword.setText("용기" + i);
            keyword.setDateNow();
            keyword.setUser(user2);
            keywordRepository.save(keyword);
        }
    }
}