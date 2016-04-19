package com.osiristher.webapp.dbtest.domain.util;

import com.osiristher.webapp.dbtest.domain.Student;
import com.osiristher.webapp.dbtest.domain.Task;

import java.io.Serializable;

/**
 * Created by DesiresDesigner on 3/23/16.
 */
public class StudentTaskId implements Serializable{


    private Student student;
    private Task task;

    public StudentTaskId(){}

    public StudentTaskId(Student student, Task task) {
        this.student = student;
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentTaskId that = (StudentTaskId) o;

        if (student != null ? !student.equals(that.student) : that.student != null) return false;
        return task != null ? task.equals(that.task) : that.task == null;

    }

    @Override
    public int hashCode() {
        int result = student != null ? student.hashCode() : 0;
        result = 31 * result + (task != null ? task.hashCode() : 0);
        return result;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
