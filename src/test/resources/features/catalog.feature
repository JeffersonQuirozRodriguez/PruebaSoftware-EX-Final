@catalogo

Feature: Catalogo de productos


  Background:
    Given que el usuario esta logueado en la aplicacion

  Scenario: Ver lista de productos
    When navega al catalogo de productos
    Then deberia ver la lista de productos disponibles

  Scenario: Buscar producto por nombre
    When busca el producto "Laptop"
    Then deberia ver productos que contengan "Laptop"

  Scenario: Filtrar productos por categoria
    When filtra los productos por la categoria "Electrónica"
    Then deberia ver solo productos de la categoria "Electrónica"