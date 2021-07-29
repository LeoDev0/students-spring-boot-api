package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentService.getStudents();

        return ResponseEntity.ok(students);
    }

    @GetMapping(path = "{studentId}")
    public ResponseEntity<Student> getSingleStudent(@PathVariable("studentId") Long id) {
        Student student = studentService.getSingleStudent(id);

        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("{studentId}")
    public ResponseEntity updateStudent(
            @PathVariable("studentId") Long id,
            @RequestBody Student student) {
        studentService.updateStudent(id, student);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path ="{studentId}")
    public ResponseEntity deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
