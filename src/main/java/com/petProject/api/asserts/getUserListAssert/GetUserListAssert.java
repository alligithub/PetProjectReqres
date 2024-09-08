package com.petProject.api.asserts.getUserListAssert;

import com.petProject.api.models.getUsersList.GetUsersListModel;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class GetUserListAssert {

    private String georgeBluthEmail = "george.bluth@reqres.in";
    public void getUserListAssert(GetUsersListModel getUsersListModel){

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(getUsersListModel.getPage(), 1);
        softAssert.assertEquals(getUsersListModel.getPerPage(), 6);
        softAssert.assertEquals(getUsersListModel.getTotal(), 12);
        softAssert.assertEquals(getUsersListModel.getTotalPages(), 2);

        softAssert.assertEquals(getUsersListModel.getData().get(0).getId(), 1);
        softAssert.assertEquals(getUsersListModel.getData().get(0).getEmail(), georgeBluthEmail);
        softAssert.assertEquals(getUsersListModel.getData().get(0).getFirstName(), "George");
        softAssert.assertEquals(getUsersListModel.getData().get(0).getLastName(), "Bluth");
        softAssert.assertEquals(getUsersListModel.getData().get(0).getAvatar(), "https://reqres.in/img/faces/1-image.jpg");

        softAssert.assertEquals(getUsersListModel.getData().get(1).getId(), 2);
        softAssert.assertEquals(getUsersListModel.getData().get(1).getEmail(), "janet.weaver@reqres.in");

        softAssert.assertEquals(getUsersListModel.getSupport().getUrl(), "https://reqres.in/#support-heading");
        softAssert.assertEquals(getUsersListModel.getSupport().getText(), "To keep ReqRes free, contributions towards server costs are appreciated!");

        softAssert.assertAll();

    }
}
