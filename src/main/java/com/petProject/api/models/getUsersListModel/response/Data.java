package com.petProject.api.models.getUsersListModel.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;


@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@lombok.Data
public class Data {

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("id")
    private int id;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("email")
    private String email;
}