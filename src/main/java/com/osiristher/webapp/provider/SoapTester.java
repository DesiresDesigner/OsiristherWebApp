package com.osiristher.webapp.provider;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Created by DesiresDesigner on 11/9/15.
 */
public class SoapTester {
    public static void main(String[] args) {
        /*BasicConfigurator.configure();

        List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());
        loggers.add(LogManager.getRootLogger());
        for ( Logger logger : loggers ) {
            logger.setLevel(Level.DEBUG);
        }*/

        try {

            UserInfo user = new UserManager().getUserInfo("boaboa", "test");
            //System.out.println(user.getMailAddress());
            System.out.println(user.getRole());

        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

}
