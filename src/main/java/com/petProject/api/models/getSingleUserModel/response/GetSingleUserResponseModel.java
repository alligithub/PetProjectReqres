package com.petProject.api.models.getSingleUserModel.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
public class GetSingleUserResponseModel {

    @JsonProperty("data")
    private Data data;

    @JsonProperty("support")
    private Support support;
}