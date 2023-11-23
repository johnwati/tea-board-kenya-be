package com.springbootmicroservices.advertisement.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Factory") // Use the table name "Factory"
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Factory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long factoryId;

    // Your fields here
    @Column(name = "FactoryName")
    private String factoryName;

    @OneToOne
    @JoinColumn(name = "wardID")
    private Ward ward;
}
