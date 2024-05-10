Feature: User Login

  As an authenticated user
  I want to log in to the application
  So that I can access my account

  @Login-normal
  Scenario: Successful Login
    Given I am on the login page
    When I enter valid credentials
      | username | admin |
      | password | admin123 |
    And I click the login button
    Then I should be redirected to the landing page

  @Login-fail
  Scenario: Failed Login
    Given I am on the login page
    When I enter invalid credentials
      | username | invalidUser |
      | password | invalidPass |
    And I click the login button
    Then I should see an error message
    And I should remain on the login page

  @Login-multiple
  Scenario Outline: Login with different credentials
    Given I am on the login page
    When I enter the following credentials
      | username | <username> |
      | password | <password> |
    And I click the login button
    Then I should see the appropriate result

    Examples:
      | username | password  | result            |
      | admin    | admin123  | Successful Login  |
      | user1    | pass1     | Successful Login  |
      | invalidUser | invalidPass | Failed Login |
      |          | admin123  | Failed Login      |
      | admin    |           | Failed Login      |