package com.Jet2Holidays.qa.base;

import com.Jet2Holidays.qa.util.TestUtill;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

public class TestBase {
    public static Logger logger = Logger.getLogger(TestBase.class);
    public static WebDriver driver;
    public static Properties prop;

    public TestBase() {
        PropertyConfigurator.configure("C:\\Users\\sahil\\OneDrive\\Desktop\\Jet2Holidays\\Jet2HolidaysTestAutomation\\resources\\log4j.properties");
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("C:\\Users\\sahil\\OneDrive\\Desktop\\Jet2Holidays\\Jet2HolidaysTestAutomation" +
                    "\\src\\main\\java\\com\\Jet2Holidays\\qa\\config\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization() {
        logger.info("Browser name : "+ prop.getProperty("browser"));
        String browserName = prop.getProperty("browser");
        if (browserName.equals("chrome")) {
            // Create ChromeOptions instance
            ChromeOptions options = new ChromeOptions();

            // Disable notification pop-up
            options.addArguments("--disable-notifications");

            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtill.PAGE_LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtill.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(prop.getProperty("url"));
    }

}
