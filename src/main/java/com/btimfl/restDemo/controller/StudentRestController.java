package com.btimfl.restDemo.controller;

import com.btimfl.restDemo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    @GetMapping("/students")
    public List<Student> listAllStudents() {
        List<Student> studentList = new ArrayList<Student>();

        studentList.add(new Student("Sanatvikram", "Bisht"));
        studentList.add(new Student("Tuhin", "Upadhyay"));
        studentList.add(new Student("Sathyam", "Rajpal"));

        return studentList;
    }
}
