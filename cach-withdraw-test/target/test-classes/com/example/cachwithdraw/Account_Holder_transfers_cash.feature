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
