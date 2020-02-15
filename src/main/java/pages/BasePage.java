package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import steps.BaseSteps;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BasePage {
    WebDriver driver = BaseSteps.getDriver();
    Map<String, Integer> products = new HashMap<>();

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public Boolean isElementPresented(WebElement element) {
        boolean notExist;
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            notExist = element.isDisplayed();

        } catch (NoSuchElementException e) {
            notExist = false;
        } finally {
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }

        return notExist;
    }

}
