package org.example.dao;

import org.example.models.PairPersonBook;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PairPersonBookRowMapper implements RowMapper<PairPersonBook> {
    @Override
    public PairPersonBook mapRow(ResultSet rs, int rowNum) throws SQLException {
        PairPersonBook pairPersonBook = new PairPersonBook();
        pairPersonBook.setPersonId(rs.getInt("person_id"));
        pairPersonBook.setBookId(rs.getInt("book_id"));
        return pairPersonBook;
    }
}
