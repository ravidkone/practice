// LoginRequest.java (Lombok class for Login Request Body)
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}

// LoginStepDefinitions.java
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.Assert.*;

public class LoginStepDefinitions {

    private Response response;
    private LoginRequest loginRequest;

    @Given("^User is at the login page$")
    public void userIsAtLoginPage() {
        RestAssured.baseURI = "https://example.com/api";
    }

    @When("^User enters valid username '(.*)' and password '(.*)'$")
    public void userEntersValidCredentials(String username, String password) {
        loginRequest = new LoginRequest(username, password);
        response = RestAssured.given().contentType("application/json").body(loginRequest).post("/login");
    }

    @When("^User enters invalid username '(.*)' and password '(.*)'$")
    public void userEntersInvalidCredentials(String username, String password) {
        loginRequest = new LoginRequest(username, password);
        response = RestAssured.given().contentType("application/json").body(loginRequest).post("/login");
    }

    @When("^User leaves both username and password fields empty$")
    public void userLeavesBothFieldsEmpty() {
        loginRequest = new LoginRequest("", "");
        response = RestAssured.given().contentType("application/json").body(loginRequest).post("/login");
    }

    @When("^User enters non-alphanumeric characters in username and password fields$")
    public void userEntersNonAlphanumericCharacters() {
        loginRequest = new LoginRequest("user!@#", "pass$$");
        response = RestAssured.given().contentType("application/json").body(loginRequest).post("/login");
    }

    @When("^User enters multiple spaces in username and password fields$")
    public void userEntersMultipleSpaces() {
        loginRequest = new LoginRequest("   ", "   ");
        response = RestAssured.given().contentType("application/json").body(loginRequest).post("/login");
    }

    @And("^User clicks on the login button$")
    public void userClicksOnLoginButton() {
        // Assuming the POST request is made here
    }

    @And("^User tries to submit$")
    public void userTriesToSubmit() {
        response = RestAssured.given().contentType("application/json").body(loginRequest).post("/login");
    }

    @Then("^User should be redirected to the home page$")
    public void userShouldBeRedirectedToHomePage() {
        assertEquals(200, response.getStatusCode());
    }

    @Then("^an error message should be displayed$")
    public void errorMessageShouldBeDisplayed() {
        assertEquals(401, response.getStatusCode());
    }

    @Then("^Submit button should be disabled$")
    public void submitButtonShouldBeDisabled() {
        // As this is an API test, we can't directly test UI elements.
        // We assume that the frontend will disable the button based on API response
        assertTrue(response.asString().contains("disabled"));
    }
}
