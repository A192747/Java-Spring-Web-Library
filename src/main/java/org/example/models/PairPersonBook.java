package org.example.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PairPersonBook {

    private int personId;
    private int bookId;

    public PairPersonBook(int personId, int bookId) {
        this.personId = personId;
        this.bookId = bookId;
    }

    public PairPersonBook() {}

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

}
