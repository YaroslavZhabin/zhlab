package com.example.demo.dao;

import com.example.demo.model.Mark;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    public List<Mark> getAll(){
        String sql = "SELECT * FROM MARK";
        return jdbcTemplate.query(sql, this::mapMark);
    }

    private Mark mapMark(ResultSet rs, int i) throws SQLException{
        return new Mark(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("value")
        );
    }

    public Mark getMarkByValue(String value){
        String sql = "SELECT * FROM MARK WHERE value = ?";
        return jdbcTemplate.queryForObject(sql, this::mapMark, value);
    }
}