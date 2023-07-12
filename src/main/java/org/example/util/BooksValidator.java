package org.example.util;

import org.example.dao.BooksDAO;
import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BooksValidator implements Validator {

    private final BooksDAO booksDAO;
    @Autowired
    public BooksValidator(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (booksDAO.show(book.) {
            errors.rejectValue("email", "", "This email is already taken!");
        }
    }
}
