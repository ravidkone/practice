import lombok.Data;

@Data
public class LoginCredentials {
    private String username;
    private String password;

    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.Assert.*;

public class LoginSteps {
    
    private Response response;
    private LoginCredentials credentials;

    @Given("the user is on the login page")
    public void the_user_is_on_login_page() {
        RestAssured.baseURI = "http://example.com/api/login";
    }
    
    @When("the user enters the valid username {string} and password {string}")
    public void the_user_enters_valid_credentials(String username, String password) {
        credentials = new LoginCredentials(username, password);
    }
    
    @When("the user enters an invalid username {string} and password {string}")
    public void the_user_enters_invalid_credentials(String username, String password) {
        credentials = new LoginCredentials(username, password);
    }
    
    @When("the user leaves the username and password fields empty")
    public void the_user_leaves_fields_empty() {
        credentials = new LoginCredentials("", "");
    }
    
    @And("the user clicks on the submit button")
    public void the_user_clicks_submit() {
        response = RestAssured.given().contentType("application/json").body(credentials).post();
    }
    
    @Then("the user should be navigated to the home page")
    public void user_navigated_to_home() {
        assertEquals(200, response.getStatusCode());
    }
    
    @Then("an error message should be displayed")
    public void error_message_displayed() {
        assertEquals(401, response.getStatusCode());
    }
    
    @When("the user leaves both the username and password fields empty")
    public void the_user_leaves_both_fields_empty() {
        credentials = new LoginCredentials("", "");
    }
    
    @Then("the submit button should be in a disabled state")
    public void submit_button_disabled() {
        // UI state check, assuming API response mimics UI behavior
        assertTrue(response.getBody().asString().contains("disabled"));
    }
    
    @When("the user enters a non-alphanumeric username {string}")
    public void non_alphanumeric_username(String username) {
        credentials = new LoginCredentials(username, "validPassword");
    }
    
    @When("the user enters a non-alphanumeric password {string}")
    public void non_alphanumeric_password(String password) {
        credentials = new LoginCredentials("validUsername", password);
    }
    
    @When("the user enters a space in the username field")
    public void space_in_username() {
        credentials = new LoginCredentials(" ", "validPassword");
    }
    
    @When("the user enters a space in the password field")
    public void space_in_password() {
        credentials = new LoginCredentials("validUsername", " ");
    }
    
    @When("the user continuously inputs alphanumeric characters {string} in the username field and {string} in the password field")
    public void continuous_alphanumeric_input(String username, String password) {
        credentials = new LoginCredentials(username, password);
    }
    
    @When("the user enters a single character {string} in the username field and {string} in the password field")
    public void single_character_input(String username, String password) {
        credentials = new LoginCredentials(username, password);
    }
    
    @Then("the inputs should be accepted without error")
    public void inputs_accepted_without_error() {
        assertEquals(200, response.getStatusCode());
    }
    
    @Then("an error message should be displayed regarding the username")
    public void error_username() {
        assertTrue(response.getBody().asString().contains("username error"));
    }
    
    @Then("an error message should be displayed regarding the password")
    public void error_password() {
        assertTrue(response.getBody().asString().contains("password error"));
    }
}