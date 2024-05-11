package com.springbootmicroservices.advertisement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Data
public class InvoicesResponse  implements Serializable{
    @JsonProperty("status")
    private int status;
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private List<InvoiceData> data;
    @JsonProperty("page_details")
    private PageDetails pageDetails;



}




