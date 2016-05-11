package com.osiristher.webapp.testingsystem.tester;

import com.osiristher.webapp.testingsystem.properties.Config;
import com.osiristher.webapp.testingsystem.tester.entities.IntFLG;
import com.osiristher.webapp.testingsystem.tester.exceptions.ConfigException;
import com.osiristher.webapp.testingsystem.tester.exceptions.FixtureException;
import com.osiristher.webapp.testingsystem.tester.fixtures.Result;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DesiresDesigner on 17.02.15.
 */

public class TesterCaller {
    private float passedPercent = 0;

    public Result testExec(String execName, long taskID, IntFLG exceptionsFlag) throws IOException, ConfigException {
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "java -DEXEC='" + execName +
                "' -jar " + Config.getProp("FitNessePath") + "/fitnesse-standalone.jar -d " + Config.getProp("BasePath") + " -c 'suites." + taskID + "?suite&format=text'");
        pb.directory(new File(Config.getProp("BasePath")));
        System.out.println("before start");
        Process p = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        //BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        String s;
        while ((s = reader.readLine()) != null) {
            try {
                float curRes = parseLine(s);
                if (curRes > -1) {
                    if (passedPercent == 0)
                        passedPercent = parseLine(s);
                    else
                        passedPercent = (passedPercent + parseLine(s)) / 2;
                }
            } catch (FixtureException e) {
                exceptionsFlag.incFLG(); // ToDo: logging of caught exceptions
            }
        }

        /*String errors = "";
        while ((s = stdError.readLine()) != null) {
            System.out.println("Script error_output: " + s);
            errors += s;
        }*/
        return formResult(execName);
        //return passedPercent;
    }

    private float parseLine(String line) throws FixtureException {
        if (line.startsWith(".")){
            return 100;
        } else if (line.startsWith("F")){
            Pattern pattern = Pattern.compile("R:([0-9]*)");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            double right = Integer.parseInt(matcher.group(1));

            pattern = Pattern.compile("W:([0-9]*)");
            matcher = pattern.matcher(line);
            matcher.find();
            double wrong = Integer.parseInt(matcher.group(1));
            return Float.parseFloat(new DecimalFormat("##.##").format((right / (wrong + right) * 100)));
        } else if (line.startsWith("X")){
            throw new FixtureException();
        } else
            return -1;
    }

    private Result formResult(String execName) throws ConfigException, FileNotFoundException {
        Result r = new Result();

        r.setCorrectnes(passedPercent);

        File file = new File(Config.getProp("BasePath") + "/" + Config.getProp("ResourcesPath") + "/Logs/" + execName + ".log");
        Scanner log = new Scanner(file);
        while(log.hasNext()) {
            r.setExitCode(log.nextInt()); //Integer.parseInt(log.nextLine())
            r.setExecTimeMS(log.nextInt());
            r.setExecMem(log.nextInt());
            String line = log.nextLine();
            while (!"END".equals(line)) {
                r.addError(line);
                line = log.nextLine();
            }
        }
        file.delete();
        return r;
    }
}
