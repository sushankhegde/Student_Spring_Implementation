package com.quinbay.dependencyInjection.repository;

import com.quinbay.dependencyInjection.entity.StudentEntitySQL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSQLRepository extends  CrudRepository<StudentEntitySQL,Long> {


}
