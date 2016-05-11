package com.osiristher.webapp.controller.wrapper;

import com.osiristher.webapp.dbtest.domain.Student;
import com.osiristher.webapp.dbtest.domain.Task;

/**
 * Created by DesiresDesigner on 5/11/16.
 */
public class StudentTaskWrapper {
    private Task task;
    private Student student;
    private float bestResult;
    private int execMem;
    private int execTime;
    private short attempts;

    public StudentTaskWrapper(Task task, Student student) {
        this.task = task;
        this.student = student;
        this.bestResult = 0;
        this.execMem = 0;
        this.execTime = 0;
        this.attempts = 0;
    }

    public short getAttempts() {
        return attempts;
    }

    public void setAttempts(short attempts) {
        this.attempts = attempts;
    }

    public int getExecTime() {
        return execTime;
    }

    public void setExecTime(int execTime) {
        this.execTime = execTime;
    }

    public int getExecMem() {
        return execMem;
    }

    public void setExecMem(int execMem) {
        this.execMem = execMem;
    }

    public float getBestResult() {
        return bestResult;
    }

    public void setBestResult(float bestResult) {
        this.bestResult = bestResult;
    }

    public Task getTask() {
        return task;
    }

    public Student getStudent() {
        return student;
    }
}
