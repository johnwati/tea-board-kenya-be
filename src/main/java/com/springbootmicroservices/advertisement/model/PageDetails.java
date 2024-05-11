package com.springbootmicroservices.advertisement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Data
class PageDetails implements Serializable {
    @JsonProperty("page")
    private int page;
    @JsonProperty("per_page")
    private int perPage;
    @JsonProperty("total_page")
    private int totalPage;
    @JsonProperty("total_elements")
    private int totalElements;
    @JsonProperty("report_name")
    private String reportName;

}
