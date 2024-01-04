package com.springbootmicroservices.advertisement.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.springbootmicroservices.advertisement.convertor.Coordinates;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TeaFarmerDTO {
    private String growerNumber;
    private String growerName;
    private String buyingCentre;
    private String nationalId;
    private String growerGroup;
    private String companyRegistrationCertificateNo;
    private String companyPin;
    private String gender;
    private String email;
    private String telNumber;
    private String landRegistrationNo;
    private double totalLandAreaAcres;
    private String teaVarietiesCultivated;
    private double teaCultivationAreaAcres;
    private Integer totalTeaBushes;
    private Integer ageOfTeaBushYears;
    private BigDecimal productivityPerBushYear;
    private String membershipInTeaAssociation;
    private Integer totalFertilizerPerYearAcre;
    private BigDecimal averageAnnualTeaProduction;
    private String dateGreenLeafAgreementSigned;
    private String cultivarName;
    private Coordinates coordinates;
    private Long factoryId;
    private Long wardId;
    private Long subCountyId;
    private Long countyId;
    private Long regionId;
    private Long teaVarietyId;
    private Long teaCultivarId;
    private Long farmingTypeId;
    private Long paymentMethodId;
}