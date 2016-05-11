package com.osiristher.webapp.controller.wrapper;

import com.osiristher.webapp.dbtest.domain.Student;
import com.osiristher.webapp.dbtest.domain.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DesiresDesigner on 5/11/16.
 */
public class TrainerTaskWrapper {
    Task task;
    Map<Long, StudentTaskWrapper> students;

    public TrainerTaskWrapper(Task task) {
        this.task = task;
        students = new HashMap<>();
    }

    public void addStudent(Student student) {
        students.put(student.getId(), new StudentTaskWrapper(task, student));
    }

    public StudentTaskWrapper getStudent(long id) {
        return students.get(id);
    }

    public Task getTask() {
        return task;
    }

    public Map<Long, StudentTaskWrapper> getStudents() {
        return students;
    }
}
