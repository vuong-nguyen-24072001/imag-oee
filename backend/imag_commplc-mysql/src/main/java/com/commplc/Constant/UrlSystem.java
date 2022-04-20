package com.commplc.Constant;

import java.time.LocalDate;

import com.commplc.Service.RealtimeDataPLC;

public class UrlSystem {
    
    //for docker
    // public static final String  excelDataPath = "./src/main/resources/DataExcel/Data.xlsx";

    // public static final String pathDataHistory = "./src/main/resources/DataExcel/DataHistory/OEE_" + LocalDate.now() + "_" + RealtimeDataPLC.shift.replace(" ", "-") + ".sql";

    public static final String  excelDataPath = "./backend/imag_commplc-mysql/src/main/resources/DataExcel/Data.xlsx";

    public static String pathDataHistory;

    public static void setPathDataHistory(String line) {
        pathDataHistory = "./backend/imag_commplc-mysql/src/main/resources/DataExcel/DataHistory/OEE_" + LocalDate.now() + "_" + RealtimeDataPLC.shift.replace(" ", "-") + "-" + line + ".sql";
    }

}
