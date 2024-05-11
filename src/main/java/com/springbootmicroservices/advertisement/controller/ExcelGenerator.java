package com.springbootmicroservices.advertisement.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelGenerator {

    public static void main(String[] args) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet 1");

            String[][] data = {
                    {"Market:", "Lesotho", "COMMENTS - Please add additional information to support any changes/discrepancies", "Evidence", "Vodacom Group Questions."},
                    {"Month/Year:", "Aug-23"},
                    {"AMLC03: Customer verification"},
                    {"Sample check: New customers registered this month: No. of records which were retrievable + compliant:", "", "", "", ""},
                    {"25 x personal customers", "25 out of 25", "", "", "Evidence attached in line 5. look for the sheet name \"Newly registered Orgs\""},
                    // ... (Continue adding your data rows)
                    {"Suspicious Activity Reports (SARs):"},
                    {"No. of reports received via internal reporting channels this month:", "0"},
                    {"No. of transaction monitoring/Watchlist screening cases escalated to MLRO for review this month:", "0"},
                    {"No. of external SARs reported this month:", "0"},
                    {" - out of which how many originating from automated monitoring", "0"},
                    {" - out of which how many originating from manual monitoring", "0"},
                    {" - out of which how many originating from other", "0"},
                    {"No. of customers that have been a subject of the SAR's reported this month", "0"},
                    {"No of customers exited from the business", "0"},
                    {"No of customers placed under enhanced monitoring ", "232", "these are high risk customers (PEPs and SIPs) and as per our Policies they are placed under enhanced monitoring."},
                    {"Politically Exposed Persons (PEPs)"},
                    {"Number of PEPs approved for customer accounts this month", "0"},
                    {"Number of PEPs declined for customer accounts this month", "0"},
                    {"Total number of PEPs on PEP register", "214", "PEP Reg Attached"},
                    {"Sample check PEP approval process"},
                    {"3x PEPs approved in the reporting month", "3 out of 3"},
                    {"Special Interest Person (SIPs)"},
                    {"Number of SIPs approved for customer accounts this month", "0"},
                    {"Number of SIPs declined for customer accounts this month", "0"},
                    {"Total number of SIPs on SIPs register", "18", "SIP reg attached."},
                    {"Sample check SIPs approval process"},
                    {"3x SIPs approved in the reporting month", "3 out of 3"}
            };

            int rowCount = 0;
            for (String[] rowData : data) {
                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;
                for (String cellData : rowData) {
                    Cell cell = row.createCell(columnCount++);
                    cell.setCellValue(cellData);

                    // Apply styles to cells based on conditions (you can modify as per your requirements)
                    CellStyle style = workbook.createCellStyle();
                    if (cellData.contains("25 out of 25")) {
                        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        Font font = workbook.createFont();
                        font.setBold(true);
                        font.setColor(IndexedColors.WHITE.getIndex());
                        style.setFont(font);
                    } else if (cellData.contains("PEP")) {
                        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    }

                    // Apply bold font and font size 15 to titles
                    if (rowCount <= 2) { // Assuming titles are in the first two rows
                        Font titleFont = workbook.createFont();
                        titleFont.setBold(true);
                        titleFont.setFontHeightInPoints((short) 25); // Set font size to 15
                        style.setFont(titleFont);
                    }

                    cell.setCellStyle(style);
                }
            }
            String filePath = "ExcelFile.xlsx"; // Adjust the file name here

            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            // Print the file path
            System.out.println("Excel file generated successfully at: " + System.getProperty("user.dir") + "\\" + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
