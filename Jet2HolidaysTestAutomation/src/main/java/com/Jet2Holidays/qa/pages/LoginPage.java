package com.Jet2Holidays.qa.pages;

import com.Jet2Holidays.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class LoginPage extends TestBase {

    public LoginPage() throws IOException {
        PageFactory.initElements(driver, this);
    }

    public String email = prop.getProperty("emailId");
    public String pwd = prop.getProperty("pass");

    // Page factory - object repository
    @FindBy(xpath = "//*[@id='onetrust-accept-btn-handler']")
    WebElement cookieHandler;

    @FindBy(xpath = "//a[@class='bttn']")
    WebElement clickLoginBtn;

    @FindBy(xpath = "//input[@id='email']")
    WebElement emailId;

    @FindBy(xpath = "//*[@id='continue']")
    WebElement clickLoginContinue;

    @FindBy(xpath = "//*[@id='password']")
    WebElement passwords;

    @FindBy(xpath = "//*[@id='next']")
    WebElement clickNext;

    // Actions
    public void clickCookiePage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(cookieHandler)).click();
        } catch (Exception e) {
            System.out.println("Cookie acceptance button not interactable: " + e.getMessage());
        }
    }

    public String validateLoginPage() {
        return driver.getTitle();
    }

    public void clickLoginButton() {
        clickLoginBtn.click();
    }

    public SearchHolidays login(String emId, String pass) {
        clickLoginBtn.click();
        emailId.sendKeys(emId);
        clickLoginContinue.click();
        passwords.sendKeys(pass);
        clickNext.click();
        return new SearchHolidays();
    }

    public void enterEmailId(String emails) {
        emailId.sendKeys(emails);
    }

    public void clickLoginContinueButton() {
        clickLoginContinue.click();
    }

    public void enterPassword(String pass) {
        passwords.sendKeys(pass);
    }

    public void clickNextButton() {
        clickNext.click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public SearchHolidays singleUserLogin() throws InterruptedException {
        clickCookiePage();
        clickLoginButton();
        enterEmailId(email);
        clickLoginContinueButton();
        enterPassword(pwd);
        clickNextButton();
        return new SearchHolidays();
    }

    public SearchHolidays multipleUserLogin(String emails, String password) throws InterruptedException {
        clickCookiePage();
        clickLoginButton();
        enterEmailId(emails);
        clickLoginContinueButton();
        enterPassword(password);
        clickNextButton();
        return new SearchHolidays();
    }
}
