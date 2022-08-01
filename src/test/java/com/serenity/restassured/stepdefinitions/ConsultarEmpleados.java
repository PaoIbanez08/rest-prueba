package com.serenity.restassured.stepdefinitions;

import static com.serenity.restassured.exceptions.CodigoRespuestaEsperadaIncorrectoException.CODIGO_ESPERADO_INCORRECTO;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.hasItems;

import com.serenity.restassured.exceptions.CodigoRespuestaEsperadaIncorrectoException;
import com.serenity.restassured.questions.VerificarCodigoRespuesta;
import com.serenity.restassured.tasks.ConsultarEmpleadosTask;
import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

public class ConsultarEmpleados {

  private String actorName;

  private EnvironmentVariables environmentVariables;

  @Before
  public void setTheStage() {
    OnStage.setTheStage(new OnlineCast());
  }

  @Given("(.*) quiere consultar todos los empleados de la empresa")
  public void consultarEmpleados(String actorName) {
    this.actorName = actorName;
  }

  @When("llama al servicio")
  public void llama_al_servicio() {
    theActorCalled(actorName)
        .whoCan(CallAnApi.at(environmentVariables.getProperty("api.rest.baseUrl")))
        .attemptsTo(ConsultarEmpleadosTask.enElSistema());
  }

  @Then("se obtiene respuesta con codigo: (.*)")
  public void se_obtiene_respuesta_con_codigo(int code) {
    theActorInTheSpotlight()
        .should(
            seeThat(VerificarCodigoRespuesta.delServicio(code))
                .orComplainWith(
                    CodigoRespuestaEsperadaIncorrectoException.class, CODIGO_ESPERADO_INCORRECTO));
  }

  @Then("obtiene el listado de empleados registrados con nombres como")
  public void obtiene_el_listado_de_empleados_registrados_con_nombres_como(DataTable dt) {
    List<List<String>> rows = dt.asLists(String.class);

    List<String> names = rows.stream().flatMap(Collection::stream).collect(Collectors.toList());
    theActorInTheSpotlight()
        .should(
            seeThatResponse(
                "todos los empleados deben retornar en el servicio",
                response ->
                    response.body(
                        "data.employee_name", hasItems(names.get(0), names.get(1), names.get(2)))));
  }
}
