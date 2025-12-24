package edu.pe.cibertec.steps;

import edu.pe.cibertec.config.AppiumConfig;
import edu.pe.cibertec.pages.HomePage;
import edu.pe.cibertec.pages.LoginPage;
import edu.pe.cibertec.pages.ProfilePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LogoutSteps {

    private static AndroidDriver driver = AppiumConfig.getDriver();
    private HomePage homePage = new HomePage(driver);
    private ProfilePage profilePage = new ProfilePage(driver);
    private LoginPage loginPage = new LoginPage(driver);

    @Given("que el usuario ya esta logueado")
    public void queElUsuarioYaEstaLogueado(){
        LoginPage loginPage = new LoginPage(driver);

        String email = "admin@test.com";
        String password = "123456";

        loginPage.login(email, password);

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue("HomePage no visible, login fallido", homePage.isHomePageDisplayed());
    }

    @When("hace clic en el menu de usuario")
    public void haceClicEnElMenuDeUsuario(){
        homePage.clickProfileTab();
    }

    @And("hace clic en cerrar sesion")
    public void haceClicEnCerrarSesion(){
        profilePage.logout();

        Assert.assertTrue(
                "El icono de cerrar sesión NO fue pulsado",
                profilePage.logoutDialogIsRequested()
        );

        profilePage.confirmLogout();
    }




    @Then("deberia regresar a la pantalla de login")
    public void deberiaRegresarALaPantallaDeLogin(){
        // Validación: aparecer la pantalla de login
        boolean isLoginDisplayed = driver.findElement(
                io.appium.java_client.AppiumBy.className("android.widget.Button")).isDisplayed(); // Ajusta según botón login
        Assert.assertTrue("No se muestra la pantalla de login", isLoginDisplayed);
    }

}
