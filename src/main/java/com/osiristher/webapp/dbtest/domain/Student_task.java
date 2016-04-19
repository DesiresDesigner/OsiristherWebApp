package com.osiristher.webapp.dbtest.domain;

import com.osiristher.webapp.dbtest.domain.util.StudentTaskId;

import javax.persistence.*;

/**
 * Created by DesiresDesigner on 3/23/16.
 */

@Entity
@IdClass(StudentTaskId.class)
@Table(name = "student_task")
public class Student_task {
    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "progress", unique = false, nullable = true)
    private short progress;

    @Column(name = "time", unique = false, nullable = true)
    private int time;

    @Column(name = "memory", unique = false, nullable = true)
    private int memory;

    public Student_task() {
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

    public short getProgress() {
        return progress;
    }

    public void setProgress(short progress) {
        this.progress = progress;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }
}
