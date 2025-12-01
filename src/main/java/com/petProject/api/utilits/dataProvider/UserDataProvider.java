package com.petProject.api.utilits.dataProvider;

import com.petProject.api.models.getSingleUserModel.response.Data;
import com.petProject.api.properties.BaseUserProperties;
import org.testng.annotations.DataProvider;

import java.util.Map;

public class UserDataProvider {

    @DataProvider(name = "userPages")
    public Object[][] userPages() {
        return new Object[][]{
                {1, 3},
                {2, 4}
        };
    }

    @DataProvider(name = "usersById")
    public Object[][] usersById() {
        Map<Integer, Data> users = BaseUserProperties.getAllUsers();

        Object[][] data = new Object[users.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Data> entry : users.entrySet()) {
            data[i][0] = entry.getKey();      // userId
            data[i][1] = entry.getValue();    // expected DataItem
            i++;
        }
        return data;
    }
}
