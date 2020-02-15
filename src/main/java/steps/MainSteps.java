package steps;

import io.qameta.allure.Step;
import pages.MainPage;

public class MainSteps {
    private MainPage mainPage = new MainPage();

    @Step("выполняется поиск по {0}")
    public void searchProduct(String arg0) {
        mainPage.searchFor(arg0);
    }

    @Step("выставляется ограничение цены - {0}")
    public void priceLimit(String limit) {
        mainPage.setBorderPrice(limit);
    }

    @Step("отмечается чекбокс - {0}")
    public void clickCheckBox(String name) {
        mainPage.clickCheckBox(name);
    }

    @Step("добавляются продукты в корзину")
    public void addOddProduct(int count, String name) {
        mainPage.selectAllOdds(count, name);
    }

    @Step("выполняется переход в корзину")
    public void goToCard() {
        mainPage.goToCard();
    }

    public void selectBrands(String arg0, String arg1) {
        mainPage.selectBrands(arg0, arg1);
    }


}
