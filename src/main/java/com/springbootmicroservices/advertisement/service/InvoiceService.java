package com.springbootmicroservices.advertisement.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootmicroservices.advertisement.model.*;
import com.springbootmicroservices.advertisement.repository.InvoiceRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import okhttp3.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.annotation.PostConstruct;

@Service
public class InvoiceService {


    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
//    @PostConstruct
//    @Scheduled(fixedRate = 5000) // Run every hour
    public void fetchAndSaveInvoices() {
            OkHttpClient client = new OkHttpClient().newBuilder().build(); // Create OkHttpClient instance
            okhttp3.MediaType mediaType = MediaType.parse(
                    "text/plain"); // Define media type for request body (not used in this case as it's an empty body)
            RequestBody body = RequestBody.create(mediaType, ""); // Create an empty request body

            int currentPage = 1; // Start from page 1

            try {
                while (true) {
                    // Create the HTTP request using Request.Builder
                    Request request = new Request.Builder()
                            .url("http://api.petanns.co.ke/api/crm/invoice?page="+ currentPage) // Set the URL with current page number
                            .method("GET", null) // Set the HTTP method as GET
//                            .addHeader("Authorization", "Basic Y3JtOjc5SzNVbk9FUWV6MQ==") // Add Authorization header
//                            .addHeader("Cookie", "JSESSIONID=D587A05C9AF4FC2030D8C78BCADC8933") // Add Cookie header
                            .addHeader("Authorization", "Basic Y3JtOjc5SzNVbk9FUWV6MQ==")
                            .addHeader("Cookie", "JSESSIONID=D1FEA9F474429EB63C1C4DA69A25D23D")
                            .build(); // Build the request

                    // Execute the request synchronously and get the response
                    Response response = client.newCall(request).execute();
                    System.out.println("Error: " + response.body() + " - " + response.message());
                    // Check if the response is successful (HTTP status code 200)
                    if (response.isSuccessful()) {
                        // Process the response
                        String responseBody = response.body().string();
                        ObjectMapper objectMapper = new ObjectMapper();
                        InvoicesResponse invoicesResponse = parseInvoicesResponse(responseBody);
//                    System.out.println(invoicesResponse.toString());
//                    System.out.println();
                        // Loop through each InvoiceData object within the List
                        // Process InvoiceData objects using the helper method

                        for (InvoiceData invoiceData : invoicesResponse.getData()) {
                            // Process each InvoiceData object here (e.g., print its details)
//            System.out.println(invoiceData.toString());
                            System.out.println(toInvoice(invoiceData));
                            this.fetchAndSaveInvoices(toInvoice(invoiceData));
                        }
                        // Close the response body
                        response.body().close();

                        // Move to the next page
                        currentPage++;
                        // Check if there are more pages to fetch
                        if (currentPage > 7508) {
                            break; // Exit the loop if all pages have been fetched
                        }
                    } else {
                        // Handle non-successful response (e.g., log error)
                        System.out.println("Error: " + response.code() + " - " + response.message());
                        response.body().close();
                        break; // Exit the loop in case of error
                    }
                }
            } catch (IOException e) {
                // Handle exceptions, such as IOException
                e.printStackTrace();
            }
        }


//        System.out.println("starting" );
//        String baseUrl = "http://api.petanns.co.ke/api/crm/invoice?page=";
//        RestTemplate restTemplate = new RestTemplate();
//
//        int totalPages = 1; // Start with 1, it will be updated by the response
//        int elementsProcessed = 0;
//
//        for (int currentPage = 1; currentPage <= totalPages; currentPage++) {
//            String apiUrl = baseUrl + currentPage;
//            InvoicesResponse response = restTemplate.getForObject(apiUrl, InvoicesResponse.class);
//
////            if (response != null && response.getData() != null && !response.getData().isEmpty()) {
////                invoiceRepository.saveAll(response.getData());
////                elementsProcessed += response.getData().size();
////                totalPages = response.getPageDetails().getTotalElements();
////                System.out.println("Total elements processed: " + elementsProcessed);
////            } else {
////                break; // No more data or error occurred, exit the loop
////            }
//            OkHttpClient client = new OkHttpClient().newBuilder()
//                    .build();
//            MediaType mediaType = MediaType.parse("text/plain");
//            RequestBody body = RequestBody.create(mediaType, "");
//            Request request = new Request.Builder()
//                    .url("http://api.petanns.co.ke/api/crm/invoice?page=1")
//                    .method("GET", body)
//                    .addHeader("Authorization", "Basic Y3JtOjc5SzNVbk9FUWV6MQ==")
//                    .addHeader("Cookie", "JSESSIONID=A6F77123AFF478F7E28F0B32BB1D830B")
//                    .build();
//            Response response = client.newCall(request).execute();
//        }
//
//        System.out.println("Total elements processed: " + elementsProcessed);
private InvoicesResponse parseInvoicesResponse(String responseBody) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
        return objectMapper.readValue(responseBody, InvoicesResponse.class);
    } catch (Exception e) {
        // Handle parsing exception
        e.printStackTrace();
        return null;
    }
}
    private Invoice toInvoice(InvoiceData invoiceData) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceData.getId());
        invoice.setInvoiceNumber(invoiceData.getInvoiceNumber());
        invoice.setInvoiceDate(invoiceData.getInvoiceDate());
        invoice.setGrossAmount(invoiceData.getGrossAmount());
        invoice.setDiscount(invoiceData.getDiscount());
        invoice.setTotalAmount(invoiceData.getTotalAmount());
        invoice.setAmountPaid(invoiceData.getAmountPaid());
        invoice.setAmountDue(invoiceData.getAmountDue());

        Course course = invoiceData.getCourse();
        invoice.setCourseId(course.getId());
        invoice.setCourseName(course.getName());

        Student student = invoiceData.getStudent();
        invoice.setStudentId(student.getId());
        invoice.setFirstName(student.getFirstName());
        invoice.setLastName(student.getLastName());
        invoice.setFullName(student.getFullName());
        invoice.setStudentPhone(student.getPhone());

        Branch branch = student.getBranch();
        invoice.setBranchId(branch.getId());
        invoice.setBranchName(branch.getName());
        invoice.setBranchPhone(branch.getPhone());
        invoice.setBranchInitial(branch.getInitial());

        return invoice;
    }
    public void fetchAndSaveInvoices(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
}
