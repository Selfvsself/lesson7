package ru.home.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.home.item.Basket;
import ru.home.item.Item;
import ru.home.item.Trash;

import java.util.List;
import java.util.function.Function;

public class BasketPage extends BasePage {

    @FindBy(xpath = "//a[@data-role='cart-tab-control']")
    public WebElement anchor;

    @FindBy(xpath = "//div[@class='cart-list__product']")
    public List<WebElement> listItem;

    @FindBy(xpath = ".//div[contains(@class,'warranty')]//div[@class='radio radio_checked']//label")
    public WebElement waranty;

    @FindBy(xpath = "//div[@class='total-amount']//div[@class='item-price']//span")
    public WebElement priceItem;

    @FindBy(xpath = "//i[@class='restore-last-removed__icon']")
    public WebElement restoreButton;

    public BasketPage() {
        super();
        waitForElement(anchor);
        Trash.getTrash();
    }

    public boolean checkGuarantee() {
        boolean answer = true;
        for (WebElement elem : listItem) {
            if (!answer) {
                break;
            }
            String name = getNameListItem(elem);
            int garantee = Basket.getGuarantee(name);
            if (garantee >= 0) {
                answer = checkGuarantee(elem, garantee);
            }
        }
        return answer;
    }

    private String getNameListItem(WebElement element) {
        return element.findElement(By.xpath(".//div[@class='cart-list__product-name']/a")).getText();
    }

    private String getPrice(WebElement element) {
        return element.findElement(By.xpath(".//div[@class='item-price']/span")).getText();
    }

    private boolean checkGuarantee(WebElement element, int guarantee) {
        boolean answer = false;
        String str = element.findElement(By.xpath(".//div[contains(@class,'warranty')]//div[@class='radio radio_checked']//label")).getText();
        int warantyElem = Integer.valueOf(str.substring(2, 3));
        if (warantyElem == guarantee) {
            answer = true;
        }
        return answer;
    }

    public boolean checkPrice() {
        boolean answer = true;
        for (WebElement elem : listItem) {
            if (!answer) {
                break;
            }
            String name = getNameListItem(elem);
            double price = Double.parseDouble(getPrice(elem).replaceAll(" ", ""));
            int garantee = Basket.getGuarantee(name);
            if (garantee > 0) {
                String str = elem.findElement(By.xpath(".//div[contains(@class,'warranty')]//div[@class='radio radio_checked']//label")).getText();
                double plusPrice = Double.valueOf(str.substring(10, 15).replaceAll(" ", ""));
                plusPrice *= Basket.getNumber(name);
                price += plusPrice;
            }
            answer = Basket.equalsPrice(name, price);
        }
        return answer;
    }

    public boolean deleteOnBasket(String name) {
        for (WebElement elem : listItem) {
            if (getNameListItem(elem).equals(name)) {
                String lastPrice = priceItem.getText();
                elem.findElement(By.xpath(".//i[@class=\"remove-button__icon\"]")).click();
                waitChangeToBasket(priceItem, lastPrice);
                Trash.add(Basket.get(name));
                Basket.delete(name);
            }
        }
        return checkPrice();
    }

    private void waitChangeToBasket(WebElement elem, String str) {
        wait.until(new Function<WebDriver, Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return !elem.getText().equals(str);
            }
        });
    }

    public void addItem(String name) {
        for (WebElement elem : listItem) {
            if (getNameListItem(elem).equals(name)) {
                String lastPrice = priceItem.getText();
                elem.findElement(By.xpath(".//button[@class=\"count-buttons__button count-buttons__button_plus\"]/i")).click();
                waitChangeToBasket(priceItem, lastPrice);
                Basket.addNumber(name);
            }
        }
    }

    public void clickRestore() {
        if (Trash.size() > 0) {
            waitAndClickElement(restoreButton);
            String lastPrice = priceItem.getText();
            waitChangeToBasket(priceItem, lastPrice);
        }
    }

    public boolean checkRestore() {
        boolean answer = true;
        List<Item> listRestore = Trash.restore();

        for (Item i : listRestore) {
            if (!answer) {
                break;
            }
            boolean preAnswer = false;
            for (WebElement elem : listItem) {
                if (preAnswer) {
                    break;
                }
                String name = getNameListItem(elem);
                preAnswer = i.getName().equals(name);
            }
            answer = preAnswer;
        }
        return answer;
    }
}
