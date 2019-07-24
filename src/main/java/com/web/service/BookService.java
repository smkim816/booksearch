package com.web.service;

import com.web.domain.Book;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BookService {

    @Value("${kakao.api.url}")
    private String kakaoApiUrl;

    @Value("${kakao.api.auth}")
    private String kakaoApiAuth;

    private final RestTemplate restTemplate;

    public BookService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Book getBook(String query) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(kakaoApiUrl)
                .queryParam("target", "isbn")
                .queryParam("query", query)
                .build()
                .encode();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoApiAuth);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, httpEntity, Book.class).getBody();
    }

    public Book getBookList(String query, int page) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(kakaoApiUrl)
                .queryParam("query", query)
                .queryParam("page", page)
                .build()
                .encode();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoApiAuth);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, httpEntity, Book.class).getBody();
    }
}