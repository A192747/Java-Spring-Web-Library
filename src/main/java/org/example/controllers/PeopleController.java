package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dao.BooksDAO;
import org.example.dao.PairPersonBookDAO;
import org.example.dao.PersonDAO;
import org.example.models.Book;
import org.example.models.PairPersonBook;
import org.example.models.Person;
import org.example.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Indexed;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    private final BooksDAO booksDAO;
    private final PairPersonBookDAO pairPersonBookDAO;
    private final PersonValidator personValidator;
    @Autowired
    public PeopleController(PersonDAO personDAO, BooksDAO booksDAO, PairPersonBookDAO pairPersonBookDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.booksDAO = booksDAO;
        this.pairPersonBookDAO = pairPersonBookDAO;
        this.personValidator = personValidator;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        List<Book> books = new ArrayList<>();
        List<Integer> booksIds = pairPersonBookDAO.getBooksIdsByPersonId(id);
        System.out.println(booksIds);
        for(int bookId: booksIds) {
            books.add(booksDAO.show(bookId));
        }
        System.out.println(books);

        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", books);
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors())
            return "people/edit";
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

}
