package com.quinbay.dependencyInjection.dto;


import com.sun.istack.internal.NotNull;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Student implements Serializable {
    @NotNull
    public long id;
    @NotBlank
    public String fname;
    public String lname;
    public String branch;
    public int dept_id;

    public Student(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch =branch;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public Student(long id, String fname, String lname, String branch,int dept) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.branch = branch;
        this.dept_id = dept;
    }
}
