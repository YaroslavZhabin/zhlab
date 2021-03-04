package com.example.lab.controller;

import com.example.lab.dao.StudentJdbc;
import com.example.lab.model.Mark;
import com.example.lab.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentJdbc studentJdbc;

    public StudentController(StudentJdbc studentJdbc) {
        this.studentJdbc = studentJdbc;
    }

    @PostMapping("/student")
    int newStudent(@RequestBody Student newStudent) {
        try{
            return studentJdbc.UpdateStudent(newStudent);
        }
        catch (Exception e){
            return -1;
        }
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable int id){
        return studentJdbc.get(id);
    }

    @GetMapping("/student/group/{student_group_id}")
    public List<Student> getStudentByGroup(@PathVariable int student_group_id){
        return studentJdbc.getStudentByGroup(student_group_id);
    }

    @GetMapping("/student/all")
    public List<Student> getStudentByGroup(){
        return studentJdbc.getAll();
    }
}