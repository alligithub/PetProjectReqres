package com.petProject.api.models.getSingleUserModel.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@Data
public class Support{

    @JsonProperty("text")
    private String text;

    @JsonProperty("url")
    private String url;

}