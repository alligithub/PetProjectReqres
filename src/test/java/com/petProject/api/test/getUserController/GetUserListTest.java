package com.petProject.api.test.getUserController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petProject.api.asserts.getUserListAssert.GetUserListAssert;
import com.petProject.api.models.getUsersListModel.response.DataItem;
import com.petProject.api.models.getUsersListModel.response.GetUsersListResponseModel;
import com.petProject.api.services.userControllerServices.UserControllerService;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.petProject.api.conditions.Conditions.bodyField;
import static com.petProject.api.conditions.Conditions.statusCode;
import static com.petProject.api.properties.BaseUrlLinks.REST_FULL_API_KEY;
import static com.petProject.api.properties.BaseUserProperties.*;
import static org.hamcrest.Matchers.*;

public class GetUserListTest {

    private String usersListDirectory = "src/main/java/com/petProject/resources/baselines/";
    private int firstPage = 1;
    private String firstPageFilePath = usersListDirectory + "users_page1.json";
    private int secondPage = 2;
    private String secondPageFilePath = usersListDirectory + "users_page2.json";

    private UserControllerService userControllerService = new UserControllerService();
    private GetUserListAssert getUserListAssert = new GetUserListAssert();
    private ObjectMapper mapper = new ObjectMapper();


    @DataProvider(name = "userPages")
    public Object[][] userPages() {
        return new Object[][]{
                {firstPage, firstPageFilePath},
                {secondPage, secondPageFilePath}
        };
    }

    @Test
    void getUserListFirstPageAndCheckWithAssertToClass() {

        GetUsersListResponseModel getUsersListResponseModel = userControllerService
                .getUserListByPage(firstPage, REST_FULL_API_KEY)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        getUserListAssert.getUserListFirstPageAssert(getUsersListResponseModel);
    }

    @Test
    void getUserListFirstPageWithoutToken() {

        userControllerService
                .getUserListByPage(firstPage, "")
                .shouldHave(statusCode(401),
                        bodyField("error", containsString("Missing API key")));
    }

    @Test
    void getUserListSecondPageAndCheckWithAssertToClass() {

        GetUsersListResponseModel getUsersListResponseModel = userControllerService
                .getUserListByPage(secondPage, REST_FULL_API_KEY)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        getUserListAssert.getUserListSecondPageAssert(getUsersListResponseModel);
    }

    @Test
    void getUserListFirstPageAndCheckWithStreamPath() {

        List<DataItem> getDataItem = userControllerService
                .getUserListByPage(firstPage, REST_FULL_API_KEY)
                .shouldHave(statusCode(200))
                .responseAsList("data", DataItem.class);

        DataItem dataItemResponse =
                getDataItem.stream().filter(email -> email.getEmail().equals(BASE_georgeBluthEmail)).findAny().get();

        Assert.assertEquals(dataItemResponse.getEmail(), BASE_georgeBluthEmail);
    }

    @Test
    void getUserListFirstPageAndCheckWithStreamByEmail() {

        GetUsersListResponseModel getUsersListResponseModel = userControllerService
                .getUserListByPage(firstPage, REST_FULL_API_KEY)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        DataItem dataItemResponse =
                getUsersListResponseModel.getData().stream().filter(email -> email.getEmail().equals(BASE_georgeBluthEmail)).findAny().get();

        Assert.assertEquals(dataItemResponse.getEmail(), BASE_georgeBluthEmail);
    }

    @Test
    void getUserListSecondPageAndCheckWithStreamByEmail() {

        GetUsersListResponseModel getUsersListResponseModel = userControllerService
                .getUserListByPage(secondPage, REST_FULL_API_KEY)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        DataItem dataItemResponse =
                getUsersListResponseModel.getData().stream().filter(email -> email.getEmail().equals(BASE_georgeEdwardsEmail)).findAny().get();

        Assert.assertEquals(dataItemResponse.getEmail(), BASE_georgeEdwardsEmail);
    }

    @Test
    void getUserListFirstPageAndCheckWithStreamById() {

        GetUsersListResponseModel getUsersListResponseModel = userControllerService
                .getUserListByPage(firstPage, REST_FULL_API_KEY)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        DataItem dataItemResponse =
                getUsersListResponseModel.getData().stream().filter(message -> Integer.toString(message.getId()).equals(String.valueOf(BASE_charlesMorrisId))).findAny().get();

        Assert.assertEquals(dataItemResponse.getFirstName(), BASE_charlesMorrisFirstName);
    }

    @Test
    void getUserListSecondPageAndCheckWithStreamById() {

        GetUsersListResponseModel getUsersListResponseModel = userControllerService
                .getUserListByPage(secondPage, REST_FULL_API_KEY)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        DataItem dataItemResponse =
                getUsersListResponseModel.getData().stream().filter(message -> Integer.toString(message.getId()).equals(String.valueOf(BASE_georgeEdwardsId))).findAny().get();

        Assert.assertEquals(dataItemResponse.getId(), BASE_georgeEdwardsId);
    }

    @Test
    void getUserListFirstPageAndCheckBodyFields() {
        userControllerService
                .getUserListByPage(firstPage, REST_FULL_API_KEY)
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

        GetUsersListResponseModel getUsersListResponseModel = userControllerService
                .getUserListByPage(firstPage, REST_FULL_API_KEY)
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

        GetUsersListResponseModel getUsersListResponseModel = userControllerService
                .getUserListByPage(firstPage, REST_FULL_API_KEY)
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
    public void getUserListFirstPageAndCheckWithJsonFileAndExceptions() throws Exception {
        GetUsersListResponseModel actualResponse = userControllerService
                .getUserListByPage(firstPage, REST_FULL_API_KEY)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        GetUsersListResponseModel expectedResponse =
                readModelFromFile(firstPageFilePath,
                        GetUsersListResponseModel.class);

        Assert.assertEquals(
                actualResponse,
                expectedResponse,
                "API response model does not match expected model from file"
        );
    }
    @Test
    public void getUserListSecondPageAndCheckWithJsonFileAndExceptions() throws Exception {
        GetUsersListResponseModel actualResponse = userControllerService
                .getUserListByPage(secondPage, REST_FULL_API_KEY)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        GetUsersListResponseModel expectedResponse =
                readModelFromFile(secondPageFilePath,
                        GetUsersListResponseModel.class);

        Assert.assertEquals(
                actualResponse,
                expectedResponse,
                "API response model does not match expected model from file"
        );
    }

    @Test(dataProvider = "userPages")
    void getUserListAllPagesAndCheckWithJsonFileAndDataProvider(int page, String expectedFilePath) throws IOException {
        GetUsersListResponseModel actualResponse = userControllerService
                .getUserListByPage(page, REST_FULL_API_KEY)
                .shouldHave(statusCode(200))
                .responseAs(GetUsersListResponseModel.class);

        GetUsersListResponseModel expectedResponse =
                readModelFromFile(expectedFilePath, GetUsersListResponseModel.class);

        Assert.assertEquals(
                actualResponse,
                expectedResponse,
                "API response for page " + page + " does not match expected model from file"
        );
    }

    @Test
    void getUserListAndCheckSpecificEmailInApiAndFileOnAnyPage() throws IOException {
        String targetEmail = BASE_georgeEdwardsEmail;

        // reuse your (page, filePath) pairs
        Object[][] pagesAndFiles = userPages();

        boolean emailMatchesOnSomePage = false;
        List<String> checkedPages = new ArrayList<>();

        for (Object[] row : pagesAndFiles) {
            int page = (Integer) row[0];
            String expectedFilePath = (String) row[1];

            checkedPages.add("page " + page + " (file: " + expectedFilePath + ")");

            GetUsersListResponseModel actualResponse = userControllerService
                    .getUserListByPage(page, REST_FULL_API_KEY)
                    .shouldHave(statusCode(200))
                    .responseAs(GetUsersListResponseModel.class);

            boolean emailInApi = actualResponse.getData().stream()
                    .anyMatch(user -> targetEmail.equals(user.getEmail()));

            GetUsersListResponseModel expectedResponse =
                    readModelFromFile(expectedFilePath, GetUsersListResponseModel.class);

            boolean emailInFile = expectedResponse.getData().stream()
                    .anyMatch(user -> targetEmail.equals(user.getEmail()));

            if (emailInApi && emailInFile) {
                emailMatchesOnSomePage = true;
                break;
            }
        }

        Assert.assertTrue(
                emailMatchesOnSomePage,
                "Email " + targetEmail +
                        " was not found in BOTH API response and file on any page. Checked:" +
                        checkedPages
        );
    }

    @Test
    void getUserListAndCheckSpecificUserIdInApiAndFileOnAnyPage() throws IOException {
        int targetUserId = BASE_michaelLawsonId;

        Object[][] pagesAndFiles = userPages();

        boolean userIdMatchesOnSomePage = false;
        List<String> checkedPages = new ArrayList<>();

        for (Object[] row : pagesAndFiles) {
            int page = (Integer) row[0];
            String expectedFilePath = (String) row[1];

            checkedPages.add("page " + page + " (file: " + expectedFilePath + ")");

            GetUsersListResponseModel actualResponse = userControllerService
                    .getUserListByPage(page, REST_FULL_API_KEY)
                    .shouldHave(statusCode(200))
                    .responseAs(GetUsersListResponseModel.class);

            boolean idInApi = actualResponse.getData().stream()
                    .anyMatch(user -> user.getId() == targetUserId);

            GetUsersListResponseModel expectedResponse =
                    readModelFromFile(expectedFilePath, GetUsersListResponseModel.class);

            boolean idInFile = expectedResponse.getData().stream()
                    .anyMatch(user -> user.getId() == targetUserId);

            if (idInApi && idInFile) {
                userIdMatchesOnSomePage = true;
                break;
            }
        }

        Assert.assertTrue(
                userIdMatchesOnSomePage,
                "User id " + targetUserId +
                        " was not found in BOTH API response and file on any page. Checked: " +
                        String.join(", ", checkedPages)
        );
    }


    @Test
    void getUserListAndCheckSpecificUserIdInApiAndFileOnAnyPageWithHashMap() throws IOException {
        int targetUserId = BASE_michaelLawsonId;

        Map<Integer, String> pagesAndFiles = new LinkedHashMap<>();
        pagesAndFiles.put(firstPage, firstPageFilePath);
        pagesAndFiles.put(secondPage, secondPageFilePath);

        boolean userIdMatchesOnSomePage = false;

        for (Map.Entry<Integer, String> entry : pagesAndFiles.entrySet()) {
            int page = entry.getKey();
            String expectedFilePath = entry.getValue();

            GetUsersListResponseModel actualResponse = userControllerService
                    .getUserListByPage(page, REST_FULL_API_KEY)
                    .shouldHave(statusCode(200))
                    .responseAs(GetUsersListResponseModel.class);

            boolean idInApi = actualResponse.getData().stream()
                    .anyMatch(user -> user.getId() == targetUserId);

            GetUsersListResponseModel expectedResponse =
                    readModelFromFile(expectedFilePath, GetUsersListResponseModel.class);

            boolean idInFile = expectedResponse.getData().stream()
                    .anyMatch(user -> user.getId() == targetUserId);

            if (idInApi && idInFile) {
                userIdMatchesOnSomePage = true;
                break;
            }
        }

        String checkedPages = pagesAndFiles.entrySet().stream()
                .map(e -> "page " + e.getKey() + " (file: " + e.getValue() + ")")
                .collect(Collectors.joining(", "));

        Assert.assertTrue(
                userIdMatchesOnSomePage,
                "User id " + targetUserId +
                        " was not found in BOTH API response and file on any page. Checked: " +
                        checkedPages
        );
    }

    private <T> T readModelFromFile(String filePath, Class<T> clazz) throws IOException {
        Path path = Paths.get(filePath);
        return mapper.readValue(path.toFile(), clazz);
    }
}
