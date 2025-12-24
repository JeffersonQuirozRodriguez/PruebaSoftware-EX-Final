package edu.pe.cibertec.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShippingPage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    public ShippingPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterShippingAddress(String address) {
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.className("android.widget.EditText")
                )
        );
        input.clear(); // borra cualquier valor existente
        if (address != null && !address.isEmpty()) {
            input.sendKeys(address);
        }
    }

    public void selectYapePayment() {
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().className(\"android.widget.RadioButton\").instance(1)")
        ).click();
    }

    public void confirmPurchase() {
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().className(\"android.widget.Button\").instance(0)")
        ).click();
    }

    public boolean isAddressRequiredMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.widget.TextView[contains(@text,'La dirección es requerida')]")
            ));
            return message.isDisplayed();
        } catch (Exception e) {
            // Si no aparece en 5 segundos, devuelve false
            return false;
        }
    }


}
