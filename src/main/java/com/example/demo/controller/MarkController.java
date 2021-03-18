package com.example.demo.controller;

import com.example.demo.dao.MarkJdbc;
import com.example.demo.model.Mark;
import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class MarkController {
    private final MarkJdbc markJdbc;

    public MarkController(MarkJdbc markJdbc) {
        this.markJdbc = markJdbc;
    }

    @GetMapping("/mark/{id}")
    public Mark getMark(@PathVariable int id){
        return markJdbc.get(id);
    }

    @GetMapping("/mark")
    public Mark getMarkByValue(@RequestParam(value="value") String value){
        return markJdbc.getMarkByValue(value);
    }

    @GetMapping("/mark/all")
    public List<Mark> getMarkAll(){
        return markJdbc.getAll();
    }
}
