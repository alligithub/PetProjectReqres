package com.petProject.api.properties;



import com.petProject.api.models.getSingleUserModel.response.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class BaseUserProperties {

    public static final String usersListDirectory = "src/main/java/com/petProject/resources/baselines/";

    private static final Map<Integer, Data> USER_BY_ID = new HashMap<>();

    static {
        USER_BY_ID.put(1, user(
                1,
                "george.bluth@reqres.in",
                "George",
                "Bluth",
                "https://reqres.in/img/faces/1-image.jpg"
        ));

        USER_BY_ID.put(2, user(
                2,
                "janet.weaver@reqres.in",
                "Janet",
                "Weaver",
                "https://reqres.in/img/faces/2-image.jpg"
        ));

        USER_BY_ID.put(3, user(
                3,
                "emma.wong@reqres.in",
                "Emma",
                "Wong",
                "https://reqres.in/img/faces/3-image.jpg"
        ));

        USER_BY_ID.put(4, user(
                4,
                "eve.holt@reqres.in",
                "Eve",
                "Holt",
                "https://reqres.in/img/faces/4-image.jpg"
        ));

        USER_BY_ID.put(5, user(
                5,
                "charles.morris@reqres.in",
                "Charles",
                "Morris",
                "https://reqres.in/img/faces/5-image.jpg"
        ));

        USER_BY_ID.put(6, user(
                6,
                "tracey.ramos@reqres.in",
                "Tracey",
                "Ramos",
                "https://reqres.in/img/faces/6-image.jpg"
        ));

        USER_BY_ID.put(7, user(
                7,
                "michael.lawson@reqres.in",
                "Michael",
                "Lawson",
                "https://reqres.in/img/faces/7-image.jpg"
        ));

        USER_BY_ID.put(8, user(
                8,
                "lindsay.ferguson@reqres.in",
                "Lindsay",
                "Ferguson",
                "https://reqres.in/img/faces/8-image.jpg"
        ));

        USER_BY_ID.put(9, user(
                9,
                "tobias.funke@reqres.in",
                "Tobias",
                "Funke",
                "https://reqres.in/img/faces/9-image.jpg"
        ));

        USER_BY_ID.put(10, user(
                10,
                "byron.fields@reqres.in",
                "Byron",
                "Fields",
                "https://reqres.in/img/faces/10-image.jpg"
        ));

        USER_BY_ID.put(11, user(
                11,
                "george.edwards@reqres.in",
                "George",
                "Edwards",
                "https://reqres.in/img/faces/11-image.jpg"
        ));

        USER_BY_ID.put(12, user(
                12,
                "rachel.howell@reqres.in",
                "Rachel",
                "Howell",
                "https://reqres.in/img/faces/12-image.jpg"
        ));

    }

    private static Data user(int id, String email, String firstName,
                             String lastName, String avatar) {
        Data user = new Data();
        user.setId(id);
        user.setEmail(email);
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setAvatar(avatar);
        return user;
    }

    public static Data getById(int id) {
        Data user = USER_BY_ID.get(id);
        if (user == null) {
            throw new IllegalArgumentException("No test user with id " + id);
        }
        return user;
    }

    public static int getRandomId() {
        List<Integer> ids = new ArrayList<>(USER_BY_ID.keySet());
        int randomIndex = ThreadLocalRandom.current().nextInt(ids.size());
        return ids.get(randomIndex);
    }

    public static Data getRandomUser() {
        int id = getRandomId();
        return getById(id);
    }

    public static Map<Integer, Data> getAllUsers() {
        return USER_BY_ID;
    }

    // -------------------------------------- Separate List ------------------------------------------------------------

    // George Bluth
    public final static int BASE_georgeBluthId = 1;
    public final static String BASE_georgeBluthEmail = "george.bluth@reqres.in";
    public final static String BASE_georgeBluthFirstName = "George";
    public final static String BASE_georgeBluthLastName = "Bluth";
    public final static String BASE_georgeBluthAvatar = "https://reqres.in/img/faces/1-image.jpg";

    // Janet Weaver
    public final static int BASE_janetWeaverId = 2;
    public final static String BASE_janetWeaverEmail = "janet.weaver@reqres.in";
    public final static String BASE_janetWeaverFirstName = "Janet";
    public final static String BASE_janetWeaverLastName = "Weaver";
    public final static String BASE_janetWeaverAvatar = "https://reqres.in/img/faces/2-image.jpg";

    // Charles Morris
    public final static int BASE_charlesMorrisId = 5;
    public final static String BASE_charlesMorrisEmail = "charles.morris@reqres.in";
    public final static String BASE_charlesMorrisFirstName = "Charles";
    public final static String BASE_charlesMorrisLastName = "Morris";
    public final static String BASE_charlesMorrisAvatar = "https://reqres.in/img/faces/5-image.jpg";

    // Michael Lawson
    public final static int BASE_michaelLawsonId = 7;
    public final static String BASE_michaelLawsonEmail = "michael.lawson@reqres.in";
    public final static String BASE_michaelLawsonFirstName = "Michael";
    public final static String BASE_michaelLawsonLastName = "Lawson";
    public final static String BASE_michaelLawsonAvatar = "https://reqres.in/img/faces/7-image.jpg";

    // Lindsay Ferguson
    public final static int BASE_lindsayFergusonId = 8;
    public final static String BASE_lindsayFergusonEmail = "lindsay.ferguson@reqres.in";
    public final static String BASE_lindsayFergusonFirstName = "Lindsay";
    public final static String BASE_lindsayFergusonLastName = "Ferguson";
    public final static String BASE_lindsayFergusonAvatar = "https://reqres.in/img/faces/8-image.jpg";

    // George Edwards
    public final static int BASE_georgeEdwardsId = 11;
    public final static String BASE_georgeEdwardsEmail = "george.edwards@reqres.in";
    public final static String BASE_georgeEdwardsFirstName = "George";
    public final static String BASE_georgeEdwardsLastName = "Edwards";
    public final static String BASE_georgeEdwardsAvatar = "https://reqres.in/img/faces/11-image.jpg";


    // Support
    public final static String BASE_supportUrl = "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral";
    public final static String BASE_supportText = "Tired of writing endless social media content? Let Content Caddy generate it for you.";
}
