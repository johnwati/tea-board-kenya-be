package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.dto.TeaFarmingImportDTO;
import com.springbootmicroservices.advertisement.entity.TeaFarmer;
import com.springbootmicroservices.advertisement.repository.TeaFarmerRepository;
import com.springbootmicroservices.advertisement.service.*;
import com.springbootmicroservices.advertisement.service.specifications.TeaFarmerSpecifications;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TeaFarmerServiceImpl implements TeaFarmerService {
    private static final Logger logger = LoggerFactory.getLogger(TeaFarmerServiceImpl.class);
    private final TeaFarmerRepository teaFarmerRepository;

    private final FactoryService factoryService;

    private final WardService wardService;

    private final SubCountyService subCountyService;

    private final CountyService countyService;

    private final RegionService regionService;

    private final TeaVarietyService teaVarietyService;

    private final TeaCultivarService teaCultivarService;

    private final FarmingTypeService farmingTypeService;

    private final PaymentMethodService paymentMethodService;

    // Other autowired
    public TeaFarmerServiceImpl(TeaFarmerRepository teaFarmerRepository, FactoryService factoryService, WardService wardService, SubCountyService subCountyService, CountyService countyService, RegionService regionService, TeaVarietyService teaVarietyService, TeaCultivarService teaCultivarService, FarmingTypeService farmingTypeService, PaymentMethodService paymentMethodService) {
        this.teaFarmerRepository = teaFarmerRepository;
        this.factoryService = factoryService;
        this.wardService = wardService;
        this.subCountyService = subCountyService;
        this.countyService = countyService;
        this.regionService = regionService;
        this.teaVarietyService = teaVarietyService;
        this.teaCultivarService = teaCultivarService;
        this.farmingTypeService = farmingTypeService;
        this.paymentMethodService = paymentMethodService;
    }

    @Override
    public TeaFarmer createTeaFarmer(TeaFarmer teaFarmer) {
        log.info(teaFarmer.toString());
        return teaFarmerRepository.save(teaFarmer);
    }

    @Override
    public List<TeaFarmer> getAllTeaFarmers() {
        return teaFarmerRepository.findAll();
    }

    @Override
    public TeaFarmer getTeaFarmerByGrowerNumber(String growerNumber) {
        return teaFarmerRepository.findById(growerNumber).orElse(null);
    }

    @Override
    public TeaFarmer updateTeaFarmer(String growerNumber, TeaFarmer updatedTeaFarmer) {
        updatedTeaFarmer.setGrowerNumber(growerNumber);
        return teaFarmerRepository.save(updatedTeaFarmer);
    }

    @Override
    public void deleteTeaFarmer(String growerNumber) {
        teaFarmerRepository.deleteById(growerNumber);
    }

    @Override
    public Page<TeaFarmer> getAllTeaFarmersWithPagination(Pageable pageable) {
        return teaFarmerRepository.findAll(pageable);
    }

    @Override
    public Page<TeaFarmer> searchTeaFarmersByName(String growerName, Pageable pageable) {
        return teaFarmerRepository.findByGrowerNameContainingIgnoreCase(growerName, pageable);
    }

    @Override
    public Page<TeaFarmer> searchTeaFarmers(String growerNumber, String growerName, String buyingCentre, String nationalId,
                                            String growerGroup, double totalLandAreaAcres, int page, int size /* add more parameters for other fields */) {
        Specification<TeaFarmer> spec = TeaFarmerSpecifications.buildSpec(growerNumber, growerName, buyingCentre, nationalId,
                growerGroup, totalLandAreaAcres /* add more arguments */);

        PageRequest pageRequest = PageRequest.of(page, size);
        return teaFarmerRepository.findAll(spec, pageRequest);
    }

    @Override
    public TeaFarmer importTeaFarmer(TeaFarmingImportDTO importRequest) {
        logger.info("===========create TeaFarmer entity" + importRequest.toString());
        // Check if the tea farmer with the given grower number exists
        if (teaFarmerRepository.findById(importRequest.getGrowerNumber()).isPresent()) {
            throw new RuntimeException("Tea farmer with grower number " + importRequest.getGrowerNumber() + " already exists.");
        }
        logger.info("create TeaFarmer entity" + importRequest);
        logger.info("create TeaFarmer entity" + importRequest);
        // Create TeaFarmer entity
        TeaFarmer teaFarmer = convertToTeaFarmer(importRequest);
        logger.info("===============" + importRequest);
        // Set relationships
        setRelationships(teaFarmer, importRequest);

        // Save the tea farmer entity
        return teaFarmerRepository.save(teaFarmer);
    }

    private TeaFarmer convertToTeaFarmer(TeaFarmingImportDTO importRequest) {
        TeaFarmer teaFarmer = new TeaFarmer();
        logger.info("++++++++++++++++++++++++++");
        // Set fields from the import request
        teaFarmer.setGrowerNumber(importRequest.getGrowerNumber());
        teaFarmer.setGrowerName(importRequest.getGrowerName());
        teaFarmer.setBuyingCentre(importRequest.getBuyingCentre());
        teaFarmer.setNationalId(importRequest.getNationalId());
        teaFarmer.setGrowerGroup(importRequest.getGrowerGroup());
        teaFarmer.setCompanyRegistrationCertificateNo(importRequest.getCompanyRegistrationCertificateNo());
        teaFarmer.setCompanyPin(importRequest.getCompanyPin());
        teaFarmer.setGender(importRequest.getGender());
        teaFarmer.setEmail(importRequest.getEmail());
        teaFarmer.setTelNumber(importRequest.getTelNumber());
        teaFarmer.setLandRegistrationNo(importRequest.getLandRegistrationNo());
        teaFarmer.setTotalLandAreaAcres(importRequest.getTotalLandAreaAcres());

        // Set other fields similarly
        teaFarmer.setTeaVarietiesCultivated(importRequest.getTeaVarietiesCultivated());
        teaFarmer.setTeaCultivationAreaAcres(importRequest.getTeaCultivationAreaAcres());
        teaFarmer.setTotalTeaBushes(importRequest.getTotalTeaBushes());
        teaFarmer.setAgeOfTeaBushYears(importRequest.getAgeOfTeaBushYears());
        teaFarmer.setProductivityPerBushYear((importRequest.getProductivityPerBushYear()));
        teaFarmer.setMembershipInTeaAssociation(importRequest.getMembershipInTeaAssociation());
        teaFarmer.setTotalFertilizerPerYearAcre(importRequest.getTotalFertilizerPerYearAcre());
        teaFarmer.setAverageAnnualTeaProduction((importRequest.getAverageAnnualTeaProduction()));
        teaFarmer.setDateGreenLeafAgreementSigned(importRequest.getDateGreenLeafAgreementSigned());
//        teaFarmer.setCoordinates(importRequest.getCoordinates());

        return teaFarmer;

    }

    private void setRelationships(TeaFarmer teaFarmer, TeaFarmingImportDTO importRequest) {

        // Set relationships (e.g., Factory, Ward, SubCounty, etc.)
        // Assuming you have separate methods to set relationships based on IDs
        logger.info("***************" + teaFarmer.toString());
        // Set Region relationship
        teaFarmer.setRegion(regionService.createOrUpdateRegion(importRequest.getRegionName()));
        logger.debug(teaFarmer.toString());
        // Set County relationship
        teaFarmer.setCounty(countyService.createOrUpdateCounty(importRequest.getCountyName(), teaFarmer.getRegion()));
        log.info(teaFarmer.toString());
        // Set SubCounty relationship
        teaFarmer.setSubCounty(subCountyService.createOrUpdateSubCounty(importRequest.getSubCountyName(), teaFarmer.getCounty()));
        log.info(teaFarmer.toString());
        // Set Ward relationship
        teaFarmer.setWard(wardService.createOrUpdateWard(importRequest.getWardName(), teaFarmer.getSubCounty()));
        log.info(teaFarmer.toString());
        // Set Factory relationship
        teaFarmer.setFactory(factoryService.createOrUpdateFactory(importRequest.getFactoryName(), teaFarmer.getWard()));
        // Set TeaVariety relationship
        teaFarmer.setTeaVariety(teaVarietyService.createOrUpdateTeaVariety(importRequest.getTeaVarietyName()));
        log.info(teaFarmer.toString());

        // Set TeaCultivar relationship
        teaFarmer.setTeaCultivar(teaCultivarService.createOrUpdateTeaCultivar(importRequest.getTeaCultivarName()));
        log.info(teaFarmer.toString());
        // Set FarmingType relationship
        teaFarmer.setFarmingType(farmingTypeService.createOrUpdateFarmingType(importRequest.getFarmingTypeName()));

        // Set PaymentMethod relationship
        teaFarmer.setPaymentMethod(paymentMethodService.createOrUpdatePaymentMethod(importRequest.getPaymentMethodName()));
        log.info(teaFarmer.toString());
        // Set other relationships similarly

    }


}
