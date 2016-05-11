package com.osiristher.webapp.testingsystem.tester;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.osiristher.webapp.testingsystem.properties.Config;
import com.osiristher.webapp.testingsystem.tester.entities.IntFLG;
import com.osiristher.webapp.testingsystem.tester.exceptions.ConfigException;
import com.osiristher.webapp.testingsystem.tester.fixtures.Result;
import com.osiristher.webapp.testingsystem.tester.interfaces.Handleable;
import com.osiristher.webapp.testingsystem.tester.codes.Language;

/**
 * Created by desiresdesigner on 16.02.15.
 */
public class Examiner implements  Runnable {
    private CompilerCaller cc;
    private TesterCaller tc;

    private String fullFileName;
    private String dirShortName;
    private long userID;
    private long taskID;
    private String source;
    private Language lang;
    private LinkedList<Result> resultsList;

    private boolean needToBeProcessed = false;
    private Handleable handler;
    private Result result;


    Examiner(long userID, long taskID, String source, Language lang, LinkedList<Result> resultsList){
        cc = new CompilerCaller();
        tc = new TesterCaller();
        result = new Result();
        result.setUserId(userID);
        result.setTaskId(taskID);

        this.userID = userID;
        this.taskID = taskID;
        this.source = source;
        this.lang = lang;
        this.resultsList = resultsList;
        dirShortName = Long.toString(taskID);
    }

    public void setHandler(Handleable handler){
        this.handler = handler;
        needToBeProcessed = true;
    }

    private String saveSource() throws IOException, ConfigException {
        fullFileName = Long.toString(userID) + "_" + Long.toString(taskID) + "_" + Long.toString(System.currentTimeMillis() / 1000L);
        //String dirPath = "src/main/resources/SourceCode/" + Integer.toString(taskID);
        String dirPath = Config.getProp("BasePath") + "/src/main/resources/SourceCode/" + Long.toString(taskID);
        switch (lang) {
            case CPP:
                fullFileName += ".cc";
                break;
            case JAVA:
                fullFileName += ".java";
                break;
            default:
                fullFileName += ".cc";
                break;
        }

        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(dirPath + '/' + fullFileName, false));
        writer.write(source);
        writer.close();

        return fullFileName;
    }

    @Override
    public void run() {
        try {
        saveSource();
        } catch (Exception e) {
            String name = e.getClass().getSimpleName();
            if (name.equals("IOException")) {
                sendResultMessage(2, 1, "I/O problems while saving source");
            } else if (name.equals("ConfigException")) {
                sendResultMessage(5, 1, "I/O problems while opening config");
            }
            return;
        }

        String execToTest = "";
        try {
            execToTest = cc.compile(fullFileName, dirShortName);
        } catch (Exception e){
            String name = e.getClass().getSimpleName();
            if (name.equals("IOException")) {
                sendResultMessage(1, 3, "I/O problems when calling gcc");

            } else if (name.equals("UnknownLanguageException")) {
                sendResultMessage(1, 1, "Unknown extension of source file - " + e.getMessage());

            } else if (name.equals("LackOfExpansionException")) {
                sendResultMessage(1, 2, "no extension of source file");

            } else if (name.equals("GCCException")) {
                sendResultMessage(0, 1, "gcc compile error - " + e.toString());
            }
            return;
        }

        try {
            IntFLG exceptionsFLG = new IntFLG();
            result.copy(tc.testExec(execToTest, taskID, exceptionsFLG));
            int errorCode = 0;
            if (exceptionsFLG.getFlg() > 0)
                errorCode = 4;
            if (result.getCorrectnes() == 100.00)
                sendResultMessage(errorCode, 0, "100% passed, E:" + exceptionsFLG.getFlg());
            else {
                sendResultMessage(errorCode, 2, result.getCorrectnes() + "% passed, E:" + exceptionsFLG.getFlg());
            }
        } catch (IOException e) {
            sendResultMessage(3, 1, "I/O problems when calling Tester");
        } catch (ConfigException e){
            sendResultMessage(5, 1, "I/O problems while opening config");
        }
    }

    private void sendResultMessage(int moduleID, int errorID, String message){
        String res = Integer.toString(moduleID) + '.' + Integer.toString(errorID) +
                '.' + Long.toString(userID) + '.' + Long.toString(taskID) + ": " + message;
        result.addError(res);
        //putResult(res);
        putResult(result);
    }

    private synchronized void putResult(Result result){
        resultsList.addFirst(result);

        if (needToBeProcessed)
            handler.handle();
    }



}