# Prueba de Paola Ibañez



## requirements ##

 - Java JDK 1.8
 - Gradle 3.2.0 or higher
 - GIT 

## Compilation️

Once the project has been downloaded, import it into the IDE of preference.

It is important to compile the project for this, execute one of the following commands.
Run by console

```
gradle clean build -x test
gradle compileJava
```

## Execution

**Execution All tests**

To run all the project tests and generate the report you can use.

```
gradle clean test aggregate
```

**NOTE:** The report generated from the tests is generated in the route `/target/site/serenity/index.html`