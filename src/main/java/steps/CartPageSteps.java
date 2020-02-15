package steps;

import io.qameta.allure.Step;
import pages.CartPage;

import java.io.IOException;

import static util.Attachments.getBytes;

public class CartPageSteps {
    CartPage cartPage = new CartPage();

    @Step("проверяется что все добавленные товары находятся в корзине")
    public void checkAddProducts() {
        cartPage.checkProductInCart();
    }

    @Step("проверяется наличие текста - {0}")
    public void checkText(String arg0) {
        cartPage.checkCountProduct(arg0);
    }

    @Step("удаляются все продукты из корзины")
    public void deleteAllProducts() {
        cartPage.deleteAllProduct();
    }

    @Step("проверяется что корзина пуста")
    public void checkAllDelete() {
        cartPage.checkDelete();
    }

    @Step("информация о продуктах")
    public void infAboutProducts() {
        try {
            getBytes(CartPage.map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
