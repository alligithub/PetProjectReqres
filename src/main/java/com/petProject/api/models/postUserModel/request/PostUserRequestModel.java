package com.petProject.api.models.postUserModel.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostUserRequestModel {

	@JsonProperty("name")
	private String name;

	@JsonProperty("job")
	private String job;
}