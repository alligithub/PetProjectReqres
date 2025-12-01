package com.petProject.api.models.getSingleUserModel.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@lombok.Data
public class GetSingleUserResponseModel {

    @JsonProperty("data")
    private Data data;

    @JsonProperty("_meta")
    private Meta _meta;

    @JsonProperty("support")
    private Support support;

}