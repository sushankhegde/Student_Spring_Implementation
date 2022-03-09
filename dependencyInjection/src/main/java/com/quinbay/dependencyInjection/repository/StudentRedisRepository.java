package com.quinbay.dependencyInjection.repository;

import com.quinbay.dependencyInjection.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class StudentRedisRepository {
     private String STUDENT_COLLECTION = "student";
     private RedisTemplate<String,Student> redisTemplate;
     private HashOperations hashOperations;

     @Autowired
     public StudentRedisRepository(RedisTemplate<String,Student> redisTemplate){
         this.redisTemplate = redisTemplate;

     }
    @PostConstruct
    public void init(){
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public List<Student> findAll(){
        Map<String,Student> allElements = hashOperations.entries(STUDENT_COLLECTION);
        return allElements.values().stream().collect(Collectors.toList());
    }

    public Student findOne(String studentId){
        return (Student) hashOperations.get(STUDENT_COLLECTION, studentId);
    }

    public void save(Student student){
         hashOperations.put(STUDENT_COLLECTION,student.getId(),student);
         //return student;
    }

    public void delete(Long id){
         hashOperations.delete(STUDENT_COLLECTION,id);
    }

    public void insert(Student student){
         hashOperations.put(STUDENT_COLLECTION,student.getId(),student);
    }

}
