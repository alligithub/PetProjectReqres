package com.petProject.api.models.getSingleUserModel.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@Data
public class Meta{

    @JsonProperty("docs_url")
    private String docs_url;

    @JsonProperty("features")
    private List<String> features;

    @JsonProperty("powered_by")
    private String powered_by;

    @JsonProperty("template_gallery")
    private String template_gallery;

    @JsonProperty("message")
    private String message;

    @JsonProperty("upgrade_url")
    private String upgrade_url;

    @JsonProperty("upgrade_cta")
    private String upgrade_cta;

}