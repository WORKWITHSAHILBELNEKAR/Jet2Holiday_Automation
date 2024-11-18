package com.Jet2Holiday.qa;

import com.Jet2Holidays.qa.base.TestBase;
import com.Jet2Holidays.qa.pages.*;
import com.Jet2Holidays.qa.util.WebDriverManager;
import com.jet2hildays.qa.util.ExcelFileReading;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.*;

import java.io.IOException;
public class PassengerPageTest extends PassengerPage {

    LoginPage loginPage;
    SearchHolidays searchHolidays;
    SearchHolidayPackage searchHolidayPackage;
    PassengerPage passengerPage;
    PaymentPage paymentPage;

    @BeforeMethod
    public void setUp() throws IOException {
        initialization();
        logger.info("PassengerPageTest Method Running....");
        loginPage = new LoginPage();
        searchHolidays = new SearchHolidays();
        searchHolidayPackage = new SearchHolidayPackage();
        passengerPage = new PassengerPage();
    }


    @Test(priority = 1,dataProvider = "PassengerDetails", dataProviderClass = ExcelFileReading.class)
//    @Description("Add passenger details successfully")
//    @Epic("EP001")
//    @Feature("Feature1 : Add Passenger Details")
//    @Story("Story : Add passengers details ")
//    @Step("Add passenger details with single user")
//    @Severity(SeverityLevel.NORMAL)
    public void PassengerDetailsSingleUser(String user,String pass,String fname,String lname,String date,String month,String year) throws InterruptedException {
        searchHolidays = loginPage.multipleUserLogin(user,pass);
        searchHolidayPackage = searchHolidays.searchHoliday();
        passengerPage = searchHolidayPackage.selectHolidayPackage();
        paymentPage = passengerPage.addPassengerDetails(fname,lname,date,month,year);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        WebDriverManager.closeDriver();
    }

}
