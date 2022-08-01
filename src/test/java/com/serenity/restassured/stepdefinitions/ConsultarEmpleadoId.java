package com.serenity.restassured.stepdefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.is;

import com.serenity.restassured.tasks.ConsultarEmpleadoIdTask;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

public class ConsultarEmpleadoId {

  private String name;
  private int id;
  private EnvironmentVariables environmentVariables;

  @Before
  public void setTheStage() {
    OnStage.setTheStage(new OnlineCast());
  }

  @Given("(.*) quiere consultar un empleado de la empresa: (.*)")
  public void Paola_quiere_consultar_un_empleado_de_la_empresa(String name, int id) {
    this.name = name;
    this.id = id;
  }

  @When("consulta el servicio")
  public void consulta_el_servicio() {
    theActorCalled(name)
        .whoCan(CallAnApi.at(environmentVariables.getProperty("api.rest.baseUrl")))
        .attemptsTo(ConsultarEmpleadoIdTask.consultaid(id));
  }

  @Then("el salario del empleado es: (.*)")
  public void obtiene_informacion_del_empleado(int salario) {
    theActorInTheSpotlight()
        .should(
            seeThatResponse(
                "se obtiene informacion del empleado",
                response -> response.body("data.employee_salary", is(salario))));
  }
}
