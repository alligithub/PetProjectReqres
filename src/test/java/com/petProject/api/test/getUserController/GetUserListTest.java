package com.petProject.api.test.getUserController;

import com.petProject.api.asserts.getUserListAssert.GetUserListAssert;
import com.petProject.api.models.getUsersListModel.response.DataItem;
import com.petProject.api.models.getUsersListModel.response.GetUsersListResponseModel;
import com.petProject.api.services.userControllerServices.UserControllerService;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.petProject.api.conditions.Conditions.bodyField;
import static com.petProject.api.conditions.Conditions.statusCode;
import static com.petProject.api.properties.BaseUserFirstPageProperties.*;
import static com.petProject.api.properties.BaseUserSecondPageProperties.BASE_georgeEdwardsEmail;
import static com.petProject.api.properties.BaseUserSecondPageProperties.BASE_georgeEdwardsId;
import static org.hamcrest.Matchers.*;

public class GetUserListTest {

    private int firstPage = 1;
    private int secondPage = 2;

    private UserControllerService userController = new UserControllerService();
    private GetUserListAssert getUserListAssert = new GetUserListAssert();

    @Test
    void getUserListFirstPageAndCheckWithAssertToClass() {

        GetUsersListResponseModel getUsersListResponseModel = userController
                .getUserListByPage(firstPage)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        getUserListAssert.getUserListFirstPageAssert(getUsersListResponseModel);
    }

    @Test
    void getUserListSecondPageAndCheckWithAssertToClass() {

        GetUsersListResponseModel getUsersListResponseModel = userController
                .getUserListByPage(secondPage)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        getUserListAssert.getUserListSecondPageAssert(getUsersListResponseModel);
    }

    @Test
    void getUserListFirstPageAndCheckWithStreamByEmail() {

        GetUsersListResponseModel getUsersListResponseModel = userController
                .getUserListByPage(firstPage)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        DataItem dataItemResponse =
                getUsersListResponseModel.getData().stream().filter(email -> email.getEmail().equals(BASE_georgeBluthEmail)).findAny().get();

        Assert.assertEquals(dataItemResponse.getEmail(), BASE_georgeBluthEmail);
    }

    @Test
    void getUserListSecondPageAndCheckWithStreamByEmail() {

        GetUsersListResponseModel getUsersListResponseModel = userController
                .getUserListByPage(secondPage)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        DataItem dataItemResponse =
                getUsersListResponseModel.getData().stream().filter(email -> email.getEmail().equals(BASE_georgeEdwardsEmail)).findAny().get();

        Assert.assertEquals(dataItemResponse.getEmail(), BASE_georgeEdwardsEmail);
    }

    @Test
    void getUserListFirstPageAndCheckWithStreamById() {

        GetUsersListResponseModel getUsersListResponseModel = userController
                .getUserListByPage(firstPage)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        DataItem dataItemResponse =
                getUsersListResponseModel.getData().stream().filter(message -> Integer.toString(message.getId()).equals(String.valueOf(BASE_charlesMorrisId))).findAny().get();

        Assert.assertEquals(dataItemResponse.getFirstName(), BASE_charlesMorrisFirstName);
    }

    @Test
    void getUserListSecondPageAndCheckWithStreamById() {

        GetUsersListResponseModel getUsersListResponseModel = userController
                .getUserListByPage(secondPage)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        DataItem dataItemResponse =
                getUsersListResponseModel.getData().stream().filter(message -> Integer.toString(message.getId()).equals(String.valueOf(BASE_georgeEdwardsId))).findAny().get();

        Assert.assertEquals(dataItemResponse.getId(), BASE_georgeEdwardsId);
    }

    @Test
    void getUserListFirstPageAndCheckBodyFields() {
        userController
                .getUserListByPage(firstPage)
                .shouldHave(statusCode(200),
                        bodyField("page", is(firstPage)),
                        bodyField("per_page", is(6)),
                        bodyField("total", is(12)),
                        bodyField("total_pages", is(2)),

                        bodyField("data[0].id", is(1)),
                        bodyField("data[0].email", containsString(BASE_georgeBluthEmail)),
                        bodyField("data[0].first_name", containsString(BASE_georgeBluthFirstName)),
                        bodyField("data[0].last_name", containsString(BASE_georgeBluthLastName)),
                        bodyField("data[0].avatar", containsString(BASE_georgeBluthAvatar)),

                        bodyField("data[1].id", is(2)),
                        bodyField("data[1].email", containsString(BASE_janetWeaverEmail)),
                        bodyField("data[1].first_name", containsString(BASE_janetWeaverFirstName)),
                        bodyField("data[1].last_name", containsString(BASE_janetWeaverLastName)),
                        bodyField("data[1].avatar", containsString(BASE_janetWeaverAvatar)),

                        bodyField("support.url", containsString(BASE_supportUrl)),
                        bodyField("support.text", containsString(BASE_supportText)));
    }

    @Test
    void getUserListFirstPageAndCheckWithAssert() {

        GetUsersListResponseModel getUsersListResponseModel = userController
                .getUserListByPage(firstPage)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        Assert.assertEquals(getUsersListResponseModel.getPage(), firstPage);
        Assert.assertEquals(getUsersListResponseModel.getPerPage(), 6);
        Assert.assertEquals(getUsersListResponseModel.getTotal(), 12);
        Assert.assertEquals(getUsersListResponseModel.getTotalPages(), 2);

        Assert.assertEquals(getUsersListResponseModel.getData().get(0).getId(), 1);
        Assert.assertEquals(getUsersListResponseModel.getData().get(0).getEmail(), BASE_georgeBluthEmail);
        Assert.assertEquals(getUsersListResponseModel.getData().get(0).getFirstName(), BASE_georgeBluthFirstName);
        Assert.assertEquals(getUsersListResponseModel.getData().get(0).getLastName(), BASE_georgeBluthLastName);
        Assert.assertEquals(getUsersListResponseModel.getData().get(0).getAvatar(), BASE_georgeBluthAvatar);

        Assert.assertEquals(getUsersListResponseModel.getData().get(1).getId(), 2);
        Assert.assertEquals(getUsersListResponseModel.getData().get(1).getEmail(), BASE_janetWeaverEmail);
        Assert.assertEquals(getUsersListResponseModel.getData().get(1).getFirstName(), BASE_janetWeaverFirstName);
        Assert.assertEquals(getUsersListResponseModel.getData().get(1).getLastName(), BASE_janetWeaverLastName);
        Assert.assertEquals(getUsersListResponseModel.getData().get(1).getAvatar(), BASE_janetWeaverAvatar);


        Assert.assertEquals(getUsersListResponseModel.getSupport().getUrl(), BASE_supportUrl);
        Assert.assertEquals(getUsersListResponseModel.getSupport().getText(), BASE_supportText);
    }

    @Test
    void getUserListFirstPageAndCheckWithSoftAssert() {

        GetUsersListResponseModel getUsersListResponseModel = userController
                .getUserListByPage(firstPage)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(getUsersListResponseModel.getPage(), firstPage);
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

    @Test
    void getUserListFirstPageAndCheckWithStreamPath() {

        List<DataItem> getDataItem = userController
                .getUserListByPage(firstPage)
                .shouldHave(statusCode(200))
                .responseAsList("data", DataItem.class);

        DataItem dataItemResponse =
                getDataItem.stream().filter(email -> email.getEmail().equals(BASE_georgeBluthEmail)).findAny().get();

        Assert.assertEquals(dataItemResponse.getEmail(), BASE_georgeBluthEmail);
    }

}
