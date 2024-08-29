// Step Definition File: AuthenticationSteps.java

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class AuthenticationSteps {

    private RequestSpecification request;
    private Response response;

    @Given("^the user is on the login page$")
    public void the_user_is_on_login_page() {
        RestAssured.baseURI = "http://example.com/api/login";
        request = RestAssured.given().contentType("application/json");
    }

    @When("^the user enters a valid username and password$")
    public void the_user_enters_valid_credentials() {
        LoginBody loginBody = new LoginBody("validUser", "validPassword");
        response = request.body(loginBody).post();
    }

    @When("^the user enters an invalid username and password$")
    public void the_user_enters_invalid_credentials() {
        LoginBody loginBody = new LoginBody("invalidUser", "invalidPassword");
        response = request.body(loginBody).post();
    }

    @When("^the user enters an empty username and an empty password$")
    public void the_user_enters_empty_credentials() {
        LoginBody loginBody = new LoginBody("", "");
        response = request.body(loginBody).post();
    }

    @When("^the user inputs alphanumeric characters into the text boxes$")
    public void the_user_inputs_alphanumeric() {
        TextInputBody textInputBody = new TextInputBody("abc123");
        response = request.body(textInputBody).post("/input");
    }

    @When("^the user enters only spaces in the text boxes$")
    public void the_user_enters_only_spaces() {
        TextInputBody textInputBody = new TextInputBody("   ");
        response = request.body(textInputBody).post("/input");
    }

    @Then("^the user should be navigated to the home page$")
    public void user_navigated_to_home() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("^an error message should be displayed stating "([^"]*)"$")
    public void error_message_displayed(String message) {
        Assert.assertEquals(400, response.getStatusCode());
        Assert.assertTrue(response.asString().contains(message));
    }

    @Then("^the submit button should be disabled$")
    public void submit_button_disabled() {
        // Simulating this check as it would typically be done in UI testing
        Assert.assertEquals(422, response.getStatusCode());
    }

    @Then("^the input should be accepted without errors$")
    public void input_accepted_without_errors() {
        Assert.assertEquals(200, response.getStatusCode());
    }
}

@Data
@AllArgsConstructor
class LoginBody {
    private String username;
    private String password;
}

@Data
@AllArgsConstructor
class TextInputBody {
    private String inputText;
}