package com.hapifyme.api.tests;

import com.hapifyme.api.utils.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    public static RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigManager.get("baseUrl"))
                .setContentType(ConfigManager.get("contentType"))
                .addHeader("Accept", ConfigManager.get("accept"))
                .build();
    }
}

