package com.quinbay.dependencyInjection.repository;

import com.quinbay.dependencyInjection.entity.StudentEntityMongo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMongoRepository extends CrudRepository<StudentEntityMongo,Long> {
}
