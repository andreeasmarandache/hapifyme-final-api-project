package com.hapifyme.api.tests;

import com.hapifyme.api.models.*;
import com.hapifyme.api.utils.ApiPoller;
import com.hapifyme.api.utils.DataGenerator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.hapifyme.api.tests.BaseTest.requestSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class UserLifecycleTest extends BaseTest {

    private String email;
    private String apiKey;
    private String userId;
    private String registeredUsername;
    private String bearerToken;
    private String firstName;
    private String lastName;
    private String password;

    @Test
    public void userEndToEndFlow() {
        generateRandomUserData();
        registerUser();
        String confirmationToken = retrieveConfirmationToken();
        confirmUser(confirmationToken);
        loginUser();
        getProfile();
        updateProfile();
        deleteProfile();
        negativeCheckProfile();
    }

    private void generateRandomUserData() {
        email = DataGenerator.randomEmail();
        firstName = DataGenerator.randomFirstName();
        lastName = DataGenerator.randomLastName();
        password = DataGenerator.randomPassword();
    }

    private void registerUser() {
        RegisterRequest registerRequest = new RegisterRequest(firstName, lastName, email, password);

        RegisterResponse response = given()
                .spec(requestSpec)
                .body(registerRequest)
                .post("/user/register.php")
                .then()
                .statusCode(201)
                .extract()
                .as(RegisterResponse.class);

        apiKey = response.getApiKey();
        userId = response.getUserId();
        registeredUsername = response.getUsername();
    }


    private String retrieveConfirmationToken() {
        ApiPoller.pollForStatus("/user/retrieve_token.php", "success", apiKey, email);

        String confirmationToken = given()
                .spec(requestSpec)
                .header("Authorization", apiKey)
                .queryParam("username_or_email", email)
                .get("/user/retrieve_token.php")
                .then()
                .statusCode(200)
                .extract()
                .path("confirmation_token");

        return confirmationToken;

    }

    private void confirmUser(String token) {
        given()
                .spec(requestSpec)
                .queryParam("token", token)
                .get("/user/confirm_email.php")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", equalTo("Email confirmed successfully. You can now log in."));
    }

    private void loginUser() {
        LoginRequest loginRequest = new LoginRequest(registeredUsername, password);

        LoginResponse response = given()
                .spec(requestSpec)
                .body(loginRequest)
                .post("/user/login.php")
                .then()
                .statusCode(200)
                .extract()
                .as(LoginResponse.class);

        bearerToken = response.getToken();
    }

    private void getProfile() {
        Response response = given()
                .spec(requestSpec)
                .header("Authorization", apiKey)
                .queryParam("user_id", userId)
                .get("/user/get_profile.php")
                .then()
                .statusCode(200)
                .extract().response();

        assertEquals(response.path("user.email"), email);
        assertEquals(response.path("user.username"), registeredUsername);
        assertEquals(response.path("user.first_name"), firstName);
        assertEquals(response.path("user.last_name"), lastName);
    }

    private void updateProfile() {
        String newFirstName = "UpdatedFirst";
        String newLastName = "UpdatedLast";
        String updateBody = String.format("{\"first_name\":\"%s\",\"last_name\":\"%s\"}", newFirstName, newLastName);

        given()
                .spec(requestSpec)
                .header("Authorization", apiKey) // exact cum merge în Postman
                .body(updateBody)
                .put("/user/update_profile.php")
                .then()
                .statusCode(200);

        firstName = newFirstName;
        lastName = newLastName;
    }

    private void deleteProfile() {
        given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + bearerToken)
                .delete("/user/delete_profile.php")
                .then()
                .statusCode(200);
    }

    private void negativeCheckProfile() {
        Response response = given()
                .spec(requestSpec)
                .header("Authorization", apiKey)
                .queryParam("user_id", userId)
                .get("/user/get_profile.php")
                .then()
                .statusCode(200)
                .extract().response();

        assertEquals(response.path("status"), "error");
        assertEquals(response.path("message"), "User not found.");
    }

}
