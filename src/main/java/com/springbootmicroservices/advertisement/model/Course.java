package com.springbootmicroservices.advertisement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Course implements Serializable {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("dept_id")
    private String deptId;

    // Getters and setters
}
