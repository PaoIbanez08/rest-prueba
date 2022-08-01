package com.serenity.restassured.tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import com.serenity.restassured.model.CrearEmpleadoRequest;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class CrearEmpleadoTask implements Task {

  private static final String ENDPOINT = "/create";

  private final CrearEmpleadoRequest crearEmpleadoRequest;

  public CrearEmpleadoTask(CrearEmpleadoRequest crearEmpleadoRequest) {
    this.crearEmpleadoRequest = crearEmpleadoRequest;
  }

  public static Performable ingresarDatos(CrearEmpleadoRequest crearEmpleadoRequest) {
    return instrumented(CrearEmpleadoTask.class, crearEmpleadoRequest);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Post.to(ENDPOINT)
            .with(request -> request.contentType(ContentType.JSON).body(crearEmpleadoRequest)));
  }
}
