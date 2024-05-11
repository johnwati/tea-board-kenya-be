package com.springbootmicroservices.advertisement.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.springbootmicroservices.advertisement.convertor.Coordinates;
import lombok.Data;


import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tea_farmer") // Added @Table annotation with name attribute
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TeaFarmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Grower_Number")
    private String growerNumber;

    @Column(name = "Grower_Name")
    private String growerName;

    @Column(name = "NationalID")
    private String nationalID;

    @Column(name = "Grower_Group")
    private String growerGroup;

    @Column(name = "Company_Registration_Certificate_No")
    private String companyRegistrationCertificateNo;

    @Column(name = "CompanyPIN")
    private String companyPIN;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Date_of_Birth")
    private String dateOfBirth;

    @Column(name = "TEl_Number")
    private String telNumber;

    @Column(name = "Email")
    private String email;

    @Column(name = "Land_Registration_No")
    private String landRegistrationNo;

    @Column(name = "Total_Land_Area")
    private String totalLandArea;

    @Column(name = "Tea_Cultivation_Area")
    private String teaCultivationArea;

    @Column(name = "Factory")
    private String factory;

    @Column(name = "Buying_Centre")
    private String buyingCentre;

    @Column(name = "Ward_Location")
    private String wardLocation;

    @Column(name = "Sub_County")
    private String subCounty;

    @Column(name = "County")
    private String county;

    @Column(name = "Regionid")
    private String regionId;

    @Column(name = "Tea_Varieties_Cultivated")
    private String teaVarietiesCultivated;

    @Column(name = "Tea_Cultivars")
    private String teaCultivars;

    @Column(name = "Total_Tea_Bushes")
    private String totalTeaBushes;

    @Column(name = "Age_of_the_Tea_Bush")
    private String ageOfTheTeaBush;

    @Column(name = "Productivity_per_Bush")
    private String productivityPerBush;

    @Column(name = "Type_of_Farming")
    private String typeOfFarming;

    @Column(name = "Membership_in_Tea_Association")
    private String membershipInTeaAssociation;

    @Column(name = "Total_Fertilizer_Per_Year_Acre")
    private String totalFertilizerPerYearAcre;

    @Column(name = "Average_Annual_Tea_Production")
    private String averageAnnualTeaProduction;

    @Column(name = "Payment_Method")
    private String paymentMethod;

    @Column(name = "Date_Greenleaf_Agreement_Signed")
    private String dateGreenleafAgreementSigned;

    @Column(name = "age_of_tea_bush_years")
    private String ageOfTeaBushYears;

    @Column(name = "arbitration")
    private String arbitration;

    @Column(name = "arbitration_comment")
    private String arbitrationComment;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "date_green_leaf_agreement_signed")
    private String dateGreenLeafAgreementSigned;

    @Column(name = "date_created")
    private java.sql.Date dateCreated;

    @Column(name = "productivity_per_bush_year")
    private String productivityPerBushYear;

    @Column(name = "tea_cultivation_area_acres")
    private String teaCultivationAreaAcres;

    @Column(name = "total_land_area_acres")
    private String totalLandAreaAcres;

    @Column(name = "countyid")
    private Long countyId;

    @Column(name = "farming_typeid")
    private Long farmingTypeId;

    @Column(name = "payment_methodid")
    private Long paymentMethodId;

    @Column(name = "sub_countyid")
    private Long subCountyId;

    @Column(name = "tea_cultivarid")
    private Long teaCultivarId;

    @Column(name = "tea_varietyid")
    private Long teaVarietyId;

    @Column(name = "wardid")
    private Long wardId;

    // Getters and setters
}
