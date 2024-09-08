package com.petProject.api.models.getUsersList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
public class Support{

    @JsonProperty("text")
    private String text;

    @JsonProperty("url")
    private String url;
}