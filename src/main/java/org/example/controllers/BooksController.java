package org.example.controllers;

import org.example.dao.BooksDAO;
import org.example.util.BooksValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksDAO booksDAO;
    private final BooksValidator booksValidator;

    @Autowired
    public BooksController(BooksDAO booksDAO, BooksValidator booksValidator) {
        this.booksDAO = booksDAO;
        this.booksValidator = booksValidator;
    }
}
