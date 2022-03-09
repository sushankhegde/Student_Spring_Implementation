package com.quinbay.dependencyInjection.repository;

import com.quinbay.dependencyInjection.dto.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;



@Repository
public class Studentdb {
    List<Student> llist=new ArrayList<>();

    {
        Student obj1 = new Student(1,"sushank1","hegde1","ece1",1);
        Student obj2 = new Student(2,"sushank2","hegde2","ece2",2);
        Student obj3 = new Student(3,"sushank3","hegde3","ece3",3);

        Student obj4 = new Student(4,"sushank4","hegde4","ece4",4);
        Student obj5 = new Student(5,"sushank5","hegde5","ece5",5);
        llist.add(obj1);
        llist.add(obj2);
        llist.add(obj3);
        llist.add(obj4);
        llist.add(obj5);
    }

    public List<Student> getStudentList(){
        return llist;
    }

    public void addStudent(Student student){
        llist.add(student);
    }

    public void deleteStudent(Long id){

        for(Student i : llist){
            if(i.getId() == id){
                llist.remove(i);
                break;
            }
        }
    }

    public Student updateStudent(Student student){
        for(Student i : llist){
            if(i.getId() == student.getId()){
                i.setFname(student.getFname());
                i.setLname(student.getLname());
                i.setBranch(student.getBranch());
                //  i.getDept_id(student.getDept_id());
                return new Student(i.getId(), i.getFname(), i.getLname(), i.getBranch(), i.getDept_id());
            }
        }
        return null;
    }


}
