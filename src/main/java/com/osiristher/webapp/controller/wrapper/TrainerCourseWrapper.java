package com.osiristher.webapp.controller.wrapper;

import com.osiristher.webapp.dbtest.domain.Course;
import com.osiristher.webapp.dbtest.domain.Student;
import com.osiristher.webapp.dbtest.domain.Student_task;
import com.osiristher.webapp.dbtest.domain.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DesiresDesigner on 5/11/16.
 */
public class TrainerCourseWrapper {
    private Course course;
    private Map<Long, TrainerTaskWrapper> tasks;

    public TrainerCourseWrapper(Course course) {
        this.course = course;
        tasks = new HashMap<>();
        for (Task task : this.course.getTasks()) {
            tasks.put(task.getId(), new TrainerTaskWrapper(task));
            for (Student student : this.course.getStudents()) {
                tasks.get(task.getId()).addStudent(student);
            }
        }
    }

    public void addStudentProgress(Student_task st) {
        StudentTaskWrapper stw = tasks.get(st.getTask().getId()).getStudent(st.getStudent().getId());
        //StudentTaskWrapper stw = student_tasks.get(st.getTask().getId());
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

    public Map<Long, TrainerTaskWrapper> getTasks() {
        return tasks;
    }
}
