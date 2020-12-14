package ru.homework.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class StartPage extends BasePage{

    @FindBy(xpath = "//a[contains(@class, 'kitt-top-menu__link') and @role='button']")
    private List<WebElement> menuBaseList;

    @FindBy(xpath = "//a[contains(@class, 'kitt-top-menu__link_second')]")
    private List<WebElement> menuSubList;

    @Step("Переход в главное меню {nameBaseMenu}")
    public StartPage selectBaseMenu(String nameBaseMenu) {
        for (WebElement element: menuBaseList) {
            if (element.getText().equalsIgnoreCase(nameBaseMenu)) {
                element.click();
                return this;
            }
        }
        Assert.fail("Меню \"" + nameBaseMenu + "\" не было найдено на странице");
        return this;
    }

    @Step("Выбор подменю {nameSubMenu}")
    public MortgagePage selectSubMenu(String nameSubMenu) {
        for (WebElement element: menuSubList) {
            if (element.getText().equalsIgnoreCase(nameSubMenu)) {
                waitElementToBeVisible(element);
                element.click();
                return app.getMortgagePage();
            }
        }
        Assert.fail("Меню \"" + nameSubMenu + "\" не было найдено на странице");
        return null;
    }

}
