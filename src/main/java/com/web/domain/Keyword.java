package com.web.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Keyword implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String text;

    @Column
    private LocalDateTime date;

    @OneToOne(fetch= FetchType.LAZY)
    private User user;

    public void setDateNow() {
        this.date = LocalDateTime.now();
    }
}
