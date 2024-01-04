package com.springbootmicroservices.advertisement.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wardID;

    private String wardName;

    @ManyToOne
    @JoinColumn(name = "SubCountyID")
    private SubCounty subCounty;

    // getters and setters
}
