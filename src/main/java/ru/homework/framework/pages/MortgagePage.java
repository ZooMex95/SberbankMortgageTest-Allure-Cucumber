package ru.homework.framework.pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.homework.framework.utils.MyAllureListener;


import java.util.List;

import static ru.homework.framework.managers.DriverManager.getDriver;

public class MortgagePage extends BasePage {

    @FindBy(xpath = "//iframe[contains(@sandbox, 'allow-forms')]")
    private WebElement blockInputFields;

    @FindBy(xpath = "//div[@data-label='Стоимость недвижимости']/input")
    private WebElement realtyPrice;

    @FindBy(xpath = "//div[@data-label='Первоначальный взнос']/input")
    private WebElement firstPayment;

    @FindBy(xpath = "//div[@data-label='Срок кредита']/input")
    private WebElement timeOfMortgage;

    @FindBy(xpath = "//div[@class='_10tbx0-kRV82stKf-dhvRY']/span")
    private List<WebElement> listOfNameButtons;

    @FindBy(xpath = "//div[@class='_2LdxQuPFoUotOvkdZfv9rR']/div/ul/li")
    private List<WebElement> listOfSums;

    @Step("Заполнение поля {fieldName} значением {value}")
    public MortgagePage fillInputFields(String fieldName, String value) {
        scrollToElementJs(blockInputFields);
        getDriver().switchTo().frame("iFrameResizer0"); //*blockinput
        switch (fieldName){
            case "Стоимость недвижимости":
                fillInput(realtyPrice, value);
                assertFields(realtyPrice, fieldName, value);
                break;
            case "Первоначальный взнос":
                fillInput(firstPayment, value);
                assertFields(firstPayment, fieldName, value);
                break;
            case "Срок кредита":
                fillInput(timeOfMortgage, value);
                assertFields(timeOfMortgage, fieldName, value);
                break;
            default:
                Assert.fail("Поле " + fieldName + " не найдено на странице");
        }
        getDriver().switchTo().defaultContent();
        return this;
    }

    @Step("Включение кнопки {nameOfButton}")
    public MortgagePage buttonGetOn(String nameOfButton) throws InterruptedException {
        getDriver().switchTo().frame(blockInputFields);
        for (WebElement element: listOfNameButtons) {
            WebElement currentElement = element.findElement(By.xpath("./../../span/label/div/input"));
            if (element.getText().equalsIgnoreCase(nameOfButton)) {
                if (currentElement.getAttribute("aria-checked").equals("false")) {
                    currentElement.click();
                    Assert.assertEquals("Положение кнопки " + element.getText() + " не верное",
                            "true", currentElement.getAttribute("aria-checked"));
                    Thread.sleep(2000);
                }
            }
        }
        getDriver().switchTo().defaultContent();
        return this;
    }

    @Step("Выключение кнопки {nameOfButton}")
    public MortgagePage buttonGetOff(String nameOfButton) throws InterruptedException {
        getDriver().switchTo().frame(blockInputFields);
        //String currentPercent = getDriver().findElement(By.xpath("//span[text()='Процентная ставка']/../span[@data-e2e-id]/span")).getText();
        for (WebElement element: listOfNameButtons) {
            WebElement currentElement = element.findElement(By.xpath("./../../span/label/div/input"));
            if (element.getText().equalsIgnoreCase(nameOfButton)) {
                if (currentElement.getAttribute("aria-checked").equals("true")) {
                    currentElement.click();
                    Assert.assertEquals("Положение кнопки " + element.getText() + " не верное",
                            "false", currentElement.getAttribute("aria-checked"));
                    Thread.sleep(2000);
                }
            }
        }
        getDriver().switchTo().defaultContent();
        return this;
    }

    @Step("Проверка суммы {nameOfSumToBeChecked}")
    public MortgagePage checkSum(String nameOfSumToBeChecked) {
        WebElement currentElement = findSumOfElementByName(nameOfSumToBeChecked).findElement(By.xpath("./span[@data-e2e-id]/span"));
        switch (nameOfSumToBeChecked) {
            case "Сумма кредита":
                Assert.assertEquals(nameOfSumToBeChecked + " не совпадает",
                        "2122000", currentElement.getText().replaceAll("\\D", ""));
                getDriver().switchTo().defaultContent();
                break;
            case "Ежемесячный платеж":
                Assert.assertEquals(nameOfSumToBeChecked + " не совпадает",
                        "16017", currentElement.getText().replaceAll("\\D", ""));
                getDriver().switchTo().defaultContent();
                break;
            case "Необходимый доход":
                Assert.assertEquals(nameOfSumToBeChecked + " не совпадает",
                        "20618", currentElement.getText().replaceAll("\\D", ""));
                getDriver().switchTo().defaultContent();
                break;
            case "Процентная ставка":
                Assert.assertEquals(nameOfSumToBeChecked + " не совпадает",
                        "11%", currentElement.getText());
                getDriver().switchTo().defaultContent();
                break;
            default:
                Assert.fail("Не найдено " + nameOfSumToBeChecked);
        }
        return this;
    }

    private WebElement findSumOfElementByName(String nameOfElementSum) {
        getDriver().switchTo().frame(blockInputFields);
        for (WebElement element: listOfSums) {
            WebElement currentElement = element.findElement(By.xpath("./span[@class='_270UmDDpslAzQsVny6n-hx']"));
            if (currentElement.getText().equalsIgnoreCase(nameOfElementSum)) {
                return element;
            }
        }
        Assert.fail("Не найдена " + nameOfElementSum);
        return null;

    }

    private void assertFields(WebElement element, String fieldName, String value) {
        Assert.assertEquals("Поле " + fieldName + " заполнено не верно",
                value, element.getAttribute("value").replaceAll("\\D", ""));
    }


}
