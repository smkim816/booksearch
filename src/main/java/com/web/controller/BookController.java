package com.web.controller;

import com.web.domain.Book;
import com.web.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping({"", "/"})
    public String book(@RequestParam(value = "query") String query, Model model) {
        model.addAttribute("book", bookService.getBook(query).getDocuments());
        return "book/form";
    }

    @GetMapping("/list")
    public String list(@RequestParam(value = "query") String query, @RequestParam(value = "page") int page, Model model) {
        if (page < 1) page = 1;
        Book book = bookService.getBookList(query, page);
        model.addAttribute("query", query);
        model.addAttribute("page", page);
        model.addAttribute("pageInfo", book.getMeta());
        model.addAttribute("bookList", book.getDocuments());
        return "book/list";
    }
}
