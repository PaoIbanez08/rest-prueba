package com.serenity.restassured.stepdefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

import com.serenity.restassured.tasks.EliminarEmpleadoTask;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

public class EliminarEmpleado {

  private String name;
  private int id;

  private EnvironmentVariables environmentVariables;

  @Before
  public void setTheStage() {
    OnStage.setTheStage(new OnlineCast());
  }

  @Given("(.*) quiere eliminar un empleado de la empresa con id: (.*)")
  public void Paola_quiere_eliminar_un_empleado_de_la_empresa_con_id(String name, int id) {
    this.name = name;
    this.id = id;
  }

  @When("envia peticion")
  public void envia_peticion() {
    theActorCalled(name)
        .whoCan(CallAnApi.at(environmentVariables.getProperty("api.rest.baseUrl")))
        .attemptsTo(EliminarEmpleadoTask.eliminaid(id));
  }
}
