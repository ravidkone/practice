// UserLoginSteps.java

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserLoginSteps {
    
    private RequestSpecification request;
    private Response response;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        RestAssured.baseURI = "https://example.com/api/login";
        request = RestAssured.given().contentType("application/json");
    }

    @When("the user enters the username {string} and password {string}")
    public void the_user_enters_the_username_and_password(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);
        request.body(loginRequest);
    }

    @When("the user enters an empty username and password")
    public void the_user_enters_an_empty_username_and_password() {
        LoginRequest loginRequest = new LoginRequest("", "");
        request.body(loginRequest);
    }

    @And("the user clicks on the submit button")
    public void the_user_clicks_on_the_submit_button() {
        response = request.post();
    }

    @Then("the user should be navigated to the home page")
    public void the_user_should_be_navigated_to_the_home_page() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(response.getBody().asString().contains("Welcome"));
    }

    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {
        Assert.assertEquals(401, response.getStatusCode());
        Assert.assertTrue(response.getBody().asString().contains("Error"));
    }
}