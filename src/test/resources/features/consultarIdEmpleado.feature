Feature: Consultar empleado por id

Scenario: Consultar empleado por id
  Given Paola quiere consultar un empleado de la empresa: 1
  When consulta el servicio
  Then se obtiene respuesta con codigo: 200
  And el salario del empleado es: 320800
