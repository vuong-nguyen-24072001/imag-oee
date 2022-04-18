package com.commplc.Utils;

import java.io.IOException;

public class Database {

    public static boolean backup(String dbUsername, String dbPassword, String dbName, String outputFile)
            throws IOException, InterruptedException {
        String command = String.format("mysqldump -u%s -p%s --add-drop-table --databases %s -r %s",
                dbUsername, dbPassword, dbName, outputFile);
//        for docker mysql
    //    String command = String.format("mysqldump --host mysqldb --port 3306 -u%s -p%s --add-drop-table --databases %s -r %s",
    //            dbUsername, dbPassword, dbName, outputFile);
        Process process = Runtime.getRuntime().exec(command);
        int processComplete = process.waitFor();
        return processComplete == 0;
    }

}
