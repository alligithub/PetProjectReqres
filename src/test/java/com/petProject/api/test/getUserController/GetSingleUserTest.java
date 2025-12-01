package com.petProject.api.test.getUserController;

import com.petProject.api.utilits.dataProvider.UserDataProvider;
import com.petProject.api.models.getSingleUserModel.response.Data;
import com.petProject.api.models.getSingleUserModel.response.GetSingleUserResponseModel;

import com.petProject.api.properties.BaseUserProperties;
import com.petProject.api.services.userControllerServices.UserControllerService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.petProject.api.conditions.Conditions.bodyField;
import static com.petProject.api.conditions.Conditions.statusCode;
import static com.petProject.api.properties.BaseUrlLinks.REST_FULL_API_KEY;
import static com.petProject.api.utilits.dataGenerator.DataGenerator.getFakerRandomIntNumberBetween;
import static org.hamcrest.Matchers.anEmptyMap;


public class GetSingleUserTest {

    private UserControllerService userControllerService = new UserControllerService();

    @Test
    void getRandomUserByIdAndCheckAllFields() {
        Data expectedUser = BaseUserProperties.getRandomUser();
        int targetId = expectedUser.getId();

        GetSingleUserResponseModel actualResponse = userControllerService
                .getUserById(targetId, REST_FULL_API_KEY)
                .shouldHave(statusCode(200))
                .responseAs(GetSingleUserResponseModel.class);

        Data actualUser = actualResponse.getData();

        Assert.assertEquals(actualUser.getId(), expectedUser.getId(), "Wrong id");
        Assert.assertEquals(actualUser.getEmail(), expectedUser.getEmail(), "Wrong email");
        Assert.assertEquals(actualUser.getFirst_name(), expectedUser.getFirst_name(), "Wrong firstName");
        Assert.assertEquals(actualUser.getLast_name(), expectedUser.getLast_name(), "Wrong lastName");
        Assert.assertEquals(actualUser.getAvatar(), expectedUser.getAvatar(), "Wrong avatar");

    }

    @Test
            (dataProvider = "usersById", dataProviderClass = UserDataProvider.class)
    void getUsersByIdAndCheckAllFieldsWithDataProvider(int userId, Data expectedUser) {

        GetSingleUserResponseModel actualResponse = userControllerService
                .getUserById(userId, REST_FULL_API_KEY)
                .shouldHave(statusCode(200))
                .responseAs(GetSingleUserResponseModel.class);

        Data actualUser = actualResponse.getData();

        Assert.assertEquals(actualUser.getId(), expectedUser.getId(), "Wrong id for userId=" + userId);
        Assert.assertEquals(actualUser.getEmail(), expectedUser.getEmail(), "Wrong email for userId=" + userId);
        Assert.assertEquals(actualUser.getFirst_name(), expectedUser.getFirst_name(), "Wrong firstName for userId=" + userId);
        Assert.assertEquals(actualUser.getLast_name(), expectedUser.getLast_name(), "Wrong lastName for userId=" + userId);
        Assert.assertEquals(actualUser.getAvatar(), expectedUser.getAvatar(), "Wrong avatar for userId=" + userId);
    }

    @Test
    void getSingleUserByIncorrectId(){

        int incorrectUserId = getFakerRandomIntNumberBetween(13, 103);

        userControllerService
                .getUserById(incorrectUserId, REST_FULL_API_KEY)
                .shouldHave(statusCode(404)
                        ,bodyField("$", anEmptyMap()));
    }

}
