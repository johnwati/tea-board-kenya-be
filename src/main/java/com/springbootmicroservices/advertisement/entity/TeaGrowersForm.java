package com.springbootmicroservices.advertisement.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "form_upload_tea_growers")
public class TeaGrowersForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_excel_file", nullable = false)
    private String fileExcelFile;

    @Column(name = "factory_name", nullable = false)
    private String factoryName;

    @Column(name = "processed", nullable = false)
    private String processed;

    @Column(name = "number_of_records")
    private String numberOfRecords;

    @Column(name = "status")
    private String status;

    // Getters and setters
}

