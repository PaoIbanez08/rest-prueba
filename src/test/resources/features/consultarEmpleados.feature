Feature: Consultar empleados todos los empleados del sistema

Scenario: Consultar todos empleados
  Given Paola quiere consultar todos los empleados de la empresa
  When llama al servicio
  Then obtiene el listado de empleados registrados con nombres como
    |Tiger Nixon      |
    |Garrett Winters  |
    |Ashton Cox       |