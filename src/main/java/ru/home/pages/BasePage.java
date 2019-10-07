package ru.home.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.home.Init;
import ru.home.item.Basket;

public abstract class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//nav[@id='header-search']//child::input[@data-role='search-input']")
    public WebElement searchTextInput;

    @FindBy(xpath = "//nav[@id='header-search']//child::span[contains(@class,'icon_search')]")
    public WebElement searchButton;

    @FindBy(xpath = "//nav[@id='header-search']//span[@data-of='totalPrice']")
    public WebElement basketButton;

    public BasePage() {
        driver = Init.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 20, 1000);
        Basket.getBasket();
    }

    public WebElement waitForElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitAndClickElement(WebElement element) {
        waitForElement(element).click();
    }

    public void searchItem(String nameItem) {
        searchTextInput.clear();
        searchTextInput.sendKeys(nameItem);
        searchButton.click();
    }

    public void goToBasketPage() {
        waitAndClickElement(basketButton);
    }

}
