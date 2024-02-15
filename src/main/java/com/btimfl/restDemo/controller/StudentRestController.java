package com.btimfl.restDemo.controller;

import com.btimfl.restDemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> studentList;

    @PostConstruct
    private void loadData() {
        studentList = new ArrayList<Student>();

        studentList.add(new Student("Sanatvikram", "Bisht"));
        studentList.add(new Student("Tuhin", "Upadhyay"));
        studentList.add(new Student("Sathyam", "Rajpal"));
    }

    @GetMapping("/students")
    public List<Student> listAllStudents() {
        return studentList;
    }

    @GetMapping("/students/{studentId}")
    public Student findStudentById(@PathVariable("studentId") int studentId) {
        return studentList.get(studentId);
    }
}
