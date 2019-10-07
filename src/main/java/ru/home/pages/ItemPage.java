package ru.home.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.home.item.Basket;
import ru.home.item.Item;

import java.util.function.Function;

public class ItemPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'hidden-xs')]//span[@class='current-price-value']")
    public WebElement priceItem;

    @FindBy(xpath = "//h1[@data-product-param='name']")
    public WebElement nameItem;

    @FindBy(xpath = "//select[@class='form-control select']")
    public WebElement guaranteeSelect;

    @FindBy(xpath = "//button[@class='btn btn-cart btn-lg']//span")
    public WebElement anchor;

    public ItemPage() {
        super();
        waitForElement(anchor);
    }

    public void buy() {
        Item item = new Item();
        item.setName(nameItem.getText());
        item.setPrice(priceItem.getText());
        Basket.add(item);
        String lastPrice = basketButton.getText();
        waitAndClickElement(anchor);
        waitAddToBasket(lastPrice);
    }

    public void buy(int guarantee) {
        Item item = new Item();
        item.setName(nameItem.getText());
        item.setPrice(priceItem.getText());
        item.setGuarantee(guarantee);
        Basket.add(item);
        String lastPrice = basketButton.getText();
        waitAndClickElement(anchor);
        waitAddToBasket(lastPrice);
    }

    public void changeGuaranteeTwoYear() {
        String priceStr = priceItem.getText();

        Select select = new Select(guaranteeSelect);
        select.selectByIndex(2);

        wait.until(new Function<WebDriver, Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return !priceItem.getText().equals(priceStr);
            }
        });
        buy(2);
    }

    private void waitAddToBasket(String str) {
        wait.until(new Function<WebDriver, Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return !priceItem.getText().equals(str);
            }
        });
    }

    public boolean equalsPriceBasket() {
        String lastPrice = basketButton.getText();
        waitAddToBasket(lastPrice);
        return Basket.equalsSumPrice(basketButton.getText());
    }
}
