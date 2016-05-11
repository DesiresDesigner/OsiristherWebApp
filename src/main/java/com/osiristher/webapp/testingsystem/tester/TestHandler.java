package com.osiristher.webapp.testingsystem.tester;

import com.osiristher.webapp.testingsystem.tester.fixtures.Result;
import com.osiristher.webapp.testingsystem.tester.interfaces.Handleable;

import java.util.LinkedList;

/**
 * Created by DesiresDesigner on 17.02.15.
 */
public class TestHandler implements Handleable {
    LinkedList<Result> resultsList;

    public void setResultsList(LinkedList<Result> resultsList){
        this.resultsList = resultsList;
    }

    @Override
    synchronized public void handle() {
        resultsList.removeLast().print();
    }
}
