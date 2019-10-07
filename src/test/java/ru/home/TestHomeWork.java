package ru.home;
/*
1) открыть dns-shop
2) в поиске найти playstation
3) кликнуть по playstation 4 slim black
4) запомнить цену
5) Доп.гарантия - выбрать 2 года
6) дождаться изменения цены и запомнить цену с гарантией
7) Нажать Купить
8) выполнить поиск Detroit
9) запомнить цену
10) нажать купить
11) проверить что цена корзины стала равна сумме покупок
12) перейри в корзину
13) проверить, что для приставки выбрана гарантия на 2 года
14) проверить цену каждого из товаров и сумму
15) удалить из корзины Detroit
16) проверить что Detroit нет больше в корзине и что сумма уменьшилась на цену Detroit
17) добавить еще 2 playstation (кнопкой +) и проверить что сумма верна (равна трем ценам playstation)
18) нажать вернуть удаленный товар, проверить что Detroit появился в корзине и сумма увеличилась на его значение
Подсказки:
Отдельные PageObject - поиск, страница с результатами, карточка товара, корзина, (и, возможно, позиция товара)
Следует создать отдельный класс Product - который будет являться моделью купленного товара (с полями цена, гарантия,
описание и что-тро еще)
Методы должны быть максимально параметризируемые, позволяющие проверить любой товар, и выполнить с ним любые шаги из
данного теста.
*/

import org.junit.Assert;
import org.junit.Test;
import ru.home.pages.*;

public class TestHomeWork extends BaseTest {

    @Test
    public void testDNS() {
        MainPage mainPage = new MainPage();
        mainPage.searchItem("playstation");

        FilteredPage filteredPage = new FilteredPage();
        filteredPage.findAndClickOnItemName("PlayStation 4 Slim Black");

        ItemPage itemPage = new ItemPage();
        itemPage.changeGuaranteeTwoYear();

        itemPage.searchItem("Detroit");
        itemPage.buy();
        Assert.assertTrue(itemPage.equalsPriceBasket());

        itemPage.goToBasketPage();
        BasketPage basketPage = new BasketPage();
        Assert.assertTrue(basketPage.checkGuarantee());
        Assert.assertTrue(basketPage.checkPrice());

        Assert.assertTrue(basketPage.deleteOnBasket("Игра Detroit: Стать человеком (PS4)"));

        basketPage.addItem("Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры");
        basketPage.addItem("Игровая приставка PlayStation 4 Slim Black 1 TB + 3 игры");

        Assert.assertTrue(basketPage.checkPrice());

        basketPage.clickRestore();

        Assert.assertTrue(basketPage.checkRestore());
    }
}
