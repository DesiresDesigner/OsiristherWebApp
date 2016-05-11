package com.osiristher.webapp.testingsystem.tester;

import com.osiristher.webapp.dbtest.domain.Student;
import com.osiristher.webapp.dbtest.domain.Student_task;
import com.osiristher.webapp.dbtest.domain.Task;
import com.osiristher.webapp.dbtest.service.StudentRepo;
import com.osiristher.webapp.dbtest.service.StudentTaskRepo;
import com.osiristher.webapp.dbtest.service.TaskRepo;
import com.osiristher.webapp.testingsystem.tester.fixtures.Result;
import com.osiristher.webapp.testingsystem.tester.interfaces.Handleable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;

/**
 * Created by DesiresDesigner on 5/10/16.
 */
public class TestResultHandler implements Handleable {
    StudentTaskRepo studentTaskRepo;
    StudentRepo studentRepo;
    TaskRepo taskRepo;

    LinkedList<Result> resultsList;

    public void setAutowireds(StudentTaskRepo studentTaskRepo,
                              StudentRepo studentRepo, TaskRepo taskRepo) {
        this.studentTaskRepo = studentTaskRepo;
        this.studentRepo = studentRepo;
        this.taskRepo = taskRepo;
    }
    public void setResultsList(LinkedList<Result> resultsList){
        this.resultsList = resultsList;
    }

    @Override
    synchronized public void handle() {
        Result r = resultsList.removeLast();
        Student_task st = new Student_task();
        Student student = studentRepo.findOne(r.getUserId());
        st.setStudent(student);

        Task task = taskRepo.findOne(r.getTaskId());
        st.setTask(task);
        st.setProgress(r.getCorrectnes());
        st.setTime(r.getExecTimeMS());
        st.setMemory(r.getExecMem());
        //ToDo store message in db (errors and other)
        studentTaskRepo.save(st);
    }
}