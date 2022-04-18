package com.commplc.Utils;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class WriteDataToExcel {

    public static boolean outputState = false;

    private static String excelDataPath = "./src/main/resources/DataExcel/Data.xlsx";

    private static FileInputStream inputStream = null;

    private static Workbook workbook = null;

    private static Sheet sheet = null;

    public static String writeDataExcel(HashMap<String, String>[] result) {
        try {
            outputState = false;
            if (inputStream == null) {
                inputStream = new FileInputStream(new File(excelDataPath));
                workbook = WorkbookFactory.create(inputStream);
                sheet = workbook.getSheetAt(0);
            }

            for (int i = 0; i < result.length; i++) {
                HashMap<String, String> hm = result[i];
                writeDataToTable(i + 1, sheet, hm);
            }

            FileOutputStream outputStream = new FileOutputStream(excelDataPath);
            System.out.println(outputStream);
            workbook.write(outputStream);
            System.out.println("write success");
            //workbook.close();
            outputStream.close();
            outputState = true;
            return "success";

        } catch (IOException | EncryptedDocumentException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    private static void writeDataToTable(int i, Sheet sheet, HashMap<String, String> hm) {
        Row row = sheet.getRow(sheet.getFirstRowNum() + i);
        Cell cell = row.getCell(0);
        cell.setCellValue(hm.get("line"));
        cell = row.getCell(1);
        cell.setCellValue(hm.get("status"));
        cell = row.getCell(3);
        cell.setCellValue(hm.get("counterOut"));
        cell = row.getCell(4);
        cell.setCellValue(hm.get("speed"));
        cell = row.getCell(6);
        cell.setCellValue(hm.get("runtime"));
    }


}
