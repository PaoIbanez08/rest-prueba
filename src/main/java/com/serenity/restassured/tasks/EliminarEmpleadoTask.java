package com.serenity.restassured.tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class EliminarEmpleadoTask implements Task {

  public EliminarEmpleadoTask(int id) {
    this.id = id;
  }

  private final int id;
  private static final String ENDPOINT = "/delete";

  public static Performable eliminaid(int id) {
    return instrumented(EliminarEmpleadoTask.class, id);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Delete.from(ENDPOINT + "/" + id).with(request -> request.contentType(ContentType.JSON)));
  }
}
