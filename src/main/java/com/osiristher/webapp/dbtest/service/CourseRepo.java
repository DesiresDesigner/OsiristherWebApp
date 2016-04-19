package com.osiristher.webapp.dbtest.service;

import com.osiristher.webapp.dbtest.domain.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by DesiresDesigner on 3/23/16.
 */
@Repository
public interface CourseRepo extends CrudRepository<Course, Long>{
}
