package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.dto.TeaFarmingImportDTO;
import com.springbootmicroservices.advertisement.entity.*;
import com.springbootmicroservices.advertisement.repository.GrowerRepository;
import com.springbootmicroservices.advertisement.repository.TeaFarmerRepository;
import com.springbootmicroservices.advertisement.service.*;
import com.springbootmicroservices.advertisement.service.specifications.TeaFarmerSpecifications;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
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

    private final GrowerRepository growerRepository; // Assuming JPA repository is used
    private final PaymentMethodService paymentMethodService;

    // Other autowired
    public TeaFarmerServiceImpl(TeaFarmerRepository teaFarmerRepository, FactoryService factoryService, WardService wardService, SubCountyService subCountyService, CountyService countyService, RegionService regionService, TeaVarietyService teaVarietyService, TeaCultivarService teaCultivarService, FarmingTypeService farmingTypeService, GrowerRepository growerRepository, PaymentMethodService paymentMethodService) {
        this.teaFarmerRepository = teaFarmerRepository;
        this.factoryService = factoryService;
        this.wardService = wardService;
        this.subCountyService = subCountyService;
        this.countyService = countyService;
        this.regionService = regionService;
        this.teaVarietyService = teaVarietyService;
        this.teaCultivarService = teaCultivarService;
        this.farmingTypeService = farmingTypeService;
        this.growerRepository = growerRepository;
        this.paymentMethodService = paymentMethodService;
    }

    @Override
    public TeaFarmer createTeaFarmer(TeaFarmer teaFarmer) {
        log.info(teaFarmer.toString());
        TeaFarmer fm = ArbitrationChecks(teaFarmer);
        if (teaFarmerRepository.findByGrowerNumberIgnoreCase(teaFarmer.getGrowerNumber()).isEmpty()) {
            return teaFarmerRepository.save(fm);
        }else{
           return teaFarmerRepository.findByGrowerNumberIgnoreCase(teaFarmer.getGrowerNumber()).get(0);
        }
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
        logger.info("===========create TeaFarmer entity " + importRequest.toString());
        // Check if the tea farmer with the given grower number exists
        if (teaFarmerRepository.findById(importRequest.getGrowerNumber()).isPresent()) {
            throw new RuntimeException("Tea farmer with grower number " + importRequest.getGrowerNumber() + " already exists.");
        }
        logger.info("create TeaFarmer entity" + importRequest);
        logger.info("create TeaFarmer entity" + importRequest);
        // Create TeaFarmer entity
        TeaFarmer teaFarmer = convertToTeaFarmer(importRequest);
        logger.info("===============++++++++ " + teaFarmer);
        // Set relationships
//        setRelationships(teaFarmer, importRequest);
        // Set relationships (e.g., Factory, Ward, SubCounty, etc.)
        // Assuming you have separate methods to set relationships based on IDs
        logger.info("***************" + teaFarmer);
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
        logger.info("done setting relationship===============++++++++ " + teaFarmer);
        // Save the tea farmer entity
       return this.createTeaFarmer(teaFarmer);
    }
private TeaFarmer dtoToFarmer(TeaFarmingImportDTO importRequest ){
    logger.info("===========create TeaFarmer entity " + importRequest.toString());
    // Create TeaFarmer entity
    TeaFarmer teaFarmer = convertToTeaFarmer(importRequest);
    logger.info("===============++++++++ " + teaFarmer);
    logger.info("***************" + teaFarmer);
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
    logger.info("done setting relationship===============++++++++ " + teaFarmer);
    // Save the tea farmer entity
    return teaFarmer;
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

@Override
    public void importData (MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            int numberOfSheets = workbook.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                // Set your desired thread pool size
                int THREAD_POOL_SIZE = 1;
                ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

                // Iterate through rows and submit tasks to executor
                for (Row currentRow : sheet) {
                    if (currentRow.getRowNum() == 0) {
                        // Skip header row
                        continue;
                    }

                    executorService.submit(() -> processRow(currentRow));
                }

                executorService.shutdown(); // Shutdown executor after all tasks are submitted
                // Wait for all tasks to complete before proceeding
                while (!executorService.isTerminated()) {
                    // Wait
                }
            }
        }
    }

    private void processRow(Row currentRow) {
        try {
            // Check if the row is empty or contains only blank cells
//        log.info("Processing Row Number {}",currentRow.getRowNum());
            if (!isRowEmpty(currentRow)) {
                log.info("Processing Row Number {}",currentRow.getRowNum());
                Grower grower = new Grower();
    //            logger.info(getStringCellValue(currentRow.getCell(2)));
                // Extract data from cells and create Grower objects
                grower.setGrowerNumber(getStringCellValue(currentRow.getCell(0)));
                grower.setGrowerName(getStringCellValue(currentRow.getCell(1)));
                grower.setNationalId(getStringCellValue(currentRow.getCell(2)));
                grower.setGrowerGroup(getStringCellValue(currentRow.getCell(3)));
                grower.setCompanyPin(getStringCellValue(currentRow.getCell(4)));
                grower.setCompanyPin(getStringCellValue(currentRow.getCell(5)));
                grower.setGender(getStringCellValue(currentRow.getCell(6)));
                grower.setDateOfBirth(getStringCellValue(currentRow.getCell(7)));
                grower.setTelNumber(getStringCellValue(currentRow.getCell(8)));
                grower.setEmail(getStringCellValue(currentRow.getCell(9)));
                grower.setLandRegistrationNo(getStringCellValue(currentRow.getCell(10)));
                grower.setTotalLandAreaAcres(String.valueOf(currentRow.getCell(11).getNumericCellValue()));
                grower.setTeaCultivationAreaAcres(String.valueOf(currentRow.getCell(12).getNumericCellValue()));
                grower.setFactory(getStringCellValue(currentRow.getCell(13)));
                grower.setBuyingCentre(getStringCellValue(currentRow.getCell(14)));
                grower.setWardLocation(getStringCellValue(currentRow.getCell(15)));
                grower.setSubCounty(getStringCellValue(currentRow.getCell(16)));
                grower.setCounty(getStringCellValue(currentRow.getCell(17)));
                grower.setRegion(getStringCellValue(currentRow.getCell(18)));
                grower.setTeaVarietiesCultivated(getStringCellValue(currentRow.getCell(19)));
                grower.setTeaCultivars(getStringCellValue(currentRow.getCell(20)));
                grower.setTotalTeaBushes(getStringCellValue(currentRow.getCell(21)));
                grower.setAgeOfTeaBushYears(getStringCellValue(currentRow.getCell(22)));
                grower.setProductivityPerBushKgPerYear(getStringCellValue(currentRow.getCell(23)));
                grower.setTypeOfFarming(getStringCellValue(currentRow.getCell(24)));
                grower.setMembershipInTeaAssociation(getStringCellValue(currentRow.getCell(25)));
                grower.setTotalFertilizerPerYearPerAcre(getStringCellValue(currentRow.getCell(26)));
                grower.setAverageAnnualTeaProductionKgPerAcre(getStringCellValue(currentRow.getCell(27)));
                grower.setPaymentMethod(getStringCellValue(currentRow.getCell(28)));
                grower.setDateGreenleafAgreementSigned(getStringCellValue(currentRow.getCell(29)));

                Grower grower1 =  this.growerRepository.save(grower);
                logger.info("Finished Saving grower with ID {} ", grower1.getId());
                TeaFarmer teaFarmer = this.dtoToFarmer(this.mapGrowerToDTO2(grower));
                logger.info("Converting tp GroverDTO---------- {}" ,teaFarmer.toString());
               this.createTeaFarmer(teaFarmer);
    //            logger.info(" grower {}" , grower);
    //            TeaFarmingImportDTO teaFarmingImportDTO = new TeaFarmingImportDTO().mapGrowerToDTO2(grower);
    //            logger.info(" Converting tp GroverDTO {}" , teaFarmingImportDTO.toString());
    //            TeaFarmer teaFarmer = importTeaFarmer(teaFarmingImportDTO);
    //            logger.info("Converting tp GroverDTO----------" + teaFarmer.toString());

            }else{
                log.info("Processing Row Number {} is empty",currentRow.getRowNum());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private TeaFarmingImportDTO mapGrowerToDTO2(Grower grower) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return TeaFarmingImportDTO.builder()
                .growerNumber(grower.getGrowerNumber())
                .growerName(grower.getGrowerName())
                .buyingCentre(grower.getBuyingCentre())
                .nationalId(grower.getNationalId())
                .growerGroup(grower.getGrowerGroup())
                .companyRegistrationCertificateNo(grower.getCompanyRegistrationCertificateNo())
                .companyPin(grower.getCompanyPin())
                .gender(grower.getGender())
                .email(grower.getEmail())
                .telNumber(grower.getTelNumber())
                .landRegistrationNo(grower.getLandRegistrationNo())
                .totalLandAreaAcres(String.valueOf(grower.getTotalLandAreaAcres()))
                .factoryName(grower.getFactory())
                .wardName(grower.getWardLocation())
                .subCountyName(grower.getSubCounty())
                .countyName(grower.getCounty())
                .regionName(grower.getRegion())
                .teaVarietiesCultivated(grower.getTeaVarietiesCultivated())
                .teaCultivationAreaAcres(String.valueOf(grower.getTeaCultivationAreaAcres()))
                .teaVarietyName(grower.getTeaVarietiesCultivated()) // Check if this field is intended
                .teaCultivarName(grower.getTeaCultivars())
                .totalTeaBushes(String.valueOf(grower.getTotalTeaBushes()))
                .ageOfTeaBushYears(String.valueOf(grower.getAgeOfTeaBushYears()))
                .productivityPerBushYear(String.valueOf(grower.getProductivityPerBushKgPerYear()))
                .farmingTypeName(grower.getTypeOfFarming())
                .membershipInTeaAssociation(grower.getMembershipInTeaAssociation())
                .totalFertilizerPerYearAcre(String.valueOf(grower.getTotalFertilizerPerYearPerAcre()))
                .averageAnnualTeaProduction(String.valueOf(grower.getAverageAnnualTeaProductionKgPerAcre()))
                .paymentMethodName(grower.getPaymentMethod())
//                .dateGreenLeafAgreementSigned(dateFormat.format(grower.getDateGreenleafAgreementSigned()))
                .build();
    }

//    public TeaFarmingImportDTO mapGrowerToDTO(Grower grower) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        // Set Region relationship
//        Region region = regionService.createOrUpdateRegion(grower.getRegion());
//        logger.debug(region.toString());
//        // Set County relationship
//        County county = countyService.createOrUpdateCounty(grower.getCounty(), region);
//        log.info(county.toString());
//        // Set SubCounty relationship
//        SubCounty subCounty = subCountyService.createOrUpdateSubCounty(grower.getSubCounty(),county);
//        log.info(subCounty.toString());
//        // Set Ward relationship
//        Ward ward = wardService.createOrUpdateWard(grower.getWardLocation(), subCounty);
//        log.info(ward.toString());
//        // Set Factory relationship
//        Factory factory = factoryService.createOrUpdateFactory(grower.getFactory(), ward);
//        // Set TeaVariety relationship
//        TeaVariety teaVariety = teaVarietyService.createOrUpdateTeaVariety(grower.getTeaVarietiesCultivated());
//        log.info(teaVariety.toString());
//        // Set TeaCultivar relationship
//        TeaCultivar teaCultivar = teaCultivarService.createOrUpdateTeaCultivar(grower.getTeaCultivars());
//        log.info(teaCultivar.toString());
//        // Set FarmingType relationship
//        FarmingType farmingType = farmingTypeService.createOrUpdateFarmingType(grower.getTypeOfFarming());
//        // Set PaymentMethod relationship
//        PaymentMethod paymentMethod = paymentMethodService.createOrUpdatePaymentMethod(grower.getPaymentMethod());
//        log.info(paymentMethod.toString());
//        // Set other relationships similarly
////        return TeaFarmingImportDTO.builder()
////                .growerNumber(grower.getGrowerNumber())
////                .growerName(grower.getGrowerName())
////                .buyingCentre(grower.getBuyingCentre())
////                .nationalId(grower.getNationalId())
////                .growerGroup(grower.getGrowerGroup())
////                .companyRegistrationCertificateNo(grower.getCompanyRegistrationCertificateNo())
////                .companyPin(grower.getCompanyPin())
////                .gender(grower.getGender())
////                .email(grower.getEmail())
////                .telNumber(grower.getTelNumber())
////                .landRegistrationNo(grower.getLandRegistrationNo())
////                .totalLandAreaAcres(String.valueOf(grower.getTotalLandAreaAcres()))
////                .factoryName(grower.getFactory())
////                .wardName(grower.getWardLocation())
////                .subCountyName(grower.getSubCounty())
////                .countyName(grower.getCounty())
////                .regionName(String.valueOf(region.getRegionID()))
////                .teaVarietiesCultivated(grower.getTeaVarietiesCultivated())
////                .teaCultivationAreaAcres(String.valueOf(grower.getTeaCultivationAreaAcres()))
////                .teaVarietyName(grower.getTeaVarietiesCultivated())
////                .teaCultivarName(grower.getTeaCultivars())
////                .totalTeaBushes(String.valueOf(grower.getTotalTeaBushes()))
////                .ageOfTeaBushYears(String.valueOf(grower.getAgeOfTeaBushYears()))
////                .productivityPerBushYear(String.valueOf(grower.getProductivityPerBushKgPerYear()))
////                .farmingTypeName(grower.getTypeOfFarming())
////                .membershipInTeaAssociation(grower.getMembershipInTeaAssociation())
////                .totalFertilizerPerYearAcre(String.valueOf(grower.getTotalFertilizerPerYearPerAcre()))
////                .averageAnnualTeaProduction(String.valueOf(grower.getAverageAnnualTeaProductionKgPerAcre()))
////                .paymentMethodName(grower.getPaymentMethod())
////                .dateGreenLeafAgreementSigned(dateFormat.format(grower.getDateGreenleafAgreementSigned()))
////                .build();
//    }

    private String getStringValueFromCell(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    // Adjust date formatting as needed
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                    return convertExcelDate(Double.parseDouble(dateFormat.format(date)));
                } else {
//                    return getRawValue(cell).toString();
                    return cell.getStringCellValue();
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
            default:
                return "";
        }
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
//                 Handle numeric values here, for example:
//                return String.valueOf(cell.getNumericCellValue());
                if (DateUtil.isCellDateFormatted(cell)) {
//                    Date date = cell.getDateCellValue();
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                    return dateFormat.format(date);
                    return String.valueOf(cell.getNumericCellValue());
//
                } else {
                    return formatNumericValue(cell.getNumericCellValue());
                }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
            default:
                return "";
        }
    }

    private String formatNumericValue(double numericValue) {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        return decimalFormat.format(numericValue);
    }

    public String convertExcelDate(double excelDateValue) {
        // Excel's base date is January 1, 1900
        // Convert the Excel date value to milliseconds since January 1, 1970 (Java's base date)
        long javaTime = (long) ((excelDateValue - 25569) * 86400 * 1000);

        // Create a Date object using the converted time
        Date date = new Date(javaTime);

        // Format the date as a string
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
    private Object getRawValue(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
            DataFormatter formatter = new DataFormatter();
//            return formatter.formatCellValue(cell);
            return String.valueOf(cell.getNumericCellValue());
        }
        return 0;
    }

    private boolean isRowEmpty(Row row) {
        if (row == null || row.getLastCellNum() <= 0) {
            return true;
        }

        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }

        return true;
    }

//    @Scheduled(cron = "*/1 * * * * *") // Execute every minute
//    @Scheduled(cron = "*/1 * * * * *") // Execute every second
    public void yourTaskMethod() {
        // Your task logic goes here
        System.out.println("Executing scheduled task every minute...");
    }

    private static final Logger log = LoggerFactory.getLogger(TeaFarmerServiceImpl.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        this.growerRepository.findAll().forEach((e) -> {
            log.info("Received record is : {}",e);
//            TeaFarmer teaFarmer = importTeaFarmer(this.mapGrowerToDTO2(e));
            TeaFarmer teaFarmer = this.dtoToFarmer(this.mapGrowerToDTO2(e));
            logger.info("Converting tp GroverDTO---------- {}" ,teaFarmer.toString());
            teaFarmerRepository.save(teaFarmer);
        });
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    private TeaFarmer ArbitrationChecks(TeaFarmer teaFarmer){
        if (!teaFarmerRepository.findByNationalIdIgnoreCase(teaFarmer.getNationalId()).isEmpty()) {
            teaFarmer.setArbitration("Duplicate Farmer");
            teaFarmer.setArbitrationComment("Farmer with similar ID has been Found");
        }

        if (!teaFarmerRepository.findByNationalIdIgnoreCase(teaFarmer.getNationalId()).isEmpty()) {
            TeaFarmer tf = teaFarmerRepository.findByNationalIdIgnoreCase(teaFarmer.getNationalId()).get(0);
            if(tf.getFactory() != teaFarmer.getFactory()) {
                teaFarmer.setArbitration("Multiple Factory Registration");
                teaFarmer.setArbitrationComment("Farmer is Registered In More Than one Factory");
            }
        }
        return teaFarmer;
    }
}
