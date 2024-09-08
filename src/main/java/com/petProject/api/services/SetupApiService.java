package com.petProject.api.services;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static com.petProject.api.properties.BaseUrlLinks.REST_FULL_MAIN_URL;

public class SetupApiService {

    public RequestSpecification baseSetupHeaders(String... token) {
        RequestSpecification request = RestAssured.given()
                .baseUri(REST_FULL_MAIN_URL)
                .contentType(ContentType.JSON)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        if (token != null && token.length > 0) {
            request.header("Authorization", "JWT " + token[0]);
        }

        return request;
    }
}
