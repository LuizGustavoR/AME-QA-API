package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import utils.ReadFile;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static utils.Constants.JSON_PATH;

public class Steps {

    RequestSpecification requestSpecification;
    Response response;

    @Before
    public void setup(){
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Given("that wants to create user")
    public void thatWantsToCreateUser() {

        String file = ReadFile.getFile(JSON_PATH + "user.json");
        JSONObject jsonObject = new JSONObject(file);

        requestSpecification = given().
                body(jsonObject.toString());

    }

    @When("make a post request")
    public void makeAPostRequest() {
        response = requestSpecification.when().
                post("/create");
    }

    @Then("return status {int}")
    public void returnStatus(int status) {
        response.then().
                statusCode(status);
    }

    @Given("that wants to search user")
    public void thatWantsToSearchUser() {
        requestSpecification = given();
    }

    @When("make a get request")
    public void makeAGetRequest() {
        response = requestSpecification.when().
                get("/employee/" + 2);
    }

    @Then("return user data")
    public void returnUserData() {

        String file = ReadFile.getFile(JSON_PATH + "garrett.json");
        JSONObject jsonObject = new JSONObject(file);

        response.then().assertThat().body("status", equalTo(jsonObject.get("status")));
        JSONObject jsonData = jsonObject.getJSONObject("data");
        response.then().assertThat().body("data.id", equalTo(jsonData.get("id")));
        response.then().assertThat().body("data.employee_name", equalTo(jsonData.get("employee_name")));
        response.then().assertThat().body("data.employee_salary", equalTo(jsonData.get("employee_salary")));
        response.then().assertThat().body("data.employee_age", equalTo(jsonData.get("employee_age")));
        response.then().assertThat().body("data.profile_image", equalTo(jsonData.get("profile_image")));
        response.then().assertThat().body("message", equalTo(jsonObject.get("message")));

    }

    @Given("that wants to delete user")
    public void thatWantsToDeleteUser() {
        requestSpecification = given();
    }

    @When("make a delete request")
    public void makeADeleteRequest() {
        response = requestSpecification.when().
                delete("/delete/" + 2);
    }

    @After
    public void after(){
        requestSpecification = null;
        response = null;
    }

}
