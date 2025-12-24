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
        loginPage.login("admin@test.com", "123456");
        Assertions.assertTrue(homePage.isHomePageDisplayed());

        homePage.clickProduct("Laptop HP Pavilion");
        new ProductDetailPage(driver).ClickAddToCart();
        homePage.clickCartTab();

        Assertions.assertTrue(cartPage.isCartDisplayed());
    }

    @Given("que el usuario tiene el carrito vacio")
    public void usuarioTieneCarritoVacio() {
        loginPage.login("admin@test.com", "123456");
        homePage.clickCartTab();

        Assertions.assertTrue(cartPage.isCartEmpty());
    }

    // =====================
    // WHEN
    // =====================

    @When("procede al checkout")
    public void procedeAlCheckout() {
        checkoutPage.proceedToCheckout();
    }

    @When("ingresa los datos de envio")
    public void ingresaDatosDeEnvio() {
        shippingPage.enterShippingAddress("Av. Lima 123");
    }

    @When("confirma la compra")
    public void confirmaLaCompra() {
        shippingPage.confirmPurchase();
    }

    @When("intenta proceder al checkout")
    public void intentaCheckout() {
        checkoutPage.proceedToCheckout();
    }

    // =====================
    // THEN
    // =====================

    @Then("deberia ver el mensaje de compra existosa")
    public void mensajeCompraExitosa() {
        Assertions.assertTrue(
                checkoutPage.isPurchaseSuccessMessageDisplayed(),
                "ERROR: No se mostró compra exitosa"
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
