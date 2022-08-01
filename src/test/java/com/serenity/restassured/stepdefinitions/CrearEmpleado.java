package com.serenity.restassured.stepdefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.is;

import com.serenity.restassured.model.CrearEmpleadoRequest;
import com.serenity.restassured.tasks.CrearEmpleadoTask;
import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

public class CrearEmpleado {

  private String name;

  private EnvironmentVariables environmentVariables;

  @Before
  public void setTheStage() {
    OnStage.setTheStage(new OnlineCast());
  }

  @Given("(.*) quiere crear un nuevo empleado en la empresa")
  public void Paola_quiere_crear_un_nuevo_empleado(String name) {
    this.name = name;
  }

  @When("ingresa los datos")
  public void ingresa_los_datos(DataTable dt) {
    List<List<String>> rows = dt.asLists(String.class);
    CrearEmpleadoRequest crearEmpleadoRequest = new CrearEmpleadoRequest();

    List<String> columns = rows.get(1);
    crearEmpleadoRequest.setName(columns.get(0));
    crearEmpleadoRequest.setSalary(columns.get(1));
    crearEmpleadoRequest.setAge(columns.get(2));

    theActorCalled(name)
        .whoCan(CallAnApi.at(environmentVariables.getProperty("api.rest.baseUrl")))
        .attemptsTo(CrearEmpleadoTask.ingresarDatos(crearEmpleadoRequest));
  }

  @Then("mensaje de confirmacion en (.*) es: (.*)")
  public void mensaje_de_confirmacion(String atributo, String mensaje) {
    theActorInTheSpotlight()
        .should(
            seeThatResponse(
                "El mensaje de respuesta correcto",
                response -> response.body(atributo, is(mensaje))));
  }
}
