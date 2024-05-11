package com.springbootmicroservices.advertisement.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Branch implements Serializable {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("status")
    private String status;
    @JsonProperty("initial")
    private String initial;
    @JsonProperty("county_group_id")
    private String countyGroupId;
    @JsonProperty("biotime_area_id")
    private Object biotimeAreaId;
    @JsonProperty("sync_biotime")
    private String syncBiotime;

    // Getters and setters
}