/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lfa.app.core.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author machnet1
 */
public class PropertyLoader {
    private static Logger logger = Logger.getLogger(PropertyLoader.class);
    public static Properties properties = new Properties();
    public static String dbName = null;
    public static String dbUser = null;
    public static String dbPass = null;
    public static String smtpHost = null;
    public static String smtpPort = null;
    public static String smtpUser = null;
    public static String smtpPass = null;

    public static String getOsName() {
        return System.getProperty("os.name", "unknown");
    }

    public static boolean isWindows() {
        return (getOsName().toLowerCase().indexOf("windows") >= 0);
    }

    public static boolean isLinux() {
        return getOsName().toLowerCase().indexOf("linux") >= 0;
    }

    public PropertyLoader() {
        try {
            String path = null;
            if (isLinux()) {
                path = "/home/bulk.properties";
                logger.info("Linux System, properties file is in /home/bulk.properties");
            } else if (isWindows()) {
                path = "C:\\bulk.properties";
                logger.info("Windows System, properties file is in C:/bulk.properties");
            }
            InputStream inputStream = new FileInputStream(path);
            properties.load(inputStream);
            getDbProperties();
            getSMTPDetails();
            logger.info("Property file loaded Successfully..");

        } catch (IOException e) {
            logger.error("Error occurred while reading the properties file");
            e.printStackTrace();
        }
    }

    private void getSMTPDetails() {
        try {
            smtpHost = properties.getProperty("smtp.host");
            smtpPort = properties.getProperty("smtp.port");
            smtpUser = properties.getProperty("smtp.user");
            smtpPass = properties.getProperty("smtp.pass");
        } catch (Exception e) {
        }
    }

    private void getDbProperties() {
        try {
            dbUser = properties.getProperty("db.user");
            dbPass = properties.getProperty("db.pass");
            dbName = properties.getProperty("db.name");
        } catch (Exception e) {
        }
    }
}
