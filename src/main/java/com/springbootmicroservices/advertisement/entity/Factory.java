package com.springbootmicroservices.advertisement.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Builder // Add @Builder annotation
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
