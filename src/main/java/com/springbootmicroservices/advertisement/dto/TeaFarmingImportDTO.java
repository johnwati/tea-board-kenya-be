package com.springbootmicroservices.advertisement.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.springbootmicroservices.advertisement.entity.Grower;
import lombok.Data;

import java.text.SimpleDateFormat;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TeaFarmingImportDTO {
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
    private String totalLandAreaAcres;
    private String factoryName;
    private String wardName;
    private String subCountyName;
    private String countyName;
    private String regionName;
    private String teaVarietiesCultivated;
    private String teaCultivationAreaAcres;
    private String teaVarietyName;
    private String teaCultivarName;
    private String totalTeaBushes;
    private String ageOfTeaBushYears;
    private String productivityPerBushYear;
    private String farmingTypeName;
    private String membershipInTeaAssociation;
    private String totalFertilizerPerYearAcre;
    private String averageAnnualTeaProduction;
    private String paymentMethodName;
    private String dateGreenLeafAgreementSigned;
    private String coordinates;
    public  TeaFarmingImportDTO mapGrowerToDTO(Grower grower) {
        TeaFarmingImportDTO dto = new TeaFarmingImportDTO();
        dto.setGrowerNumber(grower.getGrowerNumber());
        dto.setGrowerName(grower.getGrowerName());
        dto.setBuyingCentre(grower.getBuyingCentre());
        dto.setNationalId(grower.getNationalId());
        dto.setGrowerGroup(grower.getGrowerGroup());
        dto.setCompanyRegistrationCertificateNo(grower.getCompanyRegistrationCertificateNo());
        dto.setCompanyPin(grower.getCompanyPin());
        dto.setGender(grower.getGender());
        dto.setEmail(grower.getEmail());
        dto.setTelNumber(grower.getTelNumber());
        dto.setLandRegistrationNo(grower.getLandRegistrationNo());
        dto.setTotalLandAreaAcres(String.valueOf(grower.getTotalLandAreaAcres()));
        dto.setFactoryName(grower.getFactory());
        dto.setWardName(grower.getWardLocation());
        dto.setSubCountyName(grower.getSubCounty());
        dto.setCountyName(grower.getCounty());
        dto.setRegionName(grower.getRegion());
        dto.setTeaVarietiesCultivated(grower.getTeaVarietiesCultivated());
        dto.setTeaCultivationAreaAcres(String.valueOf(grower.getTeaCultivationAreaAcres()));
        dto.setTeaVarietyName(grower.getTeaVarietiesCultivated());
        dto.setTeaCultivarName(grower.getTeaCultivars());
        dto.setTotalTeaBushes(String.valueOf(grower.getTotalTeaBushes()));
        dto.setAgeOfTeaBushYears(String.valueOf(grower.getAgeOfTeaBushYears()));
        dto.setProductivityPerBushYear(String.valueOf(grower.getProductivityPerBushKgPerYear()));
        dto.setFarmingTypeName(grower.getTypeOfFarming());
        dto.setMembershipInTeaAssociation(grower.getMembershipInTeaAssociation());
        dto.setTotalFertilizerPerYearAcre(String.valueOf(grower.getTotalFertilizerPerYearPerAcre()));
        dto.setAverageAnnualTeaProduction(String.valueOf(grower.getAverageAnnualTeaProductionKgPerAcre()));
        dto.setPaymentMethodName(grower.getPaymentMethod());

        // Formatting date from Grower to TeaFarmingImportDTO
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(grower.getDateGreenleafAgreementSigned());
        dto.setDateGreenLeafAgreementSigned(formattedDate);



        return dto;
    }
}
