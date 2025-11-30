package com.petProject.api.models.getUsersListModel.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
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