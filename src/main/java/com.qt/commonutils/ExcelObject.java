package com.qt.commonutils;

import org.apache.poi.ss.usermodel.Workbook;


/**
 * This Class has all the Objects related to ExcelObject.
 *
 * @author
 */
public class ExcelObject {
    public String excelPath;
    public Workbook excelWorkbook;

    /**
     * Constructs a new ExcelObject.
     *
     * @param excelPath     The path to the Excel file.
     * @param excelWorkbook The workbook object representing the Excel file.
     */

    public ExcelObject(String excelPath, Workbook excelWorkbook) {
        this.excelPath = excelPath;
        this.excelWorkbook = excelWorkbook;
    }

    /**
     * Retrieves the Excel file path.
     *
     * @return The Excel file path.
     */
    public String getExcelPath() {
        return excelPath;
    }

    /**
     * Retrieves the Excel workbook.
     *
     * @return The Excel workbook.
     */
    public Workbook getExcelWorkbook() {
        return excelWorkbook;
    }
}
