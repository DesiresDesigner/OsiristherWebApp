package com.osiristher.webapp.dbtest.service;

import com.osiristher.webapp.dbtest.domain.Student;
import com.osiristher.webapp.dbtest.domain.Student_task;
import com.osiristher.webapp.dbtest.domain.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by DesiresDesigner on 3/23/16.
 */
@Repository
public interface StudentTaskRepo extends CrudRepository<Student_task, Long> {
    @Query
    List<Student_task> findByStudent(Student student);

    @Query
    List<Student_task> findByTask(Task task);
}
