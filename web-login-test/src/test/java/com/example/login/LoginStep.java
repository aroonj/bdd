package com.example.login;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.example.utils.WaitUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStep {

    private static final String LOGIN_URL="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private static final String LANDING_PAGE="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

    private WebDriver driver;

    @Before
    public void init(Scenario scenario){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Aroon Janthong\\Workspaces\\bdd\\WebDriver\\chromedriver-win64\\chromedriver.exe");
       driver = new ChromeDriver();
       
    }

     @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take a screenshot if the scenario fails
            // You can implement this logic if needed
            System.err.println("Test fail :"+scenario.getName());
        }
        System.out.println("Shutdown WebDriver");

        // Close the WebDriver instance
        driver.quit();
    }

    @Given("I am on the login page")
    public void navigateToLoginPage() {

        driver.get(LOGIN_URL);

        // Wait for completed load
        WaitUtils.waitForElementToBeVisible(driver, By.name("username"));
        
    }

    @When("I enter valid credentials")
    public void enterValidCredentials(io.cucumber.datatable.DataTable dataTable) {

        
        List<List<String>> credentials = dataTable.asLists();
        Assertions.assertNotNull(credentials,"Credential is null");
        Assertions.assertFalse(credentials.isEmpty(),"No credential");
    
        credentials.forEach(m->{
            System.out.printf("Credential %s\n",m.toString());
        });

        final String username=credentials.get(0).get(1);
        final String password=credentials.get(1).get(1);
        if(Objects.isNull(username) || username.isBlank()){
            Assertions.fail("Invalid username, null or empty");
        }
        if(Objects.isNull(password) || password.isBlank()){
            Assertions.fail("Invalid password, null or empty");
        }
        System.out.printf("Input Username: %s and Password: %s\s", username,password);

        final WebElement usernameElement=driver.findElement(By.cssSelector("input[name='username']"));
        final WebElement passwordElement=driver.findElement(By.name("password"));


        usernameElement.sendKeys(username);
        passwordElement.sendKeys(password);

        System.out.println("Setup username and password completed");

    }

    @When("I enter invalid credentials")
    public void enterInvalidCredentials(io.cucumber.datatable.DataTable dataTable) {

        List<List<String>> credentials = dataTable.asLists();
        Assertions.assertNotNull(credentials,"Credential is null");
        Assertions.assertFalse(credentials.isEmpty(),"No credential");
    
        credentials.forEach(m->{
            System.out.printf("Credential %s\n",m.toString());
        });

        final String username=credentials.get(0).get(1);
        final String password=credentials.get(1).get(1);
        if(Objects.isNull(username) || username.isBlank()){
            Assertions.fail("Invalid username, null or empty");
        }
        if(Objects.isNull(password) || password.isBlank()){
            Assertions.fail("Invalid password, null or empty");
        }
        System.out.printf("Input Username: %s and Password: %s\n", username,password);

        final WebElement usernameElement=driver.findElement(By.cssSelector("input[name='username']"));
        final WebElement passwordElement=driver.findElement(By.name("password"));


        usernameElement.sendKeys(username);
        passwordElement.sendKeys(password);

        System.out.println("Setup username and password completed");

    }

    @When("I enter the following credentials")
    public void enterCredentials(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        driver.findElement(By.name("username")).sendKeys(credentials.get(0).get("username"));
        driver.findElement(By.name("password")).sendKeys(credentials.get(0).get("password"));
    }

    @When("I click the login button")
    public void clickLoginButton() {
        
        WebElement submitElement=driver.findElement(By.cssSelector("button[type='submit']"));
        
        // WebElement submitElement=driver.findElement(By.cssSelector("button[type='submit']"));
        Assertions.assertNotNull(submitElement, "Find submit button not found");

        submitElement.click();
        

    }

    @Then("I should be redirected to the landing page")
    public void verifySuccessfulLogin() {
        // Add your assertions here to verify the landing page URL or elements
        final String landing=LANDING_PAGE;

        String url=driver.getCurrentUrl();
        Assertions.assertEquals(landing, url,"Landing URL does not matched");
        System.out.printf("Great landing URL is %s",url);

    }

    @Then("I should see an error message")
    public void verifyFailedLogin() {

        // Add your assertions here to verify the error message is displayed
        final String msg="Invalid credentials";
        By locator=By.tagName("p");
        final List<WebElement> parags=WaitUtils.waitForAllElementsToBeVisible(driver, locator);

        Assertions.assertFalse(parags.isEmpty(),"I can not found the HTML tag <p>");

        boolean isPresent=false;
        for(WebElement element:parags){
            if(msg.equals(element.getText())){
                isPresent=true;
                break;
            }
        }
        Assertions.assertTrue(isPresent,"Could not found the error message on fail login, "+msg);

    }

    @Then("I should see the appropriate result")
    public void verifyLoginResult(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expectedResults = dataTable.asMaps(String.class, String.class);
        // Add your assertions here to verify the appropriate result based on the test data
    }

    @Then("I should remain on the login page")
    public void verifyOnLoginPage() {
        // Add your assertions here to verify the current URL is the login page
        final String url=driver.getCurrentUrl();
        Assertions.assertEquals(LOGIN_URL, url,"Login URL is prefered");
    }


    

}
