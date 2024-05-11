package com.springbootmicroservices.advertisement.model;

//import java.time.LocalDate;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import jakarta.persistence.Entity;
//import jakarta.p/ersistence.Id;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class Invoice {

    @Id
    private Long id;
    private String invoiceNumber;
    private String invoiceDate;
    private Double grossAmount;
    private Double discount;
    private Double totalAmount;
    private Double amountPaid;
    private Double amountDue;
    private String courseId;
    private String courseName;
    private String dept;
    private String studentId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String studentPhone;
    private String branchId;
    private String branchName;
    private String branchPhone;
    private String branchInitial;

    public Invoice() {

    }

    // Constructors, getters, and setters
}
