package edu.pe.cibertec.steps;

import edu.pe.cibertec.config.AppiumConfig;
import edu.pe.cibertec.pages.HomePage;
import edu.pe.cibertec.pages.LoginPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class LoginSteps {

    private static AndroidDriver driver;
    private static LoginPage loginPage;

    @Before
    public void setUp(){
        System.out.println("INICIANDO DRIVER y PAGE OBJECTS");
        driver = AppiumConfig.getDriver();
        loginPage = new LoginPage(driver);
        System.out.println("DRIVER INICIADO: " + (driver!=null));
        System.out.println("LOGIN PAGE INICIADO:" + (loginPage!=null));
    }

    @After
    public void tearDown(){
        System.out.println("CERRANDO DRIVER");
        AppiumConfig.quitDriver();
        driver = null;
        loginPage = null;
    }

    @Given("que el usuario esta en la pantalla de login")
    public void queElUsuarioEstaEnLaPantallaDeLogin(){
        System.out.println("USUARIO EN PANTALLA LOGIN");
    }

    @When("ingresa el email {string}")
    public void ingresaElEmail(String email){
        loginPage.enterEmail(email);
    }

    @And("ingresa el password {string}")
    public void ingresElPassword(String password){
        loginPage.enterPassword(password);
    }

    @And("hacer clic en el boton login")
    public void haceClicEnElBotonLogin(){
        driver.hideKeyboard();
        loginPage.clickLoginButton();
    }

    @Then("deberia acceder a la pantalla principal")
    public void deberiaAccederALaPantallaPrincipal(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue("HomePage no se mostró tras login exitoso", homePage.isHomePageDisplayed());
    }

    @Then("deberia ver un mensaje de error")
    public void deberiaVerUnMensajeDeError(){
        WebElement errorMessage = driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[contains(@text,'Email o password incorrecto')]")
        );
        Assert.assertTrue("Mensaje de error no visible en login fallido", errorMessage.isDisplayed());
    }
}