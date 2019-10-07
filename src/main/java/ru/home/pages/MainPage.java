package ru.home.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.home.TestProperties;

import java.util.Properties;

public class MainPage extends BasePage {
    public static Properties properties = TestProperties.getInstance().getProperties();

    @FindBy(xpath = "//h4[@class='main-footer__nav-title']")
    public WebElement anchor;

    public MainPage() {
        super();
        driver.get(String.valueOf(properties.get("baseUrl")));
        waitForElement(anchor);
    }


}
