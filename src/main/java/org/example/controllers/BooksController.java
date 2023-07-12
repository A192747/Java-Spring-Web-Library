package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dao.BooksDAO;
import org.example.models.Book;
import org.example.util.BooksValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public String index(Model model) {
        model.addAttribute(booksDAO.index());
        return "books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute(booksDAO.show(id));
        return "books/show";
    }

    @PostMapping("")
    public String create(@ModelAttribute("book") @Valid Book book
            , BindingResult bindingResult) {
        booksValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/new";

        booksDAO.save(book);
        return "redirect:/books";
    }

}
