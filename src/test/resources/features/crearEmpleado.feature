Feature: Crear empleado

Scenario: Crear un nuevo empleado
  Given Paola quiere crear un nuevo empleado en la empresa
  When ingresa los datos
  |name  |salary|age|
  |Pedro |30000 |32 |
  Then se obtiene respuesta con codigo: 200
  And mensaje de confirmacion en message es: Successfully! Record has been added.
