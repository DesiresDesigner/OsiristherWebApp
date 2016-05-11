package com.osiristher.webapp.testingsystem.tester;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.osiristher.webapp.testingsystem.tester.codes.Language;
import com.osiristher.webapp.testingsystem.tester.fixtures.Result;
import com.osiristher.webapp.testingsystem.tester.interfaces.Handleable;

/**
 * Created by DesiresDesigner on 16.02.15.
 */


public class OsiristherNative {
    static {
        instance = new OsiristherNative();
    }

    private static volatile OsiristherNative instance;
    private OsiristherNative() {
        instance = null;
    }

    public static synchronized OsiristherNative getInstance() {
        return instance;
    }

    public static  OsiristherNative init(LinkedList<Result> resultsList) {
        instance.resultsList = resultsList;
        return instance;
    }


    private LinkedList<Result> resultsList;
    private ExecutorService executor = Executors.newFixedThreadPool(5);

    boolean handlerIsPresent = false;
    Handleable handler;

    public void setHandler(Handleable handler){
        this.handler = handler;
        handlerIsPresent = true;
    }

    public void testSource(long userID, long taskID, String source, Language lang){
        Examiner examiner = new Examiner(userID, taskID, source, lang, resultsList);

        if (handlerIsPresent)
            examiner.setHandler(handler);

        executor.execute(examiner);
    }

    public void free(){
        executor.shutdown();
        //while (!executor.isTerminated()){}
    }

    public void freeNow(){
        executor.shutdownNow();
        //while (!executor.isTerminated()){}
    }
}
