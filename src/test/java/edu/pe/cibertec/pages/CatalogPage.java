package edu.pe.cibertec.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class CatalogPage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    public CatalogPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public boolean isCatalogDisplayed() {
        try {
            WebElement title = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath("//android.widget.TextView[@text='Productos']")
                    )
            );
            return title.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void searchProduct(String productName) {
        WebElement searchInput = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator(
                                "new UiSelector().className(\"android.widget.EditText\")"
                        )
                )
        );

        searchInput.click();
        searchInput.clear();
        searchInput.sendKeys(productName);
    }



    public void filterByCategory(String category) {

        WebElement filterButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator(
                                "new UiSelector().className(\"android.widget.CheckBox\").instance(1)"
                        )
                )
        );
        filterButton.click();

        WebElement categoryOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath("//android.widget.TextView[@text='" + category + "']")
                )
        );
        categoryOption.click();
    }


    public boolean areProductsContainingName(String name) {
        List<WebElement> products = driver.findElements(
                AppiumBy.xpath("//android.widget.TextView[contains(@text,'" + name + "')]")
        );
        return !products.isEmpty();
    }

    public boolean areProductsFromCategory(String category) {
        List<WebElement> categories = driver.findElements(
                AppiumBy.xpath("//android.widget.TextView[@text='" + category + "']")
        );
        return !categories.isEmpty();
    }
}