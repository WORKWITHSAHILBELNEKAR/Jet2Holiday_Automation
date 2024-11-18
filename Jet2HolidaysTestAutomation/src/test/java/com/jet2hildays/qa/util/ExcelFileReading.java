package com.jet2hildays.qa.util;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelFileReading {
    public static FileInputStream fi;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static String link = "C:\\Users\\sahil\\OneDrive\\Desktop\\Jet2Holidays\\Jet2HolidaysTestAutomation" +
            "\\src\\main\\java\\com\\Jet2Holidays\\qa\\testdata\\jet2holidays_multidata.xlsx"; // Adjusted path for accessibility

    public static String[][] getData(String sheetName) throws IOException {
        fi = new FileInputStream(link);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);

        int totalRows = ws.getLastRowNum();
        int totalCols = ws.getRow(0).getLastCellNum();
        String[][] loginData = new String[totalRows][totalCols];

        DataFormatter formatter = new DataFormatter();
        for (int i = 1; i <= totalRows; i++) {
            row = ws.getRow(i);
            for (int j = 0; j < totalCols; j++) {
                cell = row.getCell(j);
                loginData[i - 1][j] = formatter.formatCellValue(cell);
            }
        }

        wb.close();
        fi.close();
        return loginData;
    }
    @DataProvider(name = "CombinedData")
    public Object[][] combinedData() throws IOException {
        String[][] loginData = getData("LoginDetails");
        String[][] passengerDetails = getData("PassengerDetails");

        int totalRows = Math.min(loginData.length,passengerDetails.length);
        int totalColumns = loginData[0].length + passengerDetails[0].length;

        Object[][] combinedData = new Object[totalRows][totalColumns];

        for (int i = 0; i < totalRows; i++) {
            int columnIndex = 0;
            for (int j = 0; j < loginData[i].length; j++) {
                combinedData[i][columnIndex++] = loginData[i][j];
            }
            for (int j = 0; j < passengerDetails[i].length; j++) {
                combinedData[i][columnIndex++] = passengerDetails[i][j];
            }
        }

        return combinedData;
    }

    @DataProvider(name="PassengerDetailsWithoutLogin")
    public static Object[][] PassengerDetailsWithoutLogin() throws IOException {
        String[][] PassengersDetails = getData("PassengerDetails");
        int totalRows = PassengersDetails.length;
        int totalColumns = PassengersDetails[0].length;

        int columnIndex = 2;
        int startIndex = totalColumns-columnIndex;
        Object[][] PassengerData = new Object[totalRows][totalColumns];
        for(int i=0;i<totalRows;i++){

            for(int j=0;j<PassengerData[i].length;j++){
                PassengerData[i][startIndex++] = PassengersDetails[i][j];
            }
        }
        return PassengersDetails;
    }

    @DataProvider(name = "LoginData")
    public static Object[][] loginData() throws IOException {
        return getData("LoginDetails");
    }

    @DataProvider(name = "PassengerDetails")
    public static Object[][] passengerDetails() throws IOException {
        return getData("PassengerDetails");
    }

    @DataProvider(name = "UserDetails")
    public static Object[][] userDetails() throws IOException {
        return getData("UserDetails");
    }

}
