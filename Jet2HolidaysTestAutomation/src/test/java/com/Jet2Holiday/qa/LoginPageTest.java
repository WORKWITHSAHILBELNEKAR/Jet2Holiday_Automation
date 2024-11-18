package com.Jet2Holiday.qa;

import com.Jet2Holidays.qa.base.TestBase;
import com.Jet2Holidays.qa.pages.LoginPage;
import com.Jet2Holidays.qa.pages.SearchHolidayPackage;
import com.Jet2Holidays.qa.pages.SearchHolidays;
import com.jet2hildays.qa.util.ExcelFileReading;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginPageTest extends TestBase {
    LoginPage loginPage;
    SearchHolidays searchHolidays;
    String sheetname = "LoginDetails";

    public LoginPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() throws IOException {
        initialization();
        loginPage = new LoginPage();

        // Call the method to accept cookies before proceeding to login
        loginPage.clickCookiePage();
    }

    @Test(priority = 1)
    public void loginPagetTitleTest() {
        String title = loginPage.validateLoginPage();
        System.out.println(title);
        Assert.assertEquals(title, "All Inclusive Holidays and Package Holidays | Jet2holidays");
    }

    @Test(priority = 2)
    public void loginTest() {
        searchHolidays = loginPage.login(prop.getProperty("emailId"), prop.getProperty("pass"));
    }

    @Test(priority = 3, dataProvider = "LoginData", dataProviderClass = ExcelFileReading.class)
    public void LoginWithMultipleUser(String emails, String password) throws InterruptedException {
        searchHolidays = loginPage.multipleUserLogin(emails, password);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    public void getTitles(){
        String title = loginPage.getTitle();
        System.out.println(title);
        Assert.assertEquals(title, "All Inclusive Holidays and Package Holidays | Jet2holidays");
    }
}
