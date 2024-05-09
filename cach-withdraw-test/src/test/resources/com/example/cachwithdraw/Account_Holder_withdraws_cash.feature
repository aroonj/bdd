Feature: Account Holder withdraws cash
    "**In order to** get money when the bank is closed"
    "**As** an Account Holder"
    "**I want to** withdraw cash from an ATM"

  @Priority-high
  Scenario Outline: Account has sufficient funds
    Given the account balance is "$100"
    And the machine contains enough money
    And the card is valid
    When the Account Holder requests "<amount>"
    Then the ATM should dispense "<amount>"
    And the account balance should be "<ending_balance>"
    And the card should be returned

    Examples:
      | ending_balance | amount |
      | $0 | $100 |
      | $50 | $50 |
      | $80 | $20 |

  @Priority-high
  Scenario: Account has insufficient funds
    Given the account balance is "$10"
    And the card is valid
    And the machine contains enough money
    When the Account Holder requests "$20"
    Then the ATM should not dispense any money
    And the ATM should say there are insufficient funds

  @Priority-high
  Scenario: Card has been disabled
    Given the card is disabled
    When the Account Holder requests "$10"
    Then the ATM should retain the card
    And the ATM should say the card has been retained
