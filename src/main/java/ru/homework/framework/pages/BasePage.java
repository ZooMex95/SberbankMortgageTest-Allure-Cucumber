package ru.homework.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.homework.framework.managers.InitManager;
import ru.homework.framework.managers.ManagerPages;


import java.security.Key;

import static ru.homework.framework.managers.DriverManager.getDriver;

public class BasePage {
    protected ManagerPages app = ManagerPages.getManagerPages();

    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);

    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    protected void waitElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void scrollToElementJs (WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void fillInput(WebElement element, String value) {
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(value);
    }


}
