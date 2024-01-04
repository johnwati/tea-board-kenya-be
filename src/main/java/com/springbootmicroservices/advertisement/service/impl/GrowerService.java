//package com.springbootmicroservices.advertisement.service.impl;
//
//import com.springbootmicroservices.advertisement.dto.TeaFarmingImportDTO;
//import com.springbootmicroservices.advertisement.entity.Grower;
//import com.springbootmicroservices.advertisement.entity.TeaFarmer;
//import com.springbootmicroservices.advertisement.repository.GrowerRepository;
//import com.springbootmicroservices.advertisement.repository.TeaFarmerRepository;
//import com.springbootmicroservices.advertisement.service.*;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Service
//public class GrowerService {
//
//    private static final Logger logger = LoggerFactory.getLogger(TeaFarmerServiceImpl.class);
//    private final TeaFarmerRepository teaFarmerRepository;
//    private final FactoryService factoryService;
//
//    private final WardService wardService;
//
//    private final SubCountyService subCountyService;
//
//    private final CountyService countyService;
//
//    private final RegionService regionService;
//
//    private final TeaVarietyService teaVarietyService;
//
//    private final TeaCultivarService teaCultivarService;
//
//    private final FarmingTypeService farmingTypeService;
//
//    private final PaymentMethodService paymentMethodService;
//
//
//    private final int THREAD_POOL_SIZE = 1; // Set your desired thread pool size
//    private final TeaFarmerService teaFarmerService;
//
//    @Autowired
//    public GrowerService(TeaFarmerRepository teaFarmerRepository, FactoryService factoryService, WardService wardService, SubCountyService subCountyService, CountyService countyService, RegionService regionService, TeaVarietyService teaVarietyService, TeaCultivarService teaCultivarService, FarmingTypeService farmingTypeService, PaymentMethodService paymentMethodService, GrowerRepository growerRepository, TeaFarmerService teaFarmerService) {
//        this.teaFarmerRepository = teaFarmerRepository;
//        this.factoryService = factoryService;
//        this.wardService = wardService;
//        this.subCountyService = subCountyService;
//        this.countyService = countyService;
//        this.regionService = regionService;
//        this.teaVarietyService = teaVarietyService;
//        this.teaCultivarService = teaCultivarService;
//        this.farmingTypeService = farmingTypeService;
//        this.paymentMethodService = paymentMethodService;
//        this.growerRepository = growerRepository;
//        this.teaFarmerService = teaFarmerService;
//    }
//
//    public void importData (MultipartFile file) throws IOException {
//        try (InputStream inputStream = file.getInputStream();
//             Workbook workbook = new XSSFWorkbook(inputStream)) {
//
//            Sheet sheet = workbook.getSheetAt(0);
//
//            ExecutorService executorService = Executors.newFixedThreadPool(this.THREAD_POOL_SIZE);
//
//            // Iterate through rows and submit tasks to executor
//            for (Row currentRow : sheet) {
//                if (currentRow.getRowNum() == 0) {
//                    // Skip header row
//                    continue;
//                }
//
//                executorService.submit(() -> processRow(currentRow));
//            }
//
//            executorService.shutdown(); // Shutdown executor after all tasks are submitted
//            // Wait for all tasks to complete before proceeding
//            while (!executorService.isTerminated()) {
//                // Wait
//            }
//        }
//    }
//    public void importData_(MultipartFile file) throws IOException {
//        try (InputStream inputStream = file.getInputStream();
//             Workbook workbook = new XSSFWorkbook(inputStream)) {
//
//            Sheet sheet = workbook.getSheetAt(0);
//
//            // Iterate through rows sequentially
//            for (Row currentRow : sheet) {
//                if (currentRow.getRowNum() == 0) {
//                    // Skip header row
//                    continue;
//                }
//
//                processRow(currentRow);
//            }
//        }
//    }
//
//    private void processRow(Row currentRow) {
//        // Check if the row is empty or contains only blank cells
//        if (!isRowEmpty(currentRow)) {
//            Grower grower = new Grower();
//            logger.info(getStringCellValue(currentRow.getCell(2)));
//            // Extract data from cells and create Grower objects
//            grower.setGrowerNumber(getStringCellValue(currentRow.getCell(0)));
//            grower.setGrowerName(getStringCellValue(currentRow.getCell(1)));
//            grower.setNationalId(getStringCellValue(currentRow.getCell(2)));
//            grower.setGrowerGroup(getStringCellValue(currentRow.getCell(3)));
//            grower.setCompanyPin(getStringCellValue(currentRow.getCell(4)));
//            grower.setCompanyPin(getStringCellValue(currentRow.getCell(5)));
//            grower.setGender(getStringCellValue(currentRow.getCell(6)));
//            grower.setDateOfBirth(getStringCellValue(currentRow.getCell(7)));
//            grower.setTelNumber(getStringCellValue(currentRow.getCell(8)));
//            grower.setEmail(getStringCellValue(currentRow.getCell(9)));
//            grower.setLandRegistrationNo(getStringCellValue(currentRow.getCell(10)));
//            grower.setTotalLandAreaAcres(String.valueOf(currentRow.getCell(11).getNumericCellValue()));
//            grower.setTeaCultivationAreaAcres(String.valueOf(currentRow.getCell(12).getNumericCellValue()));
//            grower.setFactory(getStringCellValue(currentRow.getCell(13)));
//            grower.setBuyingCentre(getStringCellValue(currentRow.getCell(14)));
//            grower.setWardLocation(getStringCellValue(currentRow.getCell(15)));
//            grower.setSubCounty(getStringCellValue(currentRow.getCell(16)));
//            grower.setCounty(getStringCellValue(currentRow.getCell(17)));
//            grower.setRegion(getStringCellValue(currentRow.getCell(18)));
//            grower.setTeaVarietiesCultivated(getStringCellValue(currentRow.getCell(19)));
//            grower.setTeaCultivars(getStringCellValue(currentRow.getCell(20)));
//            grower.setTotalTeaBushes(getStringCellValue(currentRow.getCell(21)));
//            grower.setAgeOfTeaBushYears(getStringCellValue(currentRow.getCell(22)));
//            grower.setProductivityPerBushKgPerYear(getStringCellValue(currentRow.getCell(23)));
//            grower.setTypeOfFarming(getStringCellValue(currentRow.getCell(24)));
//            grower.setMembershipInTeaAssociation(getStringCellValue(currentRow.getCell(25)));
//            grower.setTotalFertilizerPerYearPerAcre(getStringCellValue(currentRow.getCell(26)));
//            grower.setAverageAnnualTeaProductionKgPerAcre(getStringCellValue(currentRow.getCell(27)));
//            grower.setPaymentMethod(getStringCellValue(currentRow.getCell(28)));
//            grower.setDateGreenleafAgreementSigned(getStringCellValue(currentRow.getCell(29)));
//            // Save Grower object to the database
//
//
////            logger.info("Converting tp GroverDTO");
////            TeaFarmingImportDTO teaFarmingImportDTO = grower.mapGrowerToDTO();
////            logger.info("Converting tp GroverDTO" + teaFarmingImportDTO);
////            TeaFarmer teaFarmer = this.teaFarmerService.importTeaFarmer(teaFarmingImportDTO);
////            logger.info("Converting tp GroverDTO" + teaFarmer.toString());
//            this.growerRepository.save(grower);
//        }
//    }
//
//
//    public TeaFarmer importTeaFarmer(TeaFarmingImportDTO importRequest) {
//        logger.info("===========create TeaFarmer entity" + importRequest.toString());
//        // Check if the tea farmer with the given grower number exists
//        if (this.teaFarmerRepository.findById(importRequest.getGrowerNumber()).isPresent()) {
//            throw new RuntimeException("Tea farmer with grower number " + importRequest.getGrowerNumber() + " already exists.");
//        }
//        logger.info("+++++++++++++create TeaFarmer entity" + importRequest);
//        // Create TeaFarmer entity
//        TeaFarmer teaFarmer = convertToTeaFarmer(importRequest);
//        logger.info("===============" + importRequest);
//        // Set relationships
//        setRelationships(teaFarmer, importRequest);
//
//        // Save the tea farmer entity
//        return this.teaFarmerRepository.save(teaFarmer);
//    }
//
//
//
//
//    private TeaFarmer convertToTeaFarmer(TeaFarmingImportDTO importRequest) {
//        TeaFarmer teaFarmer = new TeaFarmer();
//        logger.info("++++++++++++++++++++++++++");
//        // Set fields from the import request
//        teaFarmer.setGrowerNumber(importRequest.getGrowerNumber());
//        teaFarmer.setGrowerName(importRequest.getGrowerName());
//        teaFarmer.setBuyingCentre(importRequest.getBuyingCentre());
//        teaFarmer.setNationalId(importRequest.getNationalId());
//        teaFarmer.setGrowerGroup(importRequest.getGrowerGroup());
//        teaFarmer.setCompanyRegistrationCertificateNo(importRequest.getCompanyRegistrationCertificateNo());
//        teaFarmer.setCompanyPin(importRequest.getCompanyPin());
//        teaFarmer.setGender(importRequest.getGender());
//        teaFarmer.setEmail(importRequest.getEmail());
//        teaFarmer.setTelNumber(importRequest.getTelNumber());
//        teaFarmer.setLandRegistrationNo(importRequest.getLandRegistrationNo());
//        teaFarmer.setTotalLandAreaAcres(importRequest.getTotalLandAreaAcres());
//
//        // Set other fields similarly
//        teaFarmer.setTeaVarietiesCultivated(importRequest.getTeaVarietiesCultivated());
//        teaFarmer.setTeaCultivationAreaAcres(importRequest.getTeaCultivationAreaAcres());
//        teaFarmer.setTotalTeaBushes(importRequest.getTotalTeaBushes());
//        teaFarmer.setAgeOfTeaBushYears(importRequest.getAgeOfTeaBushYears());
//        teaFarmer.setProductivityPerBushYear((importRequest.getProductivityPerBushYear()));
//        teaFarmer.setMembershipInTeaAssociation(importRequest.getMembershipInTeaAssociation());
//        teaFarmer.setTotalFertilizerPerYearAcre(importRequest.getTotalFertilizerPerYearAcre());
//        teaFarmer.setAverageAnnualTeaProduction((importRequest.getAverageAnnualTeaProduction()));
//        teaFarmer.setDateGreenLeafAgreementSigned(importRequest.getDateGreenLeafAgreementSigned());
////        teaFarmer.setCoordinates(importRequest.getCoordinates());
//
//        return teaFarmer;
//
//    }
//
//    private void setRelationships(TeaFarmer teaFarmer, TeaFarmingImportDTO importRequest) {
//
//        // Set relationships (e.g., Factory, Ward, SubCounty, etc.)
//        // Assuming you have separate methods to set relationships based on IDs
//        logger.info("***************" + teaFarmer.toString());
//        // Set Region relationship
//        teaFarmer.setRegion(this.regionService.createOrUpdateRegion(importRequest.getRegionName()));
//        logger.debug(teaFarmer.toString());
//        // Set County relationship
//        teaFarmer.setCounty(this.countyService.createOrUpdateCounty(importRequest.getCountyName(), teaFarmer.getRegion()));
//        logger.info(teaFarmer.toString());
//
//        // Set SubCounty relationship
//        teaFarmer.setSubCounty(this.subCountyService.createOrUpdateSubCounty(importRequest.getSubCountyName(), teaFarmer.getCounty()));
//        logger.info(teaFarmer.toString());
//        // Set Ward relationship
//        teaFarmer.setWard(this.wardService.createOrUpdateWard(importRequest.getWardName(), teaFarmer.getSubCounty()));
//        logger.info(teaFarmer.toString());
//        // Set Factory relationship
//        teaFarmer.setFactory(this.factoryService.createOrUpdateFactory(importRequest.getFactoryName(), teaFarmer.getWard()));
//        // Set TeaVariety relationship
//        teaFarmer.setTeaVariety(this.teaVarietyService.createOrUpdateTeaVariety(importRequest.getTeaVarietyName()));
//        logger.info(teaFarmer.toString());
//
//        // Set TeaCultivar relationship
//        teaFarmer.setTeaCultivar(this.teaCultivarService.createOrUpdateTeaCultivar(importRequest.getTeaCultivarName()));
//        logger.info(teaFarmer.toString());
//        // Set FarmingType relationship
//        teaFarmer.setFarmingType(this.farmingTypeService.createOrUpdateFarmingType(importRequest.getFarmingTypeName()));
//
//        // Set PaymentMethod relationship
//        teaFarmer.setPaymentMethod(this.paymentMethodService.createOrUpdatePaymentMethod(importRequest.getPaymentMethodName()));
//        logger.info(teaFarmer.toString());
//        // Set other relationships similarly
//
//    }
//
//
//}
//
