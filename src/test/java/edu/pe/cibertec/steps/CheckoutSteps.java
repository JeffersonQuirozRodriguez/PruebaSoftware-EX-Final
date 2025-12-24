package edu.pe.cibertec.steps;

import edu.pe.cibertec.config.AppiumConfig;
import edu.pe.cibertec.pages.*;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class CheckoutSteps {

    private AndroidDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private ShippingPage shippingPage;

    @Before("@checkout")
    public void setUp() {
        driver = AppiumConfig.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        shippingPage = new ShippingPage(driver);
    }

    @After("@checkout")
    public void tearDown() {
        AppiumConfig.quitDriver();
    }

    // =====================
    // GIVEN
    // =====================

    @Given("que el usuario tiene productos en el carrito")
    public void usuarioTieneProductosEnElCarrito() {

        // LOGIN REAL
        loginPage.login("admin@test.com", "123456");
        Assertions.assertTrue(homePage.isHomePageDisplayed());

        // AGREGAR PRODUCTO CON BOTÓN +
        homePage.addFirstProductToCart();

        // IR AL CARRITO
        homePage.clickCartTab();

        Assertions.assertTrue(cartPage.isCartDisplayed(),
                "ERROR: No se mostró la pantalla del carrito");
    }

    @Given("que el usuario tiene el carrito vacio")
    public void usuarioTieneCarritoVacio() {
        loginPage.login("admin@test.com", "123456");
        homePage.clickCartTab();

        Assertions.assertTrue(cartPage.isCartEmpty(),
                "ERROR: El carrito no está vacío");
    }

    // =====================
    // WHEN
    // =====================

    @When("procede al checkout")
    public void procedeAlCheckout() {
        cartPage.clickProceedToCheckout();
    }

    @When("ingresa los datos de envio")
    public void ingresaDatosDeEnvio() {
        shippingPage.enterShippingAddress("Av. Principal 123");
        shippingPage.selectYapePayment();
    }

    @When("confirma la compra")
    public void confirmaLaCompra() {
        shippingPage.confirmPurchase();
    }

    @When("no ingresa los datos de envio")
    public void noIngresaDatosDeEnvio() {
        shippingPage.enterShippingAddress(""); // deja el campo vacío
        shippingPage.selectYapePayment(); // si el pago es obligatorio, puedes seleccionarlo
    }


    @When("intenta proceder al checkout")
    public void intentaCheckout() {
        cartPage.clickProceedToCheckoutIfAvailable();
    }
    // =====================
    // THEN
    // =====================

    @Then("deberia ver el mensaje de compra existosa")
    public void mensajeCompraExitosa() {
        Assertions.assertTrue(
                checkoutPage.isPurchaseSuccessMessageDisplayed(),
                "ERROR: No se mostró el mensaje de pedido confirmado"
        );
    }

    @Then("deberia ver mensaje de carrito vacio")
    public void mensajeCarritoVacio() {
        Assertions.assertTrue(
                checkoutPage.isEmptyCartMessageDisplayed(),
                "ERROR: No se mostró mensaje de carrito vacío"
        );
    }

    @Then("deberia ver mensaje de direccion requerida")
    public void mensajeDireccionRequerida() {
        Assertions.assertTrue(
                shippingPage.isAddressRequiredMessageDisplayed(),
                "ERROR: No se mostró mensaje de dirección requerida"
        );
    }


}