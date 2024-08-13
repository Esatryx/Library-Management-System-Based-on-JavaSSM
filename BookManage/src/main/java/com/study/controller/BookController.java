package com.study.controller;

import com.study.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class BookController {
    @Resource
    BookService service;

    @GetMapping("/books")
    public String books(Model model) {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname", user.getUsername());
        model.addAttribute("book_list",service.getBookList().keySet());
        model.addAttribute("book_list_status",new ArrayList<>(service.getBookList().values()));
        return "books";
    }

    @GetMapping("/add-book")
    public String addBook(Model model) {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname", user.getUsername());
        return"add-book";
    }

    @PostMapping("/add-book")
    public String addBook(@RequestParam("title") String title, @RequestParam("desc") String desc, @RequestParam("price") double price) {

       service.addBook(title,desc,price);
       return"redirect:/books";
    }

    @GetMapping("/delete-book")
    public String deleteBook(@RequestParam("bid") int bid) {
        service.deleteBook(bid);
        return"redirect:/books";
    }
}
