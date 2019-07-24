package com.web.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rank implements Serializable {

    private String text;
    private Long cnt;

    @Builder
    public Rank(String text, Long cnt) {
        this.text = text;
        this.cnt = cnt;
    }
}
