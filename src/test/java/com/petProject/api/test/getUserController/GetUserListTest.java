package com.petProject.api.test.getUserController;

import com.petProject.api.asserts.getUserListAssert.GetUserListAssert;
import com.petProject.api.models.getUsersList.DataItem;
import com.petProject.api.models.getUsersList.GetUsersListModel;
import com.petProject.api.services.userControllerServices.UserControllerService;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.petProject.api.conditions.Conditions.bodyField;
import static com.petProject.api.conditions.Conditions.statusCode;
import static org.hamcrest.Matchers.*;

public class GetUserListTest {

    private String georgeBluthEmail = "george.bluth@reqres.in";

    private UserControllerService userController = new UserControllerService();
    private GetUserListAssert getUserListAssert = new GetUserListAssert();

    @Test
    void getUserListAndCheckBodyFields() {
        userController
                .getUser()
                .shouldHave(statusCode(200),
                        bodyField("page", is(1)),
                        bodyField("per_page", is(6)),
                        bodyField("total", is(12)),
                        bodyField("total_pages", is(2)),

                        bodyField("data[0].id", is(1)),
                        bodyField("data[0].email", containsString(georgeBluthEmail)),
                        bodyField("data[0].first_name", containsString("George")),
                        bodyField("data[0].last_name", containsString("Bluth")),
                        bodyField("data[0].avatar", containsString("https://reqres.in/img/faces/1-image.jpg")),

                        bodyField("data[1].id", is(2)),
                        bodyField("data[1].email", containsString("janet.weaver@reqres.in")),

                        bodyField("support.url", containsString("https://reqres.in/#support-heading")),
                        bodyField("support.text", containsString("To keep ReqRes free, contributions towards server costs are appreciated!")));

    }

    @Test
    void getUserListAndCheckWithAssert() {

        GetUsersListModel getUsersListModel = userController
                .getUser()
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListModel.class);

        Assert.assertEquals(getUsersListModel.getPage(), 1);
        Assert.assertEquals(getUsersListModel.getPerPage(), 6);
        Assert.assertEquals(getUsersListModel.getTotal(), 12);
        Assert.assertEquals(getUsersListModel.getTotalPages(), 2);

        Assert.assertEquals(getUsersListModel.getData().get(0).getId(), 1);
        Assert.assertEquals(getUsersListModel.getData().get(0).getEmail(), georgeBluthEmail);
        Assert.assertEquals(getUsersListModel.getData().get(0).getFirstName(), "George");
        Assert.assertEquals(getUsersListModel.getData().get(0).getLastName(), "Bluth");
        Assert.assertEquals(getUsersListModel.getData().get(0).getAvatar(), "https://reqres.in/img/faces/1-image.jpg");

        Assert.assertEquals(getUsersListModel.getData().get(1).getId(), 2);
        Assert.assertEquals(getUsersListModel.getData().get(1).getEmail(), "janet.weaver@reqres.in");

        Assert.assertEquals(getUsersListModel.getSupport().getUrl(), "https://reqres.in/#support-heading");
        Assert.assertEquals(getUsersListModel.getSupport().getText(),"To keep ReqRes free, contributions towards server costs are appreciated!");

    }

    @Test
    void getUserListAndCheckWithSoftAssert() {

        GetUsersListModel getUsersListModel = userController
                .getUser()
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListModel.class);

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
        softAssert.assertEquals(getUsersListModel.getSupport().getText(),"To keep ReqRes free, contributions towards server costs are appreciated!");

        softAssert.assertAll();

    }

    @Test
    void getUserListAndCheckWithAssertToClass() {

        GetUsersListModel getUsersListModel = userController
                .getUser()
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListModel.class);

        getUserListAssert.getUserListAssert(getUsersListModel);

    }

    @Test
    void getUserListAndCheckWithStreamPath() {


        List<DataItem> getDataItem = userController
                .getUser()
                .shouldHave(statusCode(200))
                .responseAsList("data", DataItem.class);

        DataItem dataItemResponse =
                getDataItem.stream().filter(email -> email.getEmail().equals(georgeBluthEmail)).findAny().get();

        Assert.assertEquals(dataItemResponse.getEmail(), georgeBluthEmail);

    }

    @Test
    void getUserListAndCheckWithStreamByEmail() {

        GetUsersListModel getUsersListModel = userController
                .getUser()
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListModel.class);

        DataItem dataItemResponse =
                getUsersListModel.getData().stream().filter(email -> email.getEmail().equals(georgeBluthEmail)).findAny().get();

        Assert.assertEquals(dataItemResponse.getEmail(), georgeBluthEmail);

    }

    @Test
    void getUserListAndCheckWithStreamById() {

        GetUsersListModel getUsersListModel = userController
                .getUser()
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListModel.class);

        DataItem dataItemResponse =
                getUsersListModel.getData().stream().filter(message ->
                        Integer.toString(message.getId()).equals(String.valueOf(5))).findAny().get();

//        Assert.assertEquals(dataItemResponse.getId(), 5);
        Assert.assertEquals(dataItemResponse.getFirstName(), "Charles");

    }
}
