package com.osiristher.webapp.controller.wrapper;

import com.osiristher.webapp.dbtest.domain.Course;
import com.osiristher.webapp.dbtest.domain.Student;
import com.osiristher.webapp.dbtest.domain.Student_task;
import com.osiristher.webapp.dbtest.domain.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DesiresDesigner on 5/11/16.
 */
public class StudentCourseWrapper {
    private Course course;
    private Map<Long, StudentTaskWrapper> student_tasks;


    public StudentCourseWrapper(Course course, Student student) {
        this.course = course;
        student_tasks = new HashMap();
        for (Task task : this.course.getTasks()) {
            student_tasks.put(task.getId(), new StudentTaskWrapper(task, student));
        }
    }

    public void addStudTask(Student_task st) {
        StudentTaskWrapper stw = student_tasks.get(st.getTask().getId());
        if (stw.getBestResult() < st.getProgress()) {
            stw.setBestResult(st.getProgress());
            stw.setExecMem(st.getMemory());
            stw.setExecTime(st.getTime());
        }
        stw.setAttempts((short)(stw.getAttempts() + 1));
    }

    public Course getCourse() {
        return course;
    }

    public Map<Long, StudentTaskWrapper> getStudent_tasks() {
        return student_tasks;
    }
}
