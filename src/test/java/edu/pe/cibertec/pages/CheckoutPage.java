package edu.pe.cibertec.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private AndroidDriver driver;
    private WebDriverWait wait;


    public CheckoutPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isPurchaseSuccessMessageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.xpath("//android.widget.TextView[@text='¡Pedido Confirmado!']")
                )
        ).isDisplayed();
    }

    public boolean isEmptyCartMessageDisplayed() {
        return driver.findElements(
                AppiumBy.xpath("//android.widget.TextView[@text='Tu carrito está vacío']")
        ).size() > 0;
    }
}