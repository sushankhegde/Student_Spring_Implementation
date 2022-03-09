//package com.quinbay.dependencyInjection.service;
//
//import com.quinbay.dependencyInjection.dto.Department;
//import com.quinbay.dependencyInjection.dto.Student;
//import com.quinbay.dependencyInjection.repository.Departmentdb;
//import com.quinbay.dependencyInjection.repository.Studentdb;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class DepartmentServiceImp implements DepartmentService {
//    @Autowired
//    Departmentdb departmentdb;
//
//
//    public List<Department> getDepartment() {
//        return departmentdb.getAllDepartment();
//    }
//
//    public Department getDept(Integer id){
//        for(Department dpt : departmentdb.getAllDepartment()){
//            if (dpt.getDept_id() == id) {
//                return dpt;
//            }
//        }
//        return null;
//    }
//}
