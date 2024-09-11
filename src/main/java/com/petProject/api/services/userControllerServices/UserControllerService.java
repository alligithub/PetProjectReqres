package com.petProject.api.services.userControllerServices;

import com.petProject.api.AssertableResponse;
import com.petProject.api.services.SetupApiService;
import io.restassured.response.Response;

public class UserControllerService extends SetupApiService {


    public AssertableResponse getUserListByPage(int page) {
        Response response =
                baseSetupHeaders()
                        .when()
                        .get("/api/users?page="+ page)
                        .then()
                        .extract()
                        .response();

        return new AssertableResponse(response);
    }



}
