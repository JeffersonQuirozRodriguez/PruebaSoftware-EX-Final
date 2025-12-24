package edu.pe.cibertec.pages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    public ProfilePage(AndroidDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Abrir el menú de perfil
    public void openProfileMenu(){
        WebElement profileMenu = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath("//android.widget.TextView[@text='Perfil']"))
        );
        profileMenu.click();
    }

    // Verificar que el usuario logueado es correcto (por email u otro identificador)
    public boolean isUserLogged(String userEmail){
        try {
            WebElement emailText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath("//android.widget.TextView[contains(@text,'" + userEmail + "')]")
                    )
            );
            return emailText.isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    // Cerrar sesión

    public void logout() {

        WebElement logoutView = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        AppiumBy.xpath("//android.view.View[@content-desc='Cerrar sesión']")
                )
        );

        driver.executeScript(
                "mobile: clickGesture",
                ImmutableMap.of(
                        "elementId",
                        ((RemoteWebElement) logoutView).getId()
                )
        );
    }



    public void confirmLogout() {

        WebElement confirmButton =
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        AppiumBy.androidUIAutomator(
                                "new UiSelector().text(\"Sí, cerrar sesión\")"
                        )
                ));

        driver.executeScript(
                "mobile: clickGesture",
                ImmutableMap.of(
                        "elementId",
                        ((RemoteWebElement) confirmButton).getId()
                )
        );
    }

    public boolean logoutDialogIsRequested() {
        return driver.findElements(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().text(\"Cerrar Sesión\")"
                )
        ).size() > 0;
    }

    public void waitForLogoutDialog() {

        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().text(\"Cerrar Sesión\")"
                )
        ));
    }

}
