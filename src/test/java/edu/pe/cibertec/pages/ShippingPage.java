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
        WebElement addressField = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.className("android.widget.EditText")
                )
        );
        addressField.click();
        addressField.sendKeys(address);
    }

    public void confirmPurchase() {
        WebElement confirmButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath("//android.widget.TextView[@text='Confirmar compra']")
                )
        );
        confirmButton.click();
    }

    public boolean isAddressRequiredMessageDisplayed() {
        try {
            WebElement msg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath("//android.widget.TextView[@text='Ingrese dirección de envío']")
                    )
            );
            return msg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
