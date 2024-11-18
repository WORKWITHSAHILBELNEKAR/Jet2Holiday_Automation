package com.Jet2Holidays.qa.pages;

import com.Jet2Holidays.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SearchHolidays extends TestBase {

    public SearchHolidays(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//button[@class='search-box-group__link'])[1]")
    WebElement clickDepartingListIcon;

    @FindBy(xpath = "//span[normalize-space()='Manchester']")
    WebElement selectDepartureAirport;

    @FindBy(xpath = "//span[normalize-space()='Done']")
    WebElement clickDepartureDone;

    @FindBy(xpath = "//span[@class='checkbox-button-group__label-text'][normalize-space()='Rome']")
    WebElement selectDestination;

    @FindBy(xpath = "(//a[@class='bttn bttn--primary bttn--3d button--base'])[1]")
    WebElement clickDestinationDone;

    @FindBy(xpath = "//input[@id='search-box-guests']")
    WebElement clickGuestInput;

    @FindBy(xpath = "//button[@data-gal='remove-adult-from-room-1']")
    WebElement selectGuestAndRoom;

    @FindBy(xpath = "//a[@data-gal='finished-guest-selection']")
    WebElement clickGuestDone;

    @FindBy(xpath = "//span[text()='Search holidays']")
    WebElement clickSearchHoliday;

    public void setClickDepartingListIconButton() {
        waitForElementToBeClickable(clickDepartingListIcon).click();
    }

    public void setSelectDepartureAirport() {
        waitForElementToBeClickable(selectDepartureAirport).click();
    }

    public void setClickDepartureDone() {
        waitForElementToBeClickable(clickDepartureDone).click();
    }

    public void setSelectDestination() {
        waitForElementToBeClickable(selectDestination).click();
    }

    public void setClickDestinationDone() {
        waitForElementToBeClickable(clickDestinationDone).click();
    }


    public void setClickGuestInput() {
        waitForElementToBeClickable(clickGuestInput).click();
    }

    public void setSelectGuestAndRoom() {
        waitForElementToBeClickable(selectGuestAndRoom).click();
    }

    public void setClickGuestDone() {
        waitForElementToBeClickable(clickGuestDone).click();
    }

    public void setClickSearchHoliday() {
        waitForElementToBeClickable(clickSearchHoliday).click();
    }

    public void CookiesHandler(WebDriver driver) {
        try {
            WebElement cookieHandler = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='onetrust-accept-btn-handler']")));
            cookieHandler.click();
        } catch (Exception e) {
            System.out.println("No cookie banner found.");
        }
    }

    public void SelectDayMonthYear(WebDriver driver, String month, String year, String date) {
        Select drpMonth = new Select(waitForElementToBeVisible(driver.findElement(By.xpath("//select[@id='duration-month']"))));
        drpMonth.selectByVisibleText(month);

        Select drpYear = new Select(waitForElementToBeVisible(driver.findElement(By.xpath("//select[@id='duration-year']"))));
        drpYear.selectByVisibleText(year);

        List<WebElement> drpDateEle = driver.findElements(By.xpath("//li[contains(@class,'js-day')]//div//div"));
        for (WebElement dt : drpDateEle) {
            if (dt.getText().equals(date)) {
                waitForElementToBeClickable(dt).click();
                break;
            }
        }
    }

    public void SelectNights(WebDriver driver, String day) {
        List<WebElement> drpDays = driver.findElements(By.xpath("//select[@id='search-box-nights']//option"));
        for (WebElement dy : drpDays) {
            if (dy.getText().equals(day)) {
                waitForElementToBeClickable(dy).click();
                break;
            }
        }
    }

    public SearchHolidayPackage searchHoliday() throws InterruptedException {
        try {
            setClickDepartingListIconButton();
        } catch (StaleElementReferenceException e) {
            setClickDepartingListIconButton();
        }

        setSelectDepartureAirport();
        Thread.sleep(2000);
        setClickDepartureDone();
        Thread.sleep(2000);
        setSelectDestination();
        Thread.sleep(2000);
        setClickDestinationDone();
        Thread.sleep(2000);
        SelectDayMonthYear(driver, "Dec", "2024", "1");
        Thread.sleep(2000);
        SelectNights(driver, "5");
        Thread.sleep(2000);
        setClickGuestInput();
        Thread.sleep(2000);
        setSelectGuestAndRoom();
        Thread.sleep(2000);
        setClickGuestDone();
        Thread.sleep(2000);
        setClickSearchHoliday();
        Thread.sleep(2000);

        return new SearchHolidayPackage();
    }

    // Utility method for waiting until element is clickable
    private WebElement waitForElementToBeClickable(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
    }

    // Utility method for waiting until element is visible
    private WebElement waitForElementToBeVisible(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
    }
}
