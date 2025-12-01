package com.petProject.api.services.userControllerServices;

import com.petProject.api.AssertableResponse;
import com.petProject.api.models.postUserModel.request.PostUserRequestModel;
import com.petProject.api.services.SetupApiService;
import io.restassured.response.Response;

import static com.petProject.api.properties.BaseUrlLinks.REST_FULL_API_KEY;

public class UserControllerService extends SetupApiService {


    public AssertableResponse getUserListByPage(int page, String token) {
        Response response =
                setXApiKeyHeader(token)
                        .when()
                        .get("/users?page="+ page)
                        .then()
                        .extract()
                        .response();

        return new AssertableResponse(response);
    }

    public AssertableResponse getUserById(int userId, String token) {
        Response response =
                setXApiKeyHeader(token)
                        .when()
                        .get("/users/"+ userId)
                        .then()
                        .extract()
                        .response();

        return new AssertableResponse(response);
    }

    public AssertableResponse postUser(PostUserRequestModel postUserRequestModel, String token) {
        Response response =
                setXApiKeyHeader(token)
                        .when()
                        .body(postUserRequestModel)
                        .post("/users")
                        .then()
                        .extract()
                        .response();

        return new AssertableResponse(response);
    }



}
