package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.homework.framework.managers.InitManager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static ru.homework.framework.managers.DriverManager.getDriver;

public class Hooks {

    @Before
    public void before() {
        InitManager.initFramework();
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            Allure.addAttachment("Failure screenshot", "image/png", addScreenshot(), "png");
        }
        InitManager.quitFramework();
    }

    private static InputStream addScreenshot() {
        byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        return new ByteArrayInputStream(screenshot);
    }

}
