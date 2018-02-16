/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lfa.app.core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

/**
 *
 * @author machnet1
 */
public class DBConnect {
    private static final Logger logger = Logger.getLogger(DBConnect.class);
    private static Connection con = null;
    private static String dbName = PropertyLoader.dbName;
    private static String dbUser = PropertyLoader.dbUser;
    private static String dbPass = PropertyLoader.dbPass;

    public Connection connect() {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName + "?autoReconnect=true", dbUser, dbPass);

                if (!con.isClosed()) {
                  logger.info("Mysql Connected Successfully..");
                }
            } catch (Exception e) {
                  logger.error("Mysql not Connected.. : " + e.getMessage());
                e.printStackTrace();
            }
        }
        return con;
    }
}
