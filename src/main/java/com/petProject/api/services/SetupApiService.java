package com.petProject.api.services;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static com.petProject.api.properties.BaseUrlLinks.REST_FULL_API_KEY;
import static com.petProject.api.properties.BaseUrlLinks.REST_FULL_MAIN_URL;

public class SetupApiService {

    private RequestSpecification baseSetupHeaders(){
        return RestAssured.given()
                .baseUri(REST_FULL_MAIN_URL)
                .contentType(ContentType.JSON)
//                .header("X-Requested-With", "XMLHttpRequest")
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Step
    public RequestSpecification setXApiKeyHeader(String... apiKey){
        if(apiKey.length == 0)
            return baseSetupHeaders()
                    .header("x-api-key", REST_FULL_API_KEY);
        else
            return baseSetupHeaders()
                    .header("x-api-key", apiKey[0]);
    }
}

//    public RequestSpecification baseSetupHeaders(String... token) {
//        RequestSpecification request = RestAssured.given()
//                .baseUri(REST_FULL_MAIN_URL)
//                .contentType(ContentType.JSON)
//                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
//
//        if (token != null && token.length > 0) {
//            request.header("Authorization", "JWT " + token[0]);
//        }
