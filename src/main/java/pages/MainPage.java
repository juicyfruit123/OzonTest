package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage extends BasePage {
    private JavascriptExecutor JAVASCRIPT_EXECUTOR = (JavascriptExecutor) driver;
    @FindBy(xpath = "//input[@name='search']")
    WebElement search;
    @FindBy(xpath = "//div[@class='ui-c ui-c5 ui-d7 ui-e']")
    WebElement clickSearch;
    @FindBy(xpath = "//div[contains(text(),'Цена')]/..//label[text()='до']/..//input")
    WebElement borderUpPrice;
    @FindBy(xpath = "//div[@data-widget='searchResultsV2']//a[@data-test-id='tile-name']")
    List<WebElement> goods;
    @FindBy(xpath = "//a[@href='/cart']")
    WebElement cart;
    @FindBy(xpath = "//span[@class='exponea-close-cross']")
    WebElement closeBanner;
    @FindBy(xpath = "//div[contains(text(),'Бренды')]/..//span[contains(text(),'Посмотреть все')]")
    WebElement clickSelectBrands;
    @FindBy(xpath = "//div[contains(text(),'Бренды')]/..//input[@class='ui-av9 ui-av4']")
    WebElement inputBrands;


    public void searchFor(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Actions actions = new Actions(driver);
        actions.moveToElement(search).click().perform();
        search.sendKeys(Keys.ENTER);
        search.sendKeys(name);
        if (isElementPresented(closeBanner)) {
            closeBanner.click();
        }
        clickSearch.click();
    }

    public void setBorderPrice(String price) {
        borderUpPrice.sendKeys(Keys.LEFT_CONTROL + "a" + Keys.DELETE);
        borderUpPrice.sendKeys(Keys.LEFT_CONTROL + "a" + Keys.DELETE);
        borderUpPrice.sendKeys(price);
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(search));
    }

    public void clickCheckBox(String checkBoxName) {
        WebElement checkBox = driver.findElement(By.xpath("//label//span[contains(text(),'" + checkBoxName + "')]"));
        JAVASCRIPT_EXECUTOR.executeScript("return arguments[0].scrollIntoView(true);", checkBox);
        checkBox.click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(search));
    }

    public void selectAllOdds(int quant, String elements) {
        int count = 0;
        int quantity = 0;
        int j = 0;
        if (elements.equals("четных")) {
            quantity = goods.size() % 2 == 0 ? goods.size() / 2 : (goods.size() - 1) / 2;
            j = 1;
        }
        if (elements.equals("нечетныx")) {
            quantity = goods.size() % 2 == 0 ? goods.size() / 2 : (goods.size() - 1) / 2;
        }
        if (quant != 0) {
            quantity = quant;
        }
        for (int i = j; i < goods.size(); i += 2) {
            System.out.println(count);
            if (count == quantity) {
                break;
            }
            String name = goods.get(i).getText();
            WebElement inCart = null;
            try {
                inCart = driver.findElement(By.xpath("//a[contains(text(),'" + name + "')]/../../..//button[@qa-id='tile-buy-button']"));
            } catch (NoSuchElementException e) {
                System.out.println("товара нет в наличии");
                continue;
            }
            CartPage.map.put(name
                    , Integer.valueOf(
                            goods.get(i).findElement(By.xpath("//a[contains(text(),'" + name + "')]/../../..//span[@data-test-id='tile-price']"))
                                    .getText().replaceAll("\\D", "")));
            JAVASCRIPT_EXECUTOR.executeScript("return arguments[0].scrollIntoView(true);", inCart);
            new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(inCart));
            inCart.click();
            count++;
        }
    }

    public void selectBrands(String... arg) {
        JAVASCRIPT_EXECUTOR.executeScript("return arguments[0].scrollIntoView(true);", clickSelectBrands);
        clickSelectBrands.click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(inputBrands));
        for (String brand : arg) {
            JAVASCRIPT_EXECUTOR.executeScript("return arguments[0].scrollIntoView(true);", inputBrands);
            inputBrands.sendKeys(Keys.LEFT_CONTROL + "a" + Keys.DELETE);
            inputBrands.sendKeys(brand);
            WebElement checkBox = driver.findElement(By.xpath("//div[contains(text(),'Бренды')]/..//span[contains(text(),'" + brand + "')]"));
            JAVASCRIPT_EXECUTOR.executeScript("return arguments[0].scrollIntoView(true);", checkBox);
            new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(checkBox));
            checkBox.click();
            new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(search));
        }
    }

    public void goToCard() {
        cart.click();
    }
}



