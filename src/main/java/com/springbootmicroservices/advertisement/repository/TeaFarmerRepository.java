package com.springbootmicroservices.advertisement.repository;

import com.springbootmicroservices.advertisement.entity.TeaFarmer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeaFarmerRepository extends JpaRepository<TeaFarmer, String>, JpaSpecificationExecutor<TeaFarmer> {
    Page<TeaFarmer> findByGrowerNameContainingIgnoreCase(String growerName, Pageable pageable);

    List<TeaFarmer> findByGrowerNameIgnoreCase(String growerName);

    List<TeaFarmer> findByGrowerNumberIgnoreCase(String growerNumber);

    List<TeaFarmer> findByBuyingCentreIgnoreCase(String buyingCentre);

    List<TeaFarmer> findByNationalIdIgnoreCase(String nationalId);

    List<TeaFarmer> findByGrowerGroupIgnoreCase(String growerGroup);

    List<TeaFarmer> findByCompanyRegistrationCertificateNoIgnoreCase(String companyRegistrationCertificateNo);

    List<TeaFarmer> findByCompanyPinIgnoreCase(String companyPin);

    List<TeaFarmer> findByGenderIgnoreCase(String gender);

    List<TeaFarmer> findByEmailIgnoreCase(String email);

    List<TeaFarmer> findByTelNumberIgnoreCase(String telNumber);

    List<TeaFarmer> findByLandRegistrationNoIgnoreCase(String landRegistrationNo);

    List<TeaFarmer> findByTotalLandAreaAcresIgnoreCase(String totalLandAreaAcres);

    List<TeaFarmer> findByTeaVarietiesCultivatedIgnoreCase(String teaVarietiesCultivated);

    List<TeaFarmer> findByTeaCultivationAreaAcresIgnoreCase(String teaCultivationAreaAcres);

    List<TeaFarmer> findByTotalTeaBushesIgnoreCase(String totalTeaBushes);

    List<TeaFarmer> findByAgeOfTeaBushYearsIgnoreCase(String ageOfTeaBushYears);

    List<TeaFarmer> findByProductivityPerBushYearIgnoreCase(String productivityPerBushYear);

    List<TeaFarmer> findByMembershipInTeaAssociationIgnoreCase(String membershipInTeaAssociation);

    List<TeaFarmer> findByTotalFertilizerPerYearAcreIgnoreCase(String totalFertilizerPerYearAcre);

    List<TeaFarmer> findByAverageAnnualTeaProductionIgnoreCase(String averageAnnualTeaProduction);

    List<TeaFarmer> findByDateGreenLeafAgreementSignedIgnoreCase(String dateGreenLeafAgreementSigned);

    List<TeaFarmer> findByArbitrationIgnoreCase(String arbitration);

    List<TeaFarmer> findByArbitrationCommentIgnoreCase(String arbitrationComment);
}
