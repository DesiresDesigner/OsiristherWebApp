package com.osiristher.webapp.testingsystem.tester.fixtures.runner;

import com.osiristher.webapp.testingsystem.properties.Config;
import com.osiristher.webapp.testingsystem.tester.exceptions.ConfigException;
import com.osiristher.webapp.testingsystem.tester.fixtures.Result;

import java.io.*;
import java.util.Scanner;

/**
 * Created by DesiresDesigner on 26.02.15.
 */

public class CppRunner {

    public Result runWithFileInput(String execName, String setName)
            throws IOException, InterruptedException, ConfigException {
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "./run_cpp.sh " + ' ' +
                Config.getProp("BasePath") + '/' + Config.getProp("ResourcesPath") +
                    "/ExecFiles/" + execName + ' ' +
                Config.getProp("BasePath") + '/' + Config.getProp("TestingDataPath")
                    + '/' + setName + "/input/content.txt");
        pb.directory(new File(Config.getProp("BasePath") + '/' + Config.getProp("ScriptsPath")));
        Process p = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        String s, errors = "";
        while ((s = stdError.readLine()) != null) {
            System.out.println("Script error_output: " + s);
            errors += s;
        }


        if ("".equals(errors)) {
            Scanner programOutput = new Scanner(reader);
            Scanner correctOutput = new Scanner(new File(Config.getProp("BasePath") + '/' +
                    Config.getProp("TestingDataPath") + '/' + setName + "/output/content.txt"));
            return checkMatches(programOutput, correctOutput);
        }

        Result result = new Result();
        result.addError(0, "Errors(" + errors + ')', "result");
        result.setExitCode(3);
        return result;
    }

    private Result checkMatches(Scanner output, Scanner expected) {
        int valCNT = 0;
        Result r = new Result();
        r.setExecMem(0); // ToDO
        r.setExecTimeMS(0); // ToDo

        while (expected.hasNext()) {
            ++valCNT;
            if (!output.hasNext()) {
                r.setExitCode(1);
                r.addError(valCNT, "END OF OUTPUT", expected.next());
                return r;
            } else {
                int have = output.nextInt(), need = expected.nextInt();
                if (have != need) {
                    r.setExitCode(2);
                    r.addError(valCNT, Integer.toString(have), Integer.toString(need));
                }
            }
        }
        return r;
    }
}
