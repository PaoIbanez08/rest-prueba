Feature: Eliminar empleado por id

Scenario: Eliminar empleado por id
  Given Paola quiere eliminar un empleado de la empresa con id: 2
  When envia peticion
  Then se obtiene respuesta con codigo: 200
  And mensaje de confirmacion en message es: Successfully! Record has been deleted
