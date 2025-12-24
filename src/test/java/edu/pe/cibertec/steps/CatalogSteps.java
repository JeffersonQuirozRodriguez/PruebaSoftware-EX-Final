package edu.pe.cibertec.steps;

import edu.pe.cibertec.config.AppiumConfig;
import edu.pe.cibertec.pages.CatalogPage;
import edu.pe.cibertec.pages.HomePage;
import edu.pe.cibertec.pages.LoginPage;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class CatalogSteps {

    private static AndroidDriver driver;
    private static LoginPage loginPage;
    private static HomePage homePage;
    private static CatalogPage catalogPage;

    @Before("@catalogo")
    public void setUp() {
        driver = AppiumConfig.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        catalogPage = new CatalogPage(driver);
    }

    @After("@catalogo")
    public void tearDown() {
        AppiumConfig.quitDriver();
    }

    // ======================
    // GIVEN
    // ======================

    @Given("que el usuario esta logueado en la aplicacion")
    public void usuarioLogueado() {
        loginPage.login("admin@test.com", "123456");

        Assertions.assertTrue(
                homePage.isHomePageDisplayed(),
                "ERROR: No se pudo iniciar sesión"
        );
    }

    @Given("que el usuario esta en el catalogo")
    public void usuarioEstaEnElCatalogo() {
        boolean enCatalogo = homePage.isHomePageDisplayed();
        Assertions.assertTrue(enCatalogo, "ERROR: No estamos en el catálogo");
    }


    // ======================
    // WHEN
    // ======================

    @When("navega al catalogo de productos")
    public void navegaAlCatalogo() {
        boolean catalogVisible = catalogPage.isCatalogDisplayed();
        Assertions.assertTrue(
                catalogVisible,
                "ERROR: El catálogo no está visible"
        );
    }

    @When("busca el producto {string}")
    public void buscaElProducto(String producto) {
        catalogPage.searchProduct(producto);
    }

    @When("filtra los productos por la categoria {string}")
    public void filtraPorCategoria(String categoria) {
        catalogPage.filterByCategory(categoria);
    }

    // ======================
    // THEN
    // ======================

    @Then("deberia ver la lista de productos disponibles")
    public void deberiaVerListaDeProductos() {
        boolean catalogoVisible = catalogPage.isCatalogDisplayed();
        Assertions.assertTrue(
                catalogoVisible,
                "ERROR: No se muestran los productos del catálogo"
        );
    }

    @Then("deberia ver productos que contengan {string}")
    public void deberiaVerProductosBuscados(String producto) {
        boolean productosEncontrados =
                catalogPage.areProductsContainingName(producto);

        Assertions.assertTrue(
                productosEncontrados,
                "ERROR: No se encontraron productos con el texto buscado"
        );
    }

    @Then("deberia ver solo productos de la categoria {string}")
    public void deberiaVerProductosDeCategoria(String categoria) {
        boolean categoriaCorrecta =
                catalogPage.areProductsFromCategory(categoria);

        Assertions.assertTrue(
                categoriaCorrecta,
                "ERROR: Los productos no corresponden a la categoría seleccionada"
        );
    }
}
