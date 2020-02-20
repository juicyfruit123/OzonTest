package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    public void waitPageLoaded() {
        new WebDriverWait(driver, 45)
                .until((ExpectedCondition<Boolean>) webDriver -> !isElementPresented((By.xpath("//div[contains(@class , 'parandja')]"))));
    }

    public Boolean isElementPresented(By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            return false;
        }
    }

}
