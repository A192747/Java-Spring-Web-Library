package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dao.BooksDAO;
import org.example.dao.PairPersonBookDAO;
import org.example.dao.PersonDAO;
import org.example.models.Book;
import org.example.models.PairPersonBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksDAO booksDAO;
    private final PersonDAO personDAO;

    private PairPersonBookDAO pairPersonBookDAO;

    @Autowired
    public BooksController(BooksDAO booksDAO, PersonDAO personDAO, PairPersonBookDAO pairPersonBookDAO) {
        this.booksDAO = booksDAO;
        this.personDAO = personDAO;
        this.pairPersonBookDAO = pairPersonBookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksDAO.index());
        return "books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksDAO.show(id));
        Optional<PairPersonBook> temp = pairPersonBookDAO.isBookTaken(id);
        if(temp.isPresent()) {
            model.addAttribute("personTake", personDAO.show(temp.get().getPersonId()).getName());
        }
        model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping("")
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";
        booksDAO.save(book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                          BindingResult bindingResult,
                          @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "books/edit";
        booksDAO.update(id, book);
        return "redirect:/books";
    }

    @PostMapping("/{id}")
    public String setBook(@PathVariable("id") int bookId,
                          @ModelAttribute("person_id") int personId) {

        return "redirect:/books/"+ bookId;
    }

}
