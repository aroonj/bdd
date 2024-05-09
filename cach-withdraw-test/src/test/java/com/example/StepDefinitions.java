package com.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

    @Given("an example scenario")
    public void anExampleScenario() {
        System.out.print("1: ");
    }

    @When("all step definitions are implemented")
    public void allStepDefinitionsAreImplemented() {
        System.out.println("2: ");
    }

    @Then("the scenario passes")
    public void theScenarioPasses() {
        System.out.print("3: ");
    }

}
