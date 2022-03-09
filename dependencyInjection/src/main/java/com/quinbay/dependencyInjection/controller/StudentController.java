package com.quinbay.dependencyInjection.controller;


import com.quinbay.dependencyInjection.dto.Student;
import com.quinbay.dependencyInjection.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentService studentService;



    @GetMapping("/students")
    public List<Student> getStudent(){
        return studentService.getAllStudents();
    }

//    @GetMapping(value = "/{id}")
//    public Student getStudent(@PathVariable Integer id){
//        return studentService.getStudent(id);
//    }

//    @GetMapping
//    public Student getStudent(@RequestParam(required = false) Long id,String fname){
//        return studentService.getStudent(id,fname);
//    }

    @PostMapping(consumes = "application/json")
    public void addStudent(@RequestBody Student std ,@RequestParam String string ){
        studentService.addStudent(std,string);
    }

//    @PutMapping(consumes = "application/json")
//    public Student updateStudent(@RequestBody Student student){
//        return studentService.updateStudent(student);

//    }

    @PutMapping
    public void updateStudent(@RequestBody Student std,@RequestParam String string){
        studentService.updateStudent(std,string);
    }
    @DeleteMapping
    public void deleteStudent(@RequestParam Long i,@RequestParam String string){
        studentService.deleteStudent(i,string );
    }
}

