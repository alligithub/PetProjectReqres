package com.petProject.api.test.postUserController;

import com.petProject.api.models.postUserModel.request.PostUserRequestModel;
import com.petProject.api.models.postUserModel.response.PostUserResponseModel;
import com.petProject.api.services.userControllerServices.UserControllerService;
import org.testng.annotations.Test;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static com.petProject.api.conditions.Conditions.statusCode;
import static com.petProject.api.properties.BaseUrlLinks.REST_FULL_API_KEY;
import static com.petProject.api.utilits.dataGenerator.DataGenerator.*;
import static org.testng.Assert.assertEquals;

public class PostUserTest {

    UserControllerService userControllerService = new UserControllerService();

    @Test
    void postUserWithValidData() {

        PostUserRequestModel postUserRequestModel = new PostUserRequestModel()
                .setName(getFakerFirstName())
                .setJob(getFakerJob());

        PostUserResponseModel postUserResponseModel = userControllerService.postUser(postUserRequestModel, REST_FULL_API_KEY)
                .shouldHave(statusCode(201))
                .responseAs(PostUserResponseModel.class);

        assertEquals(postUserResponseModel.getName(), postUserRequestModel.getName());
        assertEquals(postUserResponseModel.getJob(), postUserRequestModel.getJob());

        //Reformat createdAt from ISO-8601 --> "yyyy-MM-dd HH:mm:ss"
        String rawCreatedAt = postUserResponseModel.getCreatedAt();
        //Parse ISO-8601 string
        OffsetDateTime createdAt = OffsetDateTime.parse(rawCreatedAt);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedCreatedAt = createdAt.format(formatter);

        //Expected time in the same format and timezone
        String expectedTime = getCurrentTimeByTimeZone("yyyy-MM-dd HH:mm:ss", "UTC");

        assertEquals(
                formattedCreatedAt,
                expectedTime,
                "createdAt does not match current UTC time. " + "actual=" + formattedCreatedAt + ", expected=" + expectedTime
        );
    }
}
