package com.vigatec.dataproviders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vigatec.models.users.User;
import com.vigatec.models.users.UserData;
import com.vigatec.utils.EnvironmentManager;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

public class TestDataProviders {

    @DataProvider(name = "getUserValid")
    public Object[][] getUserValid() {
        try {

            ObjectMapper mapper = new ObjectMapper();

            UserData userData = mapper.readValue(
                    new File("src/test/resources/testdata/users.json"),
                    UserData.class);

            Object[][] data = new Object[userData.getValidUsers().size()][2];

            for (int i = 0; i < userData.getValidUsers().size(); i++) {
                User user = userData.getValidUsers().get(i);
                data[i][0] = user.getUsername();
                data[i][1] = EnvironmentManager.resolvePassword(user.getPassword());
            }

            return data;

        } catch (IOException e) {
            throw new RuntimeException("Error cargando datos de usuarios: " + e.getMessage(), e);
        }
    }

    @DataProvider(name = "getUserInvalid")
    public Object[][] getInvalidUsers() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            UserData userData = mapper.readValue(
                    new File("src/test/resources/testdata/users.json"),
                    UserData.class);

            Object[][] data = new Object[userData.getInvalidUsers().size()][2];

            for (int i = 0; i < userData.getInvalidUsers().size(); i++) {
                User user = userData.getInvalidUsers().get(i);
                data[i][0] = user.getUsername();
                data[i][1] = EnvironmentManager.resolvePassword(user.getPassword());
            }

            return data;

        } catch (IOException e) {
            throw new RuntimeException("Error cargando datos de usuarios: " + e.getMessage(), e);
        }
    }
}
