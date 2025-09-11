package edu.cit.queddeng.jamesadriane.campusequipmentloan.Controller;

import edu.cit.queddeng.jamesadriane.campusequipmentloan.Entity.Student;
import edu.cit.queddeng.jamesadriane.campusequipmentloan.Repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
