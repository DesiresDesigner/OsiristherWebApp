package com.osiristher.webapp.testingsystem.tester.fixtures;

/**
 * Created by DesiresDesigner on 9.05.16.
 */

import com.osiristher.webapp.testingsystem.properties.Config;
import com.osiristher.webapp.testingsystem.tester.exceptions.ConfigException;
import fitlibrary.DoFixture;
import com.osiristher.webapp.testingsystem.tester.fixtures.runner.CppRunner;

import java.io.*;

public class SimpleFixture extends DoFixture {
    String fileName;
    Long taskId;
    String testGroup;

    public void setFileName(String name){
        fileName = name;
    }

    public void setTaskId(Long taskId){
        this.taskId = taskId;
    }

    public void setTestGroup(String testGroup){
        this.testGroup = testGroup;
    }

    public boolean testFromSet(String setName){
        try {
            CppRunner cr = new CppRunner();
            //System.out.println("FROM FIXTURE: lol");
            Result r = cr.runWithFileInput(taskId + "/" + fileName, setName);

            writeLogs(r);

            if (r.getExitCode() == 0)
                return true;
            else
                return false; // ToDo: logging module instead System.out
        }  catch (IOException e) {
            System.out.println("2, Fixture I/O Exception:");
            e.printStackTrace();
            return false;
        } catch (Exception e){
            System.out.println("2, Fixture Exception:");
            e.printStackTrace();
            return false;
        }
    }

    private void writeLogs(Result r) throws IOException, ConfigException {
        File f = new File(Config.getProp("BasePath") + "/" + Config.getProp("ResourcesPath") + "/Logs/" + fileName + ".log");
        if (!f.exists())
            f.createNewFile();

        FileWriter fileWritter = new FileWriter(f.getAbsolutePath(), true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(r.getExitCode() + "\n");
        bufferWritter.write(r.getExecTimeMS() + "\n");
        bufferWritter.write(r.getExecMem() + "\n");
        while(r.haveError())
            bufferWritter.write(r.getNextError() + "\n");
        bufferWritter.write("END\n");
        bufferWritter.close();
    }

}
