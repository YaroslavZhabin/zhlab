package com.example.demo.controller;

import com.example.demo.dao.StudentJdbc;
import com.example.demo.model.Mark;
import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
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
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable int id){
        return studentJdbc.get(id);
    }

    @GetMapping("/student/group")
    public List<Student> getStudentByGroup(@RequestParam(value="student_group_id") Integer student_group_id){
        return studentJdbc.getStudentByGroup(student_group_id);
    }

    @GetMapping("/student/all")
    public List<Student> getStudentByGroup(){
        return studentJdbc.getAll();
    }

    @DeleteMapping("/student/delete")
    public void deleteStudent(@RequestParam(value="id") Integer id){
        studentJdbc.delete(id);
    }
}
