package com.osiristher.webapp;

import com.osiristher.webapp.dbtest.domain.Student;
import com.osiristher.webapp.dbtest.domain.Student_task;
import com.osiristher.webapp.dbtest.domain.Task;
import com.osiristher.webapp.dbtest.service.StudentRepo;
import com.osiristher.webapp.dbtest.service.StudentTaskRepo;
import com.osiristher.webapp.dbtest.service.TaskRepo;
import com.osiristher.webapp.testingsystem.tester.OsiristherNative;
import com.osiristher.webapp.testingsystem.tester.TestHandler;
import com.osiristher.webapp.testingsystem.tester.TestResultHandler;
import com.osiristher.webapp.testingsystem.tester.fixtures.Result;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.LinkedList;
import java.util.Map;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private static TestResultHandler handler;
    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    public void init(
            StudentTaskRepo studentTaskRepo,
            StudentRepo studentRepo,
            TaskRepo taskRepo) {
        handler = new TestResultHandler();
        LinkedList<Result> resultsList = new LinkedList<>();
        handler.setAutowireds(studentTaskRepo,
                studentRepo,
                taskRepo);
        handler.setResultsList(resultsList);
        OsiristherNative.init(resultsList);
        OsiristherNative.getInstance().setHandler(handler);
    }

}
