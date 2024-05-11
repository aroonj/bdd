# Guide

## โครงสร้าง

ใช้โครงสร้าง maven project ปกติ

## แนวคิด

1. หนึ่งไฟล์ หนึ่ง feature ซึ่งอาจมีหลาย Scenario
1. วาง features file ไว้ใน resources จะเป็น `src\test\resources` 
1. ควรแบ่งเป็น subdirectory เพื่อให้ง่ายต่อการแบ่งงาน
1. step class และ test class ควรวางไว้ใน package เดียวกัน
   * `step class` ใช้สำหรับจับคู่ test scenario ใน feature file `หนึ่ง method หนึ่ง step` และกำกับด้วย anotation เช่น `@Given(), @And(), @When() หรือ @Then()` step class เดียวอาจใช้ได้มากกว่าหนึ่ง feature
   * `test class` ซึ่งเป็น `junit version 5` สามารถทำงานร่วมกับ junit และ maven ได้เป็นอย่างดี
     

ตัวอย่าง Feature Web Login เขียนด้วยภาษา `Gherkin`

``` Gherkin

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
    Then I should see the appropriate <result>

    Examples:
      | username | password  | result            |
      | admin    | admin123  | Successful Login  |
      | user1    | pass1     | Failed Login  |
      | invalidUser | invalidPass | Failed Login |
      |          | admin123  | Failed Login      |
      | admin    |           | Failed Login      |
      
```


## Run test

[Download Selenium WebDriver](https://www.selenium.dev/downloads/) และติดตั้งในเครื่องตาม directory ที่ต้องการ เรียกว่า `$WEBDRIVE_CHROME_DEIVER` โดย `$WEBDRIVE_CHROME_DEIVER` ใช้แทน path เต็มของ WebDriver
เช่น `C:\Users\YOUR_HOME\Workspaces\bdd\WebDriver\chromedriver-win64\chromedriver.exe`

test all:

``` shell

mvn -D"webdriver.chrome.driver"=$WEBDRIVE_CHROME_DEIVER clean test
```

test Login Success:

``` shell  
mvn clean test -D"cucumber.filter.tags=@Login-normal" -D"webdriver.chrome.driver"=$WEBDRIVE_CHROME_DEIVER
```

test Login Fail:

``` shell  
mvn clean test -D"cucumber.filter.tags=@Login-fail" -D"webdriver.chrome.driver"=$WEBDRIVE_CHROME_DEIVER
```

## GenAI Prompt

``` text
create user story in Gherkin language for User Login and test scenario step start with goto URL "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login" follow by user input username and password when their click login button if verify credential success then goto landing URL if not goto login URL again
```

``` text
generate action step in java code from the given Gherkin above
```

``` text
list necessary maven dependency should include to this project
```

``` text
insert code for close the WebDriver after test scenario
```

``` text
how to config WebDriver wait before load all elements completed
```

### Summarize

``` text
What are difference meaning and action of keyword Given, When, And, and Then in Gherkin language. How does java testing framework execution each cucumber annotation
```
