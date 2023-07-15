package org.example.dao;

import org.example.models.PairPersonBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PairPersonBookDAO {
    @Autowired
    public PairPersonBookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final JdbcTemplate jdbcTemplate;

    public List<Integer> getBooksIdsByPersonId(int personId) {
        return jdbcTemplate.query("select * from person_book where person_id=?", new Object[]{personId},
                new BeanPropertyRowMapper<>(Integer.class));
    }

    public Optional<PairPersonBook> isBookTaken(int bookId) {
        return jdbcTemplate.query("select * from person_book where book_id=?", new Object[]{bookId},
                        new BeanPropertyRowMapper<>(PairPersonBook.class))
            .stream()
            .findFirst();
    }

    public void save(int personId, int bookId) {
        jdbcTemplate.update("insert into person_book(person_id, book_id) values (?,?)", personId, bookId);
    }

    public void deleteBook(int bookId) {
        jdbcTemplate.update("delete from person_book where book_id=?", bookId);
    }
    public void deletePersonsBooks(int personId) {
        jdbcTemplate.update("delete from person_book where person_id=?", personId);
    }

}
