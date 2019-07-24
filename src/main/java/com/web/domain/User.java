package com.web.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.web.domain.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class User implements Serializable {

    @Id
    @Column
    private String id;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String id) {
        this.id = id;
    }
}
