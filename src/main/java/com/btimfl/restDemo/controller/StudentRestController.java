package com.btimfl.restDemo.controller;

import com.btimfl.restDemo.entity.Student;
import com.btimfl.restDemo.errorhandling.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
        if(studentId < 0 || studentList.size() <= studentId) throw new StudentNotFoundException("Student with id " + studentId + " not found!");
        return studentList.get(studentId);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleStudentNotFoundException(StudentNotFoundException exc) {
        StudentErrorResponse response = new StudentErrorResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setTimestamp(System.currentTimeMillis());
        response.setMessage(exc.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleGenericExceptions(Exception exc) {
        StudentErrorResponse response = new StudentErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(System.currentTimeMillis());
        response.setMessage(exc.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
