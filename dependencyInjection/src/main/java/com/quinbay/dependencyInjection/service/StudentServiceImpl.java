package com.quinbay.dependencyInjection.service;

import com.quinbay.dependencyInjection.dto.Student;
import com.quinbay.dependencyInjection.entity.StudentEntityMongo;
import com.quinbay.dependencyInjection.entity.StudentEntitySQL;
import com.quinbay.dependencyInjection.repository.StudentMongoRepository;
import com.quinbay.dependencyInjection.repository.StudentRedisRepository;
import com.quinbay.dependencyInjection.repository.StudentSQLRepository;
import com.quinbay.dependencyInjection.repository.Studentdb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentServiceImpl implements  StudentService{
    @Autowired
    Studentdb studentdb;

    @Autowired
    StudentSQLRepository studentSQLRepository;

    @Autowired
    StudentRedisRepository studentRedisRepository;

    @Autowired
    StudentMongoRepository studentMongoRepository;

    //MongoGet
//    public List<Student> getAllStudents(){
//        Iterable<StudentEntityMongo> std = studentMongoRepository.findAll() ;
//        List<Student> std1 = new ArrayList<>();
//
//      for (StudentEntityMongo item : std) {
//           Student student=new Student(item.getId(),item.getfName(),item.getlName(),item.getBranch(),item.getDept_id());
//           std1.add(student);
//      }
//       return std1;
//
//
//    }
    //MongoGet


//    @Override
//    public List<Student> getAllStudents() {
//        return studentdb.getStudentList();
//
//    }

//    public Student getStudent(Long id,String str){
//        for(Student student : studentdb.getStudentList()){
//            if (student.getFname().equals(str) == false) {
//                continue;
//            }
//            if(id == null) {
//                return student;
//            }
//            else if (student.getId() == id) {
//                return student;
//            }
//        }
//        return null;
//    }

    //MongoPost
//    @Override
//    public void addStudent(Student student) {
//        //Redis
//        //studentRedisRepository.save(new Student(student.getId(),student.getFname(),student.getLname(),student.getBranch(),student.getDept_id()));
//        studentMongoRepository.save(new StudentEntityMongo(student.getId(),student.getFname(),student.getLname(),student.getBranch(),student.getDept_id()));
//    }
    //MongoPost

    //MongoUpdate
//    @Override
//    public void updateStudent(Student student){
//        studentMongoRepository.save(new StudentEntityMongo(student.getId(),student.getFname(),student.getLname(),student.getBranch(),student.getDept_id()));
//
//    }
    //MongoUpdate



    //MongoDelete
//    @Override
//    public void deleteStudent(Long id) {
//        Iterable<StudentEntityMongo> studentEntityIterator = studentMongoRepository.findAll();
//        for(StudentEntityMongo mongoEntity:studentEntityIterator)
//            if(mongoEntity.getId()==id) {
//                studentMongoRepository.delete(mongoEntity);
//            }
//
//    }
    //MongoDelete
    @Override
    public boolean isNotPresent(Student std){
        List<Student> llist = getAllStudents();
        for(Student std1 : llist){
            if(std1.getId()==std.getId()){
                return false;
            }
        }
        return true;


    }
    @Override
    public List<Student> getAllStudents() {
        Iterable<StudentEntityMongo> iterable = studentMongoRepository.findAll();
        List<Student> students = new ArrayList<>();
        for (StudentEntityMongo i : iterable) {
            Student s = new Student();
            s.setId(i.getId());
            s.setFname(i.getfName());
            s.setLname(i.getlName());
            s.setBranch(i.getBranch());
            s.setDept_id(i.getDept_id());
            students.add(s);
        }

        Iterable<StudentEntitySQL> iterator = studentSQLRepository.findAll();
        List<Student> studentEntities = new ArrayList<>();
        for (StudentEntitySQL i : iterator) {
            Student student = new Student();
            student.setId(i.getId());
            student.setFname(i.getfName());
            student.setLname(i.getlName());
            student.setBranch(i.getBranch());
            student.setDept_id(i.getDept_id());
            studentEntities.add(student);
        }
        students.addAll(studentEntities);
        List<Student> s = studentdb.getStudentList();
        students.addAll(s);
        List<Student> s1 = studentRedisRepository.findAll();
        students.addAll(s1);
        return students;
    }


    @Override
    public void addStudent(Student student,String integer) {
        if(isNotPresent(student)) {
            if (integer.equals("simpledb")) { //Simple db
                studentdb.addStudent(student);
            } else if (integer.equals("mongodb")) { //Mongo db
                studentMongoRepository.save(new StudentEntityMongo(student.getId(), student.getFname(), student.getLname(), student.getBranch(), student.getDept_id()));
            } else if (integer.equals("redisdb")) { //Redis
                studentRedisRepository.insert(student);
            } else if (integer.equals("postgres")) { //Postgres
                studentSQLRepository.save(new StudentEntitySQL(student.getId(), student.getFname(), student.getLname(), student.getBranch(), student.getDept_id()));
            }
        }
    }

    @Override
    public void updateStudent(Student student,String integer) {

        if (integer.equals("simpledb") && isNotPresent(student)) { //Simple db
            Student s = studentdb.updateStudent(student);

        } else if (integer.equals("mongodb") && isNotPresent(student)) { //Mongo db
            studentMongoRepository.save(new StudentEntityMongo(student.getId(), student.getFname(), student.getLname(), student.getBranch(), student.getDept_id()));
            }
        else if(integer.equals("redisdb") && isNotPresent(student)){ //Redis
            studentRedisRepository.save(student);
        } else if(integer.equals("postgres") && isNotPresent(student )){ //Postgres
            Iterable<StudentEntitySQL> studentEntityList = studentSQLRepository.findAll();
            for (StudentEntitySQL studentEntity : studentEntityList) {
                if(studentEntity.getId() == student.getId()) {
                    studentSQLRepository.save(new StudentEntitySQL(student.getId(), student.getFname(), student.getLname(), student.getBranch(), student.getDept_id()));

                }
            }

        }
    }


    @Override
    public void deleteStudent(Long id,String integer) {
        if (integer.equals("simpledb")) { //Simple db
            for (Student student : studentdb.getStudentList()) {
                if (student.getId() == student.getId()) {
                    studentdb.getStudentList().remove(student);
                }
            }
        } else if (integer.equals("mongodb")) { //Mongo db
            Iterable<StudentEntityMongo> studentEntityIterator = studentMongoRepository.findAll();
            for(StudentEntityMongo mongoEntity:studentEntityIterator)
                if(mongoEntity.getId()==id) {
                    studentMongoRepository.delete(mongoEntity);
                }
        }else if (integer.equals("redisdb")){ //Redis
            studentRedisRepository.delete(id);
        } else if (integer.equals("postgres")){ //Postgres
            studentSQLRepository.deleteById(id);
        }
    }
}
