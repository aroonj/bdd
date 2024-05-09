package com.example.cachwithdraw;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefinitions {
    
    private final Actionwords actionwords = new Actionwords();

    @Given("the card is valid")
    public void theCardIsValid() {

        actionwords.theCardIsValid();
    }

    @Given("the machine contains enough money")
    public void theMachineContainsEnoughMoney() {
        actionwords.theMachineContainsEnoughMoney();
    }

    @Then("the card should be returned")
    public void theCardShouldBeReturned() {
        actionwords.theCardShouldBeReturned();
    }

    @Given("the account balance is {string}")
    public void theAccountBalanceIsBalance(String balance) {
        actionwords.theAccountBalanceIsBalance(balance);
    }

    @When("the Account Holder requests {string}")
    public void theAccountHolderRequestsAmount(String amount) {
        actionwords.theAccountHolderRequestsAmount(amount);
    }

    @Then("the ATM should dispense {string}")
    public void theATMShouldDispenseAmount(String amount) {
        actionwords.theATMShouldDispenseAmount(amount);
    }

    @Then("the account balance should be {string}")
    public void theAccountBalanceShouldBeBalance(String balance) {
        actionwords.theAccountBalanceShouldBeBalance(balance);
    }

    @Then("the ATM should not dispense any money")
    public void theATMShouldNotDispenseAnyMoney() {
        actionwords.theATMShouldNotDispenseAnyMoney();
    }

    @Then("the ATM should say there are insufficient funds")
    public void theATMShouldSayThereAreInsufficientFunds() {
        actionwords.theATMShouldSayThereAreInsufficientFunds();
    }

    @Given("the card is disabled")
    public void theCardIsDisabled() {
        actionwords.theCardIsDisabled();
    }

    @Then("the ATM should retain the card")
    public void theATMShouldRetainTheCard() {
        actionwords.theATMShouldRetainTheCard();
    }

    @Then("the ATM should say the card has been retained")
    public void theATMShouldSayTheCardHasBeenRetained() {
        actionwords.theATMShouldSayTheCardHasBeenRetained();
    }

    @Given("the savings account balance is {string}")
    public void theSavingsAccountBalanceIsAmount(String amount) {
        actionwords.theSavingsAccountBalanceIsAmount(amount);
    }

    @When("the Account Holder transfers {string} to the savings account")
    public void theAccountHolderTransfersAmountToTheSavingsAccount(String amount) {
        actionwords.theAccountHolderTransfersAmountToTheSavingsAccount(amount);
    }

    @When("the savings account balance should be {string}")
    public void theSavingsAccountBalanceShouldBeAmount(String amount) {
        actionwords.theSavingsAccountBalanceShouldBeAmount(amount);
    }
}