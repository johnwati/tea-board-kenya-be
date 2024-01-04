package com.springbootmicroservices.advertisement.entity;

import com.springbootmicroservices.advertisement.dto.TeaFarmingImportDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "grower_data")
public class Grower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grower_number")
    private String growerNumber;

    @Column(name = "grower_name")
    private String growerName;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "grower_group")
    private String growerGroup;

    @Column(name = "company_registration_certificate_no")
    private String companyRegistrationCertificateNo;

    @Column(name = "company_pin")
    private String companyPin;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "tel_number")
    private String telNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "land_registration_no")
    private String landRegistrationNo;

    @Column(name = "total_land_area_acres")
    private String totalLandAreaAcres;

    @Column(name = "tea_cultivation_area_acres")
    private String teaCultivationAreaAcres;

    @Column(name = "factory")
    private String factory;

    @Column(name = "buying_centre")
    private String buyingCentre;

    @Column(name = "ward_location")
    private String wardLocation;

    @Column(name = "sub_county")
    private String subCounty;

    @Column(name = "county")
    private String county;

    @Column(name = "region")
    private String region;

    @Column(name = "tea_varieties_cultivated")
    private String teaVarietiesCultivated;

    @Column(name = "tea_cultivars")
    private String teaCultivars;

    @Column(name = "total_tea_bushes")
    private String totalTeaBushes;

    @Column(name = "age_of_tea_bush_years")
    private String ageOfTeaBushYears;

    @Column(name = "productivity_per_bush_kg_per_year")
    private String productivityPerBushKgPerYear;

    @Column(name = "type_of_farming")
    private String typeOfFarming;

    @Column(name = "membership_in_tea_association")
    private String membershipInTeaAssociation;

    @Column(name = "total_fertilizer_per_year_per_acre")
    private String totalFertilizerPerYearPerAcre;

    @Column(name = "average_annual_tea_production_kg_per_acre")
    private String averageAnnualTeaProductionKgPerAcre;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "date_greenleaf_agreement_signed")
    private String dateGreenleafAgreementSigned;

    @Column(name = "processed")
    private boolean processed = false;


}
