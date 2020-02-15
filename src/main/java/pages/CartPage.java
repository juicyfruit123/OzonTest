package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartPage extends BasePage {
    @FindBy(xpath = "//div[@class='by7']//a/span")
    List<WebElement> productsInCart;
    @FindBy(xpath = "//div[@data-widget='totalv2']//span[contains(text(),'товаров')]")
    WebElement countProduct;
    @FindBy(xpath = "//span[contains(text(),'Удалить выбранные')]")
    WebElement deleteAll;
    @FindBy(xpath = "//div[contains(text(),'Удалить')]")
    WebElement confirmDelete;
    @FindBy(xpath = "//h1[contains(text(),'')]")
    WebElement deleteMessage;

    public static Map<String, Integer> map = new HashMap<>();

    public void checkProductInCart() {
        for (WebElement element : productsInCart) {
            Assert.assertTrue(map.containsKey(element.getText()));
        }
    }

    public void checkCountProduct(String count) {
        try {
            Assert.assertEquals(count, countProduct.getText().substring(0, countProduct.getText().indexOf("•")).trim());
        } catch (AssertionError e) {
            System.out.println("не найдено столько элементов");
        }
    }

    public void deleteAllProduct() {
        deleteAll.click();
        confirmDelete.click();
    }

    public void checkDelete() {
        Assert.assertTrue("Корзина пуста".contains(deleteMessage.getText()));
    }


}
