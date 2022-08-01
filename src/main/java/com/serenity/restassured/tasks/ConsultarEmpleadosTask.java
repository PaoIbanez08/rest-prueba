package com.serenity.restassured.tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class ConsultarEmpleadosTask implements Task {

  private static final String ENDPOINT = "/employees";

  public ConsultarEmpleadosTask() {}

  public static Performable enElSistema() {
    return instrumented(ConsultarEmpleadosTask.class);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(Get.resource(ENDPOINT).with(request -> request.contentType(ContentType.JSON)));
  }
}
