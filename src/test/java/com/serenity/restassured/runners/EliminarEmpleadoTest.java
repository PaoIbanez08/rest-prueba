package com.serenity.restassured.runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features/eliminarEmpleado.feature",
    glue = {"com.serenity.restassured.stepdefinitions"})
public class EliminarEmpleadoTest {}
