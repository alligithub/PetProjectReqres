package com.petProject.api.models.getUsersListModel.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;


@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@Data
public class GetUsersListResponseModel {

    @JsonProperty("per_page")
    private int perPage;

    @JsonProperty("total")
    private int total;

    @JsonProperty("data")
    private List<DataItem> data;

    @JsonProperty("page")
    private int page;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("support")
    private Support support;

    @JsonProperty("_meta")
    private Meta _meta;
}