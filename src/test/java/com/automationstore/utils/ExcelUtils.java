package com.automationstore.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private static final String FILE_PATH = "src/test/resources/testdata/UserData.xlsx";

    // Helper to open the file and get a specific sheet
    private static Sheet getSheet(int sheetIndex) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        return workbook.getSheetAt(sheetIndex);
    }

    // 1. For Login (Sheet 0)
    public static String[] getLoginData(int rowIndex) throws IOException {
        Sheet sheet = getSheet(0); // LoginData is first sheet
        Row row = sheet.getRow(rowIndex);
        String username = row.getCell(0).getStringCellValue();
        String password = row.getCell(1).getStringCellValue();
        return new String[]{username, password};
    }

    // 2. For Search (Sheet 1)
    public static String getSearchTerm(int rowIndex) throws IOException {
        Sheet sheet = getSheet(1); // SearchData is second sheet
        Row row = sheet.getRow(rowIndex);
        return row.getCell(0).getStringCellValue();
    }

    // 3. For Contact Us (Sheet 2)
    public static String[] getContactData(int rowIndex) throws IOException {
        Sheet sheet = getSheet(2); // ContactData is third sheet
        Row row = sheet.getRow(rowIndex);
        String name = row.getCell(0).getStringCellValue();
        String email = row.getCell(1).getStringCellValue();
        String enquiry = row.getCell(2).getStringCellValue();
        return new String[]{name, email, enquiry};
    }

	public static String[] getCredentials(String string, int rowIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}