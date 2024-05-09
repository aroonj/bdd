# Guide

## โครงสร้าง

ใช้โครงสร้าง maven project ปกติ

## แนวคิด

### features files

1. หนึ่งไฟล์ หนึ่ง feature ซึ่งอาจมีหลาย Scenario
1. วาง features file ไว้ใน resources จะเป็น `src\test\resources` หรือ `src\main\resources` ก็ได้
1. ควรแบ่งเป็น subdirectory เพื่อให้ง่ายต่อการแบ่งงาน
1. step class และ test class ควรวางไว้ใน package เดียวกัน
   * `step class` ใช้สำหรับจับคู่ test scenario ใน feature file `หนึ่ง method หนึ่ง step` และกำกับด้วย anotation เช่น `@Given(), @And(), @When() หรือ @Then()` step class เดียวอาจใช้ได้มากกว่าหนึ่ง feature
   * `test class` ซึ่งเป็น `junit version 5` สามารถทำงานร่วมกับ junit และ maven ได้เป็นอย่างดี
     

ตัวอย่าง Feature file เขียนด้วยภาษา `Gherkin`

``` Gherkin

Feature: Account Holder transfers cash
    "**In order to** transfer money between accounts"
    "**As** an Account Holder"
    "**I want to** transfer cash from an ATM"

  @Priority-medium
  Scenario: Account has sufficient funds for transferring cash
    Given the account balance is "$100"
    And the savings account balance is "$1000"
    And the card is valid
    When the Account Holder transfers "$20" to the savings account
    And the ATM should dispense "$0"
    And the account balance should be "$80"
    And the savings account balance should be "$1020"
    And the card should be returned

```

ตัวอย่าง step class

``` java

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

```

ตัวอย่าง Test class

``` java

@Suite
@IncludeEngines("cucumber")
@SelectPackages("com.example.cachwithdraw")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:target/cach_ransfer.json, html:target/cach_transfer.html")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/com/example/cachwithdraw/Account_Holder_transfers_cash.feature")
@ConfigurationParameter(key=GLUE_PROPERTY_NAME,value="com.example.cachwithdraw")
public class RunCucumberCachTransferTest {
}

```

## Run test

test all:

``` shell  
mvn clean test 
```

test Cach Withdraw:

``` shell  
mvn clean test -Dtest="com.example.cachwithdraw.RunCucumberCachWithdrawTest"
```

test Cach Transfer:

``` shell  
mvn clean test -Dtest="com.example.cachwithdraw.RunCucumberCachTransferTest"
```