package com.osiristher.webapp.dbtest.service;

import com.osiristher.webapp.dbtest.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by DesiresDesigner on 3/23/16.
 */
@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {
    Student findByLrid(long lrid);
}
