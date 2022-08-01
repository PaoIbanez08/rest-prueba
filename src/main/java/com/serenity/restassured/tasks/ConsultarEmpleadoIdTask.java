package com.serenity.restassured.tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class ConsultarEmpleadoIdTask implements Task {

  private final int id;

  private static final String ENDPOINT = "/employee";

  public ConsultarEmpleadoIdTask(int id) {
    this.id = id;
  }

  public static Performable consultaid(int id) {
    return instrumented(ConsultarEmpleadoIdTask.class, id);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Get.resource(ENDPOINT + "/" + id).with(request -> request.contentType(ContentType.JSON)));
  }
}
