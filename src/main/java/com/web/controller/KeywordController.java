package com.web.controller;

import com.web.domain.Keyword;
import com.web.service.KeywordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/keyword")
public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @PostMapping({"", "/"})
    public ResponseEntity<?> postKeyword(@RequestBody Keyword keyword, Principal principal) {
        return keywordService.saveKeyword(keyword, principal.getName());
    }

    @GetMapping({"/history"})
    public String history(@PageableDefault Pageable pageable, Principal principal, Model model) {
        model.addAttribute("keywordHistory", keywordService.getKeywordHistory(pageable, principal.getName()));
        return "keyword/history";
    }

    @GetMapping("/rank")
    public String rank(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("keywordRank", keywordService.getKeywordRank(pageable));
        return "keyword/rank";
    }
}
