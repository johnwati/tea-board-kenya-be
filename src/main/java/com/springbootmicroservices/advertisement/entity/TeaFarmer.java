package com.springbootmicroservices.advertisement.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.springbootmicroservices.advertisement.convertor.Coordinates;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TeaFarmer {
    @Id
    private String growerNumber;

    @Column(name = "GrowerName")
    private String growerName;

    @Column(name = "BuyingCentre")
    private String buyingCentre;

    @Column(name = "NationalID")
    private String nationalId;

    @Column(name = "GrowerGroup")
    private String growerGroup;

    @Column(name = "CompanyRegistrationCertificateNo")
    private String companyRegistrationCertificateNo;

    @Column(name = "CompanyPIN")
    private String companyPin;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "email")
    private String email;

    @Column(name = "TelNumber")
    private String telNumber;

    @Column(name = "LandRegistrationNo")
    private String landRegistrationNo;

    @Column(name = "TotalLandAreaAcres")
    private String totalLandAreaAcres;

    @ManyToOne
    @JoinColumn(name = "Factory")
    private Factory factory;

    @Column(name = "TeaVarietiesCultivated")
    private String teaVarietiesCultivated;

    @Column(name = "TeaCultivationAreaAcres")
    private String teaCultivationAreaAcres;

    @ManyToOne
    @JoinColumn(name = "WardID")
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "SubCountyID")
    private SubCounty subCounty;

    @ManyToOne
    @JoinColumn(name = "CountyID")
    private County county;

    @ManyToOne
    @JoinColumn(name = "RegionID")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "TeaVarietyID")
    private TeaVariety teaVariety;

    @ManyToOne
    @JoinColumn(name = "TeaCultivarID")
    private TeaCultivar teaCultivar;

    @Column(name = "TotalTeaBushes")
    private String totalTeaBushes;

    @Column(name = "AgeOfTeaBushYears")
    private String ageOfTeaBushYears;

    @Column(name = "ProductivityPerBushYear")
    private String productivityPerBushYear;

    @ManyToOne
    @JoinColumn(name = "FarmingTypeID")
    private FarmingType farmingType;

    @Column(name = "MembershipInTeaAssociation")
    private String membershipInTeaAssociation;

    @Column(name = "TotalFertilizerPerYearAcre")
    private String totalFertilizerPerYearAcre;

    @Column(name = "AverageAnnualTeaProduction")
    private String averageAnnualTeaProduction;

    @ManyToOne
    @JoinColumn(name = "PaymentMethodID")
    private PaymentMethod paymentMethod;

    @Column(name = "DateGreenLeafAgreementSigned")
    private String dateGreenLeafAgreementSigned;

    @Embedded
    private Coordinates coordinates;
}