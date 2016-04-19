package com.osiristher.webapp.dbtest.service;

import com.osiristher.webapp.dbtest.domain.Student_task;
import com.osiristher.webapp.dbtest.domain.util.StudentTaskId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by DesiresDesigner on 3/23/16.
 */
@Repository
public interface StudentTaskRepo extends CrudRepository<Student_task, StudentTaskId> {
}
