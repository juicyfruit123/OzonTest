package steps;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;

public class MyStepdefs {
    MainSteps mainPageStep = new MainSteps();
    CartPageSteps cartPageSteps = new CartPageSteps();

    @Когда("выполнен поиск по {string}")
    public void searchProduct(String arg0) {
        mainPageStep.searchProduct(arg0);
    }


    @Тогда("ограничить цену до {string}")
    public void priceLimit(String limit) {
        mainPageStep.priceLimit(limit);
    }

    @Тогда("отметить чекбокс {string} {string}")
    public void clickCheckBox(String name, String bool) {
        if (bool.equals("true")) {
            mainPageStep.clickCheckBox(name);
        }
    }

    @Тогда("запоминаем название товаров и переходим в корзину")
    public void goToCard() {
        mainPageStep.goToCard();
    }


    @Тогда("убедиться что все добавленные товары находятся в корзине")
    public void checkAddProducts() {
        cartPageSteps.checkAddProducts();
    }

    @Тогда("проверить что отображается текст Ваша корзина  - {string} {string}")
    public void checkText(String arg0, String arg1) {
        if (arg1.equals("true")) {
            cartPageSteps.checkText(arg0);
        }
    }

    @Тогда("удалить все товары из корзины")
    public void deleteAllProducts() {
        cartPageSteps.deleteAllProducts();
    }

    @Тогда("проверить что корзина не содержит товаров")
    public void checkAllDelete() {
        cartPageSteps.checkAllDelete();
    }

    @И("приложить файл с информацией о товарах")
    public void productsReport() {
        cartPageSteps.infAboutProducts();
    }

    @И("выбрать бренды {string} {string} {string}")
    public void selectBrands(String arg0, String arg1, String b) {
        if (b.equals("true")) {
            mainPageStep.selectBrands(arg0, arg1);
        }
    }

    @И("отметить чекбокс {string}")
    public void selectRating(String arg0) {
        mainPageStep.clickCheckBox(arg0);
    }


    @Тогда("из результатов поиска добавить {string} {string} товаров {string}")
    public void addProducts(String arg0, String arg1, String bool) {
        if (bool.equals("true")) {
            int count = 0;
            if (!arg0.equals("всех")) {
                count = Integer.parseInt(arg0);
            }
            mainPageStep.addOddProduct(count, arg1);
        }

    }
}
