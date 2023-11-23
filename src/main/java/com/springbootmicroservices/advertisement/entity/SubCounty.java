package com.springbootmicroservices.advertisement.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SubCounty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCountyID;

    private String subCountyName;

    @ManyToOne
    @JoinColumn(name = "CountyID")
    private County county;


}
