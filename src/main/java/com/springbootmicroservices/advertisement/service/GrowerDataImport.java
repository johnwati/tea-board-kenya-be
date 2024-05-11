package com.springbootmicroservices.advertisement.service;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "grower_data_new")
public class GrowerDataImport {
    private String growerNumber;
    private String growerName;
    private String nationalID;
    private String growerGroup;
    private String companyRegistrationCertificateNo;
    private String companyPIN;
    private String gender;
    private String dateOfBirth;
    private String telNumber;
    private String email;
    private String landRegistrationNo;
    private String totalLandArea;
    private String teaCultivationArea;
    private String factory;
    private String buyingCentre;
    private String wardLocation;
    private String subCounty;
    private String county;
    private String regionId;
    private String teaVarietiesCultivated;
    private String teaCultivars;
    private String totalTeaBushes;
    private String ageOfTheTeaBush;
    private String productivityPerBush;
    private String typeOfFarming;
    private String membershipInTeaAssociation;
    private String totalFertilizerPerYearAcre;
    private String averageAnnualTeaProduction;
    private String paymentMethod;
    private String dateGreenleafAgreementSigned;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;


}
