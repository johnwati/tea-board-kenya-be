package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.entity.TeaFarmer;
import com.springbootmicroservices.advertisement.entity.TeaGrowersForm;
import com.springbootmicroservices.advertisement.repository.ImportGrowerRepository;
import com.springbootmicroservices.advertisement.repository.TeaGrowersFormRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Service
public class ExcelDownloader implements ApplicationRunner {


    private final ImportGrowerRepository importGrowerRepository;
    private final TeaGrowersFormRepository teaGrowersFormRepository;

    public ExcelDownloader(ImportGrowerRepository importGrowerRepository, TeaGrowersFormRepository teaGrowersFormRepository) {
        this.importGrowerRepository = importGrowerRepository;
        this.teaGrowersFormRepository = teaGrowersFormRepository;
    }

    //@PostConstruct
    @Scheduled(fixedRate = 50000) // Run every hour
    public  void mainw() {
        List<TeaGrowersForm> unprocessedForms = teaGrowersFormRepository.findByProcessed("no");
        unprocessedForms.stream().forEach(form -> {
            form.setStatus("In Progress");
            teaGrowersFormRepository.save(form);
            String fileName = form.getFileExcelFile();
            String fileUrl = "http://41.220.116.115/administrator/file/download/form_upload_tea_growers/"+fileName;
            String destinationFilePath = "downloaded_file.xlsx";

            try {
                // Download the Excel file
                downloadFile(fileUrl, destinationFilePath);

                // Extract rows from the downloaded Excel file
                List<TeaFarmer> growers = extractGrowers(destinationFilePath,form);
                importGrowerRepository.saveAll(growers);
                // Print the extracted data (for verification)
                for (TeaFarmer grower : growers) {
                    System.out.println(grower);
                    //importGrowerRepository.save(grower);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            form.setProcessed("yes");
            form.setStatus("Done");
            teaGrowersFormRepository.save(form);
        });
    }

    private static void downloadFile(String fileUrl, String destinationFilePath) throws IOException {
        URL url = new URL(fileUrl);
        InputStream inputStream = url.openStream();
        FileOutputStream outputStream = new FileOutputStream(destinationFilePath);

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.close();
        System.out.println("File downloaded successfully.");
    }

    private static void extractRows2(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        // Skipping header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        // Iterating over rows and extracting data
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.iterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                System.out.print(cell.toString() + "\t");
            }
            System.out.println();
        }

        workbook.close();
        fileInputStream.close();
    }

    private static void extractRows(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        // Skipping header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        // Iterating over rows and extracting data from columns 0 to 30
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            for (int i = 0; i <= 30; i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                String cellValue = "";
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            cellValue = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                cellValue = cell.getDateCellValue().toString();
                            } else {
                                cellValue = String.valueOf(cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            cellValue = cell.getCellFormula();
                            break;
                        default:
                            // Handle other types as needed
                    }
                }
                System.out.print(cellValue + "\t");
            }
            System.out.println();
        }

        workbook.close();
        fileInputStream.close();
    }

    private  List<TeaFarmer> extractGrowers(String filePath,TeaGrowersForm growerDataImport) throws IOException {
        List<TeaFarmer> growers = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Integer numberOfRows = sheet.getPhysicalNumberOfRows()-1;
        growerDataImport.setNumberOfRecords(String.valueOf(numberOfRows));
        teaGrowersFormRepository.save(growerDataImport);
        Iterator<Row> rowIterator = sheet.iterator();

        // Skipping header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        // Iterating over rows and extracting data from columns 0 to 30
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            TeaFarmer grower = new TeaFarmer();
            for (int i = 0; i <= 30; i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                String cellValue = "";
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            cellValue = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                cellValue = cell.getDateCellValue().toString();
                            } else {
                                cellValue = String.valueOf(cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            cellValue = cell.getCellFormula();
                            break;
                        default:
                            // Handle other types as needed
                    }
                }
                // Set data to corresponding field based on column index
                setGrowerData(grower, i, cellValue);
            }
            growers.add(grower);
        }

        workbook.close();
        fileInputStream.close();
        return growers;
    }

    private static void setGrowerData(TeaFarmer grower, int columnIndex, String cellValue) {
        switch (columnIndex) {
            case 0:
                grower.setGrowerNumber(cellValue);
                break;
            case 1:
                grower.setGrowerName(cellValue);
                break;
            case 2:
                grower.setNationalID(cellValue);
                break;
            case 3:
                grower.setGrowerGroup(cellValue);
                break;
            case 4:
                grower.setCompanyRegistrationCertificateNo(cellValue);
                break;
            case 5:
                grower.setCompanyPIN(cellValue);
                break;
            case 6:
                grower.setGender(cellValue);
                break;
            case 7:
                grower.setDateOfBirth(cellValue);
                break;
            case 8:
                grower.setTelNumber(cellValue);
                break;
            case 9:
                grower.setEmail(cellValue);
                break;
            case 10:
                grower.setLandRegistrationNo(cellValue);
                break;
            case 11:
                // Assuming totalLandArea is a double
                grower.setTotalLandArea(cellValue);
                break;
            case 12:
                // Assuming teaCultivationArea is a double
                grower.setTeaCultivationArea((cellValue));
                break;
            case 13:
                grower.setFactory(cellValue);
                break;
            case 14:
                grower.setBuyingCentre(cellValue);
                break;
            case 15:
                grower.setWardLocation(cellValue);
                break;
            case 16:
                grower.setSubCounty(cellValue);
                break;
            case 17:
                grower.setCounty(cellValue);
                break;
            case 18:
                grower.setRegionId(cellValue);
                break;
            case 19:
                grower.setTeaVarietiesCultivated(cellValue);
                break;
            case 20:
                grower.setTeaCultivars(cellValue);
                break;
            case 21:
                // Assuming totalTeaBushes is an integer
                grower.setTotalTeaBushes((cellValue));
                break;
            case 22:
                // Assuming ageOfTheTeaBush is an integer
                grower.setAgeOfTheTeaBush((cellValue));
                break;
            case 23:
                // Assuming productivityPerBush is a double
                grower.setProductivityPerBush((cellValue));
                break;
            case 24:
                grower.setTypeOfFarming(cellValue);
                break;
            case 25:
                grower.setMembershipInTeaAssociation(cellValue);
                break;
            case 26:
                // Assuming totalFertilizerPerYearAcre is a double
                grower.setTotalFertilizerPerYearAcre((cellValue));
                break;
            case 27:
                // Assuming averageAnnualTeaProduction is a double
                grower.setAverageAnnualTeaProduction((cellValue));
                break;
            case 28:
                grower.setPaymentMethod(cellValue);
                break;
            case 29:
                grower.setDateGreenleafAgreementSigned(cellValue);
                break;
            // Add cases for additional fields as needed
            default:
                break;
        }
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Application started. Running startup tasks...");
    }
}

