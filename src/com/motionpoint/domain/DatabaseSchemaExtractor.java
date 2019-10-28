package com.motionpoint.domain;/*
 * com.motionpoint.domain.DatabaseSchemaExtractor.java
 *
 * Copyright (c) 2000-2019 MotionPoint Corporation. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * MotionPoint Corp. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with MotionPoint.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

public class DatabaseSchemaExtractor {

    public static List<String> getColumnNamesFromTable(Session aSession, final String aTableName) {

        return aSession.doReturningWork(new ReturningWork<List<String>>() {
            public List<String> execute(Connection connection) throws SQLException {
                List<String> columnNames = new ArrayList<String>();
                Statement stmt = null;

                try {
                    stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM " + aTableName);
                    ResultSetMetaData rsmd = rs.getMetaData();

                    int columnCount = rsmd.getColumnCount();

                    // The column count starts from 1
                    for (int i = 1; i <= columnCount; i++) {
                        String name = rsmd.getColumnName(i);
                        columnNames.add(name);
                    }
                    return columnNames;
                } catch (SQLException e) {
                    //log
                    return Collections.emptyList();
                } finally {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException e) {
                            // log
                            return columnNames;
                        }
                    }
                }
            }
        });

    }

    public static List<String> getColumnNamesFromTableJDBC(final String aTableName) {

        List<String> columnNames = new ArrayList<String>();
        Statement stmt = null;

        try {
            String databaseURL = "jdbc:sqlserver://localhost:1433;database=client1";
            String username = "controlpanel";
            String password = "controlpanel";
            Connection connection = DriverManager.getConnection(databaseURL, username, password);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + aTableName);
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnCount = rsmd.getColumnCount();

            // The column count starts from 1
            for (int i = 1; i <= columnCount; i++) {
                String name = rsmd.getColumnName(i);
                columnNames.add(name);
            }
            return columnNames;
        } catch (SQLException e) {
            //log
            return Collections.emptyList();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // log
                    return columnNames;
                }
            }
        }
    }


    public static boolean isColumnPresent(Session aSession, String aTableClass, String aColumnName) {
        List<String> columns = getColumnNamesFromTable(aSession, aTableClass);


        boolean found = false;
        int i = 0;
        while (i < columns.size() && !found) {

            if (columns.get(i++).equals(aColumnName)) {
                found = true;
                break;
            }
        }

        return found;
    }
}

