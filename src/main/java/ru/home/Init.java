package ru.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Init {
    private static WebDriver driver = null;
    public static Properties properties = TestProperties.getInstance().getProperties();

    public static WebDriver getDriver() {
        if (driver == null) {
            initialDriver();
        }
        return driver;
    }

    private static void initialDriver() {
        switch (properties.getProperty("browser")) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
                driver = new FirefoxDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(properties.getProperty("timeout.global")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(properties.getProperty("timeout.pageLoad")), TimeUnit.SECONDS);
    }
}
