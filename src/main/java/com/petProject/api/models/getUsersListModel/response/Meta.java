package com.petProject.api.models.getUsersListModel.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;


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