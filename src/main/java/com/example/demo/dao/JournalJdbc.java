package com.example.demo.dao;

import com.example.demo.model.Journal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
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
    public List<Journal> getJournalByStudent(int student_id){
        String sql = "SELECT * FROM JOURNAL WHERE student_id = ?";
        return jdbcTemplate.query(sql, this::mapJournal, student_id);
    }

    //  Просмотр записей журнала по группе
    public List<Journal> getJournalByGroup(int study_group_id){
        String sql = "SELECT * FROM JOURNAL WHERE STUDENT_ID IN" +
                "(SELECT ID FROM STUDENT WHERE STUDY_GROUP_ID = ?) ";
        return jdbcTemplate.query(sql, this::mapJournal, study_group_id);
    }

    //   Редактирование оценок в журнале
    public int UpdateJournal(int id, int student_id, int study_plan_id, byte in_time, int count, int mark_id) {
        return jdbcTemplate.update(
                "MERGE INTO JOURNAL KEY (ID) VALUES (?, ?, ?, ?, ?, ?)",
                id, student_id, study_plan_id, in_time, count,  mark_id
        );
    }

    public int UpdateJournal(Journal journal) {
        return jdbcTemplate.update("MERGE INTO JOURNAL KEY (ID) VALUES (?, ?, ?, ?, ?, ?)",
                journal.getId(), journal.getStudent_id(),
                journal.getStudy_plan_id(), journal.getIn_time(),
                journal.getCount(), journal.getMark_id()
        );
    }

    public List<?> getTypeCourse(){
        return jdbcTemplate.queryForList(
                "SELECT S.NAME, E.TYPE FROM EXAM_TYPE E , SUBJECT S, STUDY_PLAN ST " +
                "WHERE ST.SUBJECT_ID=S.ID AND ST.EXAM_TYPE_ID=E.ID", new Object[] {});
    }

    public List<?> getPerformance(){
        return jdbcTemplate.queryForList(
                "SELECT S.name, S.surname, S.second_name, SUB.NAME as subject, ET.TYPE, J.IN_TIME, J.COUNT, M.NAME as mark, M.VALUE\n" +
                        "FROM JOURNAL J\n" +
                        "JOIN STUDENT S ON J.student_id=S.id\n" +
                        "JOIN STUDY_PLAN ST ON J.study_plan_id=ST.id\n" +
                        "JOIN SUBJECT SUB on ST.SUBJECT_ID = SUB.ID\n" +
                        "JOIN EXAM_TYPE ET on ET.ID = ST.EXAM_TYPE_ID\n" +
                        "JOIN MARK M on M.ID = J.MARK_ID",
                new Object[] {});
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

    //  Удаление журнала
    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM JOURNAL WHERE id = ?", id);
    }
}
