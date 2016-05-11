package com.osiristher.webapp.testingsystem.tester.fixtures;

import java.util.LinkedList;

/**
 * Created by DesiresDesigner on 26.02.15.
 */

/*
Exit code
0 - ok
1 - unexpected end
2 - wrong answer
3 - rt-error
 */

public class Result {
    private long userId;
    private long taskId;
    private float correctnes;
    private int exitCode;
    private int execTimeMS;
    private int execMem;
    private LinkedList<String> errors;
    private int errorsCNT = 0;

    public Result() {
        execMem = 0;
        execTimeMS = 0;
        exitCode = 0;
        errors = new LinkedList<>();
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getExecTimeMS() {
        return execTimeMS;
    }

    public void setExecTimeMS(int execTimeMS) {
        this.execTimeMS = execTimeMS;
    }

    public int getExecMem() {
        return execMem;
    }

    public void setExecMem(int execMem) {
        this.execMem = execMem;
    }

    public void addError (int str, String have, String expected){
        errors.addLast("On " + str + " line: " + "have: " + have + ", expected: " + expected + ';');
    }

    public void addError (String mes){
        errors.addLast(mes);
    }

    public void setCntToNull(){
        errorsCNT = 0;
    }

    public boolean haveError(){
        return errorsCNT < errors.size();
    }

    public String getNextError(){
        return errors.get(errorsCNT++);
    }

    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    public float getCorrectnes() {
        return correctnes;
    }

    public void setCorrectnes(float correctnes) {
        this.correctnes = correctnes;
    }

    public void copy(Result r){
        correctnes = r.getCorrectnes();
        r.setCntToNull();
        while (r.haveError()) {
            addError(r.getNextError());
        }
    }

    public void print() {
        System.out.println("User: " + userId);
        System.out.println("Task: " + taskId);
        System.out.println("Correctnes: " + correctnes);
        System.out.println("Exit code: " + exitCode);
        System.out.println("Exec time: " + execTimeMS);
        System.out.println("Exec mem: " + execMem);
        setCntToNull();
        while (haveError()) {
            System.out.println(getNextError());
        }
    }
}
