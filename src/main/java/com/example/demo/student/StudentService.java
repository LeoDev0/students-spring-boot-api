package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        List<Student> students = studentRepository.findAll();

        return students;
    }

    public Student getSingleStudent(Long studentId) {
        Student student = studentRepository.findStudentById(studentId);

        if (student == null) {
            throw new IllegalStateException("Student of id " + studentId + " does not exists.");
        }

        return student;
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentEmail = studentRepository.findStudentByEmail(student.getEmail());

        if (studentEmail.isPresent()) {
            throw new IllegalStateException("Email already exists.");
        }

        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Long studentId, Student newStudentData) {
        Student student = studentRepository.findStudentById(studentId);

        if (student == null) {
            throw new IllegalStateException("Student of id " + studentId + " does not exists.");
        }

        String name = newStudentData.getName();
        String email = newStudentData.getEmail();

        if (!isValidEmail(student.getEmail(), email)) {
            throw new IllegalStateException("Email already taken.");
        }

        student.setEmail(email);

        if (!isValidName(student.getName(), name)) {
            throw new IllegalStateException("Name is not valid.");
        }

        student.setName(name);
    }

    public void deleteStudent(Long studentId) {
        boolean studentExists = studentRepository.existsById(studentId);

        if (!studentExists) {
            throw new IllegalStateException("Student of id " + studentId + " does not exists.");
        }

        studentRepository.deleteById(studentId);
    }

    private boolean isValidName(String oldName, String newName) {
        return newName != null && newName.length() > 0 && !Objects.equals(oldName, newName);
    }

    private boolean isValidEmail(String oldEmail, String newEmail) {
        if (newEmail != null && newEmail.length() > 0 && !Objects.equals(oldEmail, newEmail)) {
            Optional<Student> emailExists = studentRepository.findStudentByEmail(newEmail);

            if (emailExists.isPresent()) {
                return false;
            }

            return true;
        }

        return false;
    }
}
