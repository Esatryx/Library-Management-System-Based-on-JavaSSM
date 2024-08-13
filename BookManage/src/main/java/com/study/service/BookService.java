package com.study.service;

import com.study.entity.Book;
import com.study.entity.Borrow;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Borrow> getBorrowList();
    Map<Book,Boolean> getBookList();
    void addBorrow(int sid,int bid);
    void returnBook(String id);
    List<Book>getActiveBookList();
    void addBook(String title,String desc,double price);
    void deleteBook(int bid);
}
