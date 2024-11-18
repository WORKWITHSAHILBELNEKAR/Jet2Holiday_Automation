package com.Jet2Holiday.qa;


import com.Jet2Holidays.qa.pages.*;
import com.Jet2Holidays.qa.util.WebDriverManager;
import com.jet2hildays.qa.util.ExcelFileReading;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

//@Listeners(TestListners.class)
public class PaymentTest extends PaymentPage {
    LoginPage loginPage;
    SearchHolidays searchHoliday;
    SearchHolidayPackage selectHolidayPackage;
    PassengerPage passengerDetails;
    PaymentPage paymentPage;

    @BeforeMethod
    public void setUp() throws IOException {
        initialization();
        logger.info("PaymentTest Method Running....");
        loginPage = new LoginPage();
        searchHoliday = new SearchHolidays();
        selectHolidayPackage = new SearchHolidayPackage();
        passengerDetails = new PassengerPage();
    }


    @Test(priority = 1,dataProvider = "PassengerDetails", dataProviderClass = ExcelFileReading.class)
    @Description("Verify payment successfully")
    @Epic("EP001")
    @Feature("Feature1 : Payment details")
    @Story("Story : verify payment ")
    @Step("Add payment details with single user")
    @Severity(SeverityLevel.CRITICAL)
    public void Payment(String user,String pass,String fname,String lname,String date,String month,String year,String countrys,String adds,String citys,String nos,String emails,String cnos,String cnames,String cmonths,String cyears,String cvvs) throws InterruptedException {
        searchHoliday = loginPage.singleUserLogin();
        selectHolidayPackage = searchHoliday.searchHoliday();
        passengerDetails = selectHolidayPackage.selectHolidayPackage();
        paymentPage = passengerDetails.addPassengerDetails(fname,lname,date,month,year);
        paymentPage = paymentPage.setPayment(countrys,adds,citys,nos,emails,cnos,cnames,cmonths,cyears,cvvs);
    }


    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        WebDriverManager.closeDriver();
    }


}

