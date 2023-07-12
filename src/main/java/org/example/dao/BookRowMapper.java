package org.example.dao;

import org.example.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setName(rs.getString("id"));
        book.setAuthor(rs.getString("author"));
        book.setName(rs.getString("name"));
        book.setYear(rs.getInt("year"));
        return book;
    }
}
