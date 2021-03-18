package com.example.demo.controller;

import com.example.demo.dao.JournalJdbc;
import com.example.demo.model.Journal;
import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class JournalController {
    private final JournalJdbc journalJdbc;

    public JournalController(JournalJdbc journalJdbc) {
        this.journalJdbc = journalJdbc;
    }

    @GetMapping("/journal/student")
    public List<Journal> getJournalByStudent(@RequestParam(value="student_id") Integer student_id){
        return journalJdbc.getJournalByStudent(student_id);
    }

    @GetMapping("/journal/group")
    public List<Journal> getJournalByGroup(@RequestParam(value="group_id") Integer study_group_id){
        return journalJdbc.getJournalByGroup(study_group_id);
    }

    @GetMapping("/journal/types")
    public List<?> getJournalType(){
        return journalJdbc.getTypeCourse();
    }

    @GetMapping("/journal/mark")
    public List<?> getPerformance(){
        return journalJdbc.getPerformance();
    }

    @PostMapping("/journal")
    int newJournal(@RequestBody Journal newJournal) {
        try{
            return journalJdbc.UpdateJournal(newJournal);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @DeleteMapping("/journal/delete")
    public void deleteJournal(@RequestParam(value="id") Integer id){
        journalJdbc.delete(id);
    }
}
