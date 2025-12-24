@logout

Feature: Cerrar sesion

  Scenario: Logout exitoso
    Given que el usuario ya esta logueado
    When hace clic en el menu de usuario
    And hace clic en cerrar sesion
    Then deberia regresar a la pantalla de login
