package com.springbootmicroservices.advertisement.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    @JsonProperty("id")
    private String id;
    @JsonProperty("admission_no")
    private String admissionNo;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("branch")
    private Branch branch;

    // Getters and setters
}
