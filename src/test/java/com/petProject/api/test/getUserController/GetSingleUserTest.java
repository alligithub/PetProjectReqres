package com.petProject.api.test.getUserController;

import com.petProject.api.asserts.getSingleUserAssert.GetSingleUserAssert;
import com.petProject.api.models.getSingleUserModel.response.GetSingleUserResponseModel;
import com.petProject.api.services.userControllerServices.UserControllerService;
import org.testng.annotations.Test;

import static com.petProject.api.conditions.Conditions.statusCode;
import static com.petProject.api.properties.BaseUrlLinks.REST_FULL_API_KEY;
import static com.petProject.api.properties.BaseUserProperties.BASE_georgeEdwardsId;

public class GetSingleUserTest {

    private int userId = BASE_georgeEdwardsId;

    private UserControllerService userController = new UserControllerService();
    private GetSingleUserAssert getSingleUserAssert = new GetSingleUserAssert();

    @Test
    void getSingleUserAndCheckWithAssertToClass() {
        // check with randomizer from 1 to 12 id's range
        // check with dataProvider for each user and compare with JSON file
        // check with HashMap

        GetSingleUserResponseModel getSingleUserResponseModel = userController
                .getUserListById(userId, REST_FULL_API_KEY)
                .shouldHave(statusCode(200))
                .responseAs(GetSingleUserResponseModel.class);

        getSingleUserAssert.getSingleUserAssert(userId, getSingleUserResponseModel);

    }
}
