package com.petProject.api.models.getSingleUserModel.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@lombok.Data
public class Data{

    @JsonProperty("last_name")
    private String last_name;

    @JsonProperty("id")
    private int id;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("first_name")
    private String first_name;

    @JsonProperty("email")
    private String email;

}