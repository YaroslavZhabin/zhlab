package com.example.lab.dao;

import com.example.lab.model.Journal;
import com.example.lab.model.Student;
import com.example.lab.model.StudentGroup;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JournalJdbc {

    private final JdbcTemplate jdbcTemplate;

    public JournalJdbc(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //  Просмотр записи журнала
    public Journal get(int id){
        String sql = "SELECT * FROM JOURNAL WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapJournal, id);
    }

    //  Просмотр записей журнала по студенту
    public Journal getByStudent(int student_id){
        String sql = "SELECT * FROM JOURNAL WHERE student_id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapJournal, student_id);
    }

    //  Просмотр записей журнала по группе
    public Journal getBy(int study_group_id){
        String sql = "SELECT * FROM JOURNAL WHERE STUDENT_ID IN" +
                "(SELECT ID FROM STUDENT WHERE STUDY_GROUP_ID = ?) ";
        return jdbcTemplate.queryForObject(sql, this::mapJournal, study_group_id);
    }

    //   Редактирование оценок в журнале
    public void UpdateJournal(int id, int student_id, int study_plan_id, byte in_time, int count, int mark_id) {
        this.jdbcTemplate.update(
                "MERGE INTO JOURNAL KEY (ID) VALUES (?, ?, ?, ?, ?, ?)",
                id, student_id, study_plan_id, in_time, count,  mark_id);
    }

    public void UpdateStudent(Journal journal) {
        int status = jdbcTemplate.update("MERGE INTO JOURNAL KEY (ID) VALUES (?, ?, ?, ?, ?, ?)",
                journal.getId(), journal.getStudent_id(),
                journal.getStudy_plan_id(), journal.getIn_time(),
                journal.getCount(), journal.getMark_id()
        );
    }

    private Journal mapJournal(ResultSet rs, int i) throws SQLException{
        return new Journal(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("study_plan_id"),
                rs.getByte("in_time"),
                rs.getInt("count"),
                rs.getInt("mark_id")
        );
    }
}