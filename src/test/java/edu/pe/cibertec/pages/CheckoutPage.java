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

    public void proceedToCheckout() {
        WebElement checkoutButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath("//android.widget.TextView[@text='Proceder al checkout']")
                )
        );
        checkoutButton.click();
    }

    public boolean isEmptyCartMessageDisplayed() {
        try {
            WebElement msg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath("//android.widget.TextView[@text='Tu carrito está vacío']")
                    )
            );
            return msg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPurchaseSuccessMessageDisplayed() {
        try {
            WebElement msg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath("//android.widget.TextView[@text='Compra realizada con éxito']")
                    )
            );
            return msg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}