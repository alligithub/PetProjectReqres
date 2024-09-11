package com.petProject.api.asserts.getUserListAssert;

import com.petProject.api.models.getUsersListModel.response.GetUsersListResponseModel;
import org.testng.asserts.SoftAssert;

import static com.petProject.api.properties.BaseUserFirstPageProperties.*;
import static com.petProject.api.properties.BaseUserFirstPageProperties.BASE_supportText;
import static com.petProject.api.properties.BaseUserFirstPageProperties.BASE_supportUrl;
import static com.petProject.api.properties.BaseUserSecondPageProperties.*;


public class GetUserListAssert {

    public void getUserListFirstPageAssert(GetUsersListResponseModel getUsersListResponseModel){

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(getUsersListResponseModel.getPage(), 1);
        softAssert.assertEquals(getUsersListResponseModel.getPerPage(), 6);
        softAssert.assertEquals(getUsersListResponseModel.getTotal(), 12);
        softAssert.assertEquals(getUsersListResponseModel.getTotalPages(), 2);

        softAssert.assertEquals(getUsersListResponseModel.getData().get(0).getId(), 1);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(0).getEmail(), BASE_georgeBluthEmail);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(0).getFirstName(), BASE_georgeBluthFirstName);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(0).getLastName(), BASE_georgeBluthLastName);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(0).getAvatar(), BASE_georgeBluthAvatar);

        softAssert.assertEquals(getUsersListResponseModel.getData().get(1).getId(), 2);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(1).getEmail(), BASE_janetWeaverEmail);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(1).getFirstName(), BASE_janetWeaverFirstName);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(1).getLastName(), BASE_janetWeaverLastName);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(1).getAvatar(), BASE_janetWeaverAvatar);

        softAssert.assertEquals(getUsersListResponseModel.getSupport().getUrl(), BASE_supportUrl);
        softAssert.assertEquals(getUsersListResponseModel.getSupport().getText(), BASE_supportText);

        softAssert.assertAll();

    }

    public void getUserListSecondPageAssert(GetUsersListResponseModel getUsersListResponseModel){

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(getUsersListResponseModel.getPage(), 2);
        softAssert.assertEquals(getUsersListResponseModel.getPerPage(), 6);
        softAssert.assertEquals(getUsersListResponseModel.getTotal(), 12);
        softAssert.assertEquals(getUsersListResponseModel.getTotalPages(), 2);

        softAssert.assertEquals(getUsersListResponseModel.getData().get(0).getId(), 7);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(0).getEmail(), BASE_michaelLawsonEmail);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(0).getFirstName(), BASE_michaelLawsonFirstName);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(0).getLastName(), BASE_michaelLawsonLastName);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(0).getAvatar(), BASE_michaelLawsonAvatar);

        softAssert.assertEquals(getUsersListResponseModel.getData().get(1).getId(), 8);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(1).getEmail(), BASE_lindsayFergusonEmail);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(1).getFirstName(), BASE_lindsayFergusonFirstName);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(1).getLastName(), BASE_lindsayFergusonLastName);
        softAssert.assertEquals(getUsersListResponseModel.getData().get(1).getAvatar(), BASE_lindsayFergusonAvatar);

        softAssert.assertEquals(getUsersListResponseModel.getSupport().getUrl(), BASE_supportUrl);
        softAssert.assertEquals(getUsersListResponseModel.getSupport().getText(), BASE_supportText);

        softAssert.assertAll();

    }
}
