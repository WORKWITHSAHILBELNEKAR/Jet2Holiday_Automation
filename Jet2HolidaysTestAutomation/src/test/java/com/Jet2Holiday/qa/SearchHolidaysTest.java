package com.Jet2Holiday.qa;

import com.Jet2Holidays.qa.pages.LoginPage;
import com.Jet2Holidays.qa.pages.SearchHolidayPackage;
import com.Jet2Holidays.qa.pages.SearchHolidays;
import com.Jet2Holidays.qa.util.WebDriverManager;
import com.jet2hildays.qa.util.ExcelFileReading;
import junit.framework.TestListener;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;


//@Listeners(TestListener.class)
public class SearchHolidaysTest extends SearchHolidays {

    LoginPage loginPage;
    SearchHolidays searchHolidays ;
    SearchHolidayPackage searchHolidayPackage;
    @BeforeMethod
    public void setup () throws IOException {
        initialization();
        logger.info("SearchHolidayTest Method Running....");
        loginPage =new LoginPage();
        SearchHolidays SearchHolidays = new SearchHolidays();

    }
    @Test(priority = 1)
    public void SearchHolidaySingleUser() throws InterruptedException {
            searchHolidays = loginPage.singleUserLogin();
            searchHolidayPackage = searchHolidays.searchHoliday();
    }


    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        WebDriverManager.closeDriver();
    }


}
