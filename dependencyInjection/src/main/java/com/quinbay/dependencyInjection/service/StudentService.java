package com.quinbay.dependencyInjection.service;

import com.quinbay.dependencyInjection.dto.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents() ;
    void addStudent(Student student,String integer) ;
    void updateStudent(Student student,String integer);
    void deleteStudent(Long id,String integer);
    boolean isNotPresent(Student std);
}
