package com.petProject.api.models.getUsersListModel.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataItem{

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