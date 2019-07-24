package com.web.service;

import com.web.domain.Keyword;
import com.web.domain.Rank;
import com.web.domain.User;
import com.web.repository.KeywordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class KeywordService {

    @Autowired
    private KeywordRepository keywordRepository;

    public ResponseEntity<?> saveKeyword(Keyword keyword, String userId) {
        keyword.setUser(new User(userId));
        keyword.setDateNow();
        keywordRepository.save(keyword);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    public Page<Keyword> getKeywordHistory(Pageable pageable, String userId) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), new Sort(Sort.Direction.DESC, "Idx"));
        return keywordRepository.findByUser(pageable, new User(userId));
    }

    public Page<Rank> getKeywordRank(Pageable pageable) {
        return keywordRepository.findTop10(pageable);
    }
}