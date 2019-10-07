package ru.home.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FilteredPage extends BasePage {

    @FindBy(xpath = "//div[@class='product-info__title-link']")
    public List<WebElement> filteredItem;

    @FindBy(xpath = "//div[@data-role='items-catalog-container']")
    public WebElement anchor;

    public FilteredPage() {
        super();
        waitForElement(anchor);
    }

    public void findAndClickOnItemName(String itemName) {
        WebElement selected = null;
        for (WebElement elem : filteredItem) {
            if (selected != null) {
                break;
            }
            List<WebElement> search = elem.findElements(By.xpath("./a[contains(text(),'" + itemName + "')]"));
            if (search.size() > 0) {
                selected = search.get(0);
            }
        }
        selected.click();
    }
}
