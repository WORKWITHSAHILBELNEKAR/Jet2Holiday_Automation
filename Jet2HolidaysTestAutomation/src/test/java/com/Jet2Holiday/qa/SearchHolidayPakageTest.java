package com.Jet2Holiday.qa;

import com.Jet2Holidays.qa.pages.LoginPage;
import com.Jet2Holidays.qa.pages.PassengerPage;
import com.Jet2Holidays.qa.pages.SearchHolidayPackage;
import com.Jet2Holidays.qa.pages.SearchHolidays;
import com.Jet2Holidays.qa.util.WebDriverManager;
import com.jet2hildays.qa.util.ExcelFileReading;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class SearchHolidayPakageTest extends SearchHolidayPackage {

    LoginPage loginPage ;
    SearchHolidays searchHolidays;
    SearchHolidayPackage searchHolidayPackage;
    PassengerPage passengerPage;

    @BeforeMethod
    public void setUp() throws IOException {
        initialization();
        logger.info("SelectHolidayPackageTest Method Running....");
        loginPage = new LoginPage();
        searchHolidays= new SearchHolidays();
        searchHolidayPackage = new SearchHolidayPackage();
    }

    @Test(priority = 1)
    public void SelectHolidaySingleUser() throws InterruptedException {
        searchHolidays = loginPage.singleUserLogin();
        searchHolidayPackage = searchHolidays.searchHoliday();
        passengerPage = searchHolidayPackage.selectHolidayPackage();
    }


    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        WebDriverManager.closeDriver();
    }

}
