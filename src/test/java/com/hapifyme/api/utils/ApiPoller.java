package com.hapifyme.api.utils;

import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;

import com.hapifyme.api.tests.BaseTest;

public class ApiPoller {


    public static void pollForStatus(String endpoint, String expectedStatus, String apiKey, String email) {
        System.out.println("Polling URL: " + endpoint + " expecting status: " + expectedStatus);

        await()
                .alias("Expecting status " + expectedStatus + " on " + endpoint)
                .atMost(20, SECONDS)
                .pollInterval(2, SECONDS)
                .untilAsserted(() -> {
                    given()
                            .spec(BaseTest.requestSpec)
                            .header("Authorization", apiKey)
                            .queryParam("username_or_email", email)
                            .when()
                            .get(endpoint)
                            .then()
                            .statusCode(200)
                            .body("status", equalTo(expectedStatus));
                });

        System.out.println("Status confirmed!");
    }
}
