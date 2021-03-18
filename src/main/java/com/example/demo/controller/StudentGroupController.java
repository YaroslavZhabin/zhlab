package com.example.demo.controller;

import com.example.demo.dao.StudentGroupJdbc;
import com.example.demo.model.StudentGroup;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class StudentGroupController {
    private final StudentGroupJdbc studentGroupJdbc;


    public StudentGroupController(StudentGroupJdbc studentGroupJdbc) {
        this.studentGroupJdbc = studentGroupJdbc;
    }

    @PostMapping("/group")
    int newGroup(@RequestBody StudentGroup newStudentGroup) {
        try{
            return studentGroupJdbc.UpdateStudentGroup(newStudentGroup);
        }
        catch (Exception e){
            return -1;
        }
    }

    @GetMapping("/group/{id}")
    public StudentGroup getStudentGroup(@PathVariable int id){
        return studentGroupJdbc.get(id);
    }

    @GetMapping("/group/all")
    public List<StudentGroup> StudentGroups(){
        return studentGroupJdbc.getAll();
    }

    @DeleteMapping("/group/delete")
    public void deleteGroup(@RequestParam(value="id") Integer id){
        studentGroupJdbc.delete(id);
    }
}
