package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LoginSteps {

    @Given("User is on the Login Page")
    public void userIsOnTheLoginPage() {
        RestAssured.baseURI = "http://example.com";
    }

    @When("User enters valid username and password")
    public void userEntersValidUsernameAndPassword() {
        given().auth().basic("validUser", "validPass").when().get("/login").then().statusCode(200);
    }

    @Then("User is navigated to the home page")
    public void userIsNavigatedToTheHomePage() {
        get("/home").then().body("title", equalTo("Home"));
    }

    @When("User enters invalid username and password")
    public void userEntersInvalidUsernameAndPassword() {
        given().auth().basic("invalidUser", "invalidPass").when().get("/login").then().assertThat().statusCode(403);
    }

    @Then("Error message is displayed")
    public void errorMessageIsDisplayed() {
        get("/login").then().body("error", equalTo("Invalid credentials"));
    }

    @When("User enters empty username and password")
    public void userEntersEmptyUsernameAndPassword() {
        given().auth().basic("", "").when().get("/login").then().assertThat().statusCode(400);
    }

    @And("Submit button is in disable state")
    public void submitButtonIsInDisableState() {
        get("/login").then().body("submitButton", equalTo("disabled"));
    }

    @Given("User types into input text boxes")
    public void userTypesIntoInputTextBoxes() {
        given().contentType("application/x-www-form-urlencoded").body("username=validUser&password=validPass");
    }

    @Then("Input boxes accept only alphanumeric values")
    public void inputBoxesAcceptOnlyAlphanumericValues() {
        post("/validateInput").then().body("inputValid", equalTo(true));
    }

    @When("User enters empty space in input text boxes")
    public void userEntersEmptySpaceInInputTextBoxes() {
        given().contentType("application/x-www-form-urlencoded").body("username= &password= ").post("/validateInput").then().assertThat().body("inputValid", equalTo(false));
    }

    @Then("Error message for empty space is shown")
    public void errorMessageForEmptySpaceIsShown() {
        post("/login").then().body("error", equalTo("Input cannot be empty"));
    }
}