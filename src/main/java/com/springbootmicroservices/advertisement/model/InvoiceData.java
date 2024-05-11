package com.springbootmicroservices.advertisement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class InvoiceData implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("invoice_number")
    private String invoiceNumber;
    @JsonProperty("invoice_date")
    private String invoiceDate;
    @JsonProperty("gross_amount")
    private double grossAmount;
    @JsonProperty("discount")
    private double discount;
    @JsonProperty("total_amount")
    private double totalAmount;
    @JsonProperty("amount_paid")
    private double amountPaid;
    @JsonProperty("amount_due")
    private double amountDue;
    @JsonProperty("course")
    private Course course;
    @JsonProperty("student")
    private Student student;


}

