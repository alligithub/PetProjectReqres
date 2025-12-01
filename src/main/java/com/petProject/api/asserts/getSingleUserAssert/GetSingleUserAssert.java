package com.petProject.api.asserts.getSingleUserAssert;

import com.petProject.api.models.getSingleUserModel.response.GetSingleUserResponseModel;
import org.testng.asserts.SoftAssert;

import static com.petProject.api.properties.BaseUserProperties.*;
import static com.petProject.api.properties.BaseUserProperties.BASE_supportText;


public class GetSingleUserAssert {

    public void getSingleUserAssert(int userId, GetSingleUserResponseModel getSingleUserResponseModel){

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(getSingleUserResponseModel.getData().getId(), userId);
        softAssert.assertEquals(getSingleUserResponseModel.getData().getEmail(), BASE_georgeEdwardsEmail);
        softAssert.assertEquals(getSingleUserResponseModel.getData().getFirst_name(), BASE_georgeEdwardsFirstName);
        softAssert.assertEquals(getSingleUserResponseModel.getData().getLast_name(), BASE_georgeEdwardsLastName);
        softAssert.assertEquals(getSingleUserResponseModel.getData().getAvatar(), BASE_georgeEdwardsAvatar);

        softAssert.assertEquals(getSingleUserResponseModel.getSupport().getUrl(), BASE_supportUrl);
        softAssert.assertEquals(getSingleUserResponseModel.getSupport().getText(), BASE_supportText);

        softAssert.assertAll();

    }
}
