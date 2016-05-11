package com.osiristher.webapp.dbtest.domain;

import javax.persistence.*;

/**
 * Created by DesiresDesigner on 3/23/16.
 */

@Entity
//@IdClass(StudentTaskId.class)
@Table(name = "student_task")
public class Student_task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "progress", unique = false, nullable = true)
    private float progress;

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

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
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
