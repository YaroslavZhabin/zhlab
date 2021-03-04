package com.example.lab.dao;

import com.example.lab.model.Mark;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MarkJdbc{

    private final JdbcTemplate jdbcTemplate;

    public MarkJdbc(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Mark get(int id){
        String sql = "SELECT * FROM MARK WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapMark, id);
    }

    private Mark mapMark(ResultSet rs, int i) throws SQLException{
        return new Mark(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("value")
        );
    }

    public Mark search(String mark){
        String sql = "SELECT * FROM MARK WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, Mark.class, mark);
    }
}