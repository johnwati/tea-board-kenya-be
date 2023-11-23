package com.springbootmicroservices.advertisement.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countyID;

    private String countyName;

    @ManyToOne
    @JoinColumn(name = "RegionID")
    private Region region;
}