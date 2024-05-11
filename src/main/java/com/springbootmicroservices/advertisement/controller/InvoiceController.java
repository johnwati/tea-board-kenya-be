//package com.springbootmicroservices.advertisement.controller;
//
//import okhttp3.*;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//public class InvoiceController {
//
//    @GetMapping("/fetch-invoices")
//    public String fetchInvoices() {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("text/plain");
//        RequestBody body = RequestBody.create(mediaType, "");
//        Request request = new Request.Builder()
//                .url("http://api.petanns.co.ke/api/crm/invoice?page=1")
//                .method("GET", body)
//                .addHeader("Authorization", "Basic Y3JtOjc5SzNVbk9FUWV6MQ==")
//                .addHeader("Cookie", "JSESSIONID=A6F77123AFF478F7E28F0B32BB1D830B")
//                .build();
////        Response response = client.newCall(request).execute();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) {
//                throw new IOException("Unexpected code " + response);
//            }
//            return response.body().string();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Error occurred: " + e.getMessage();
//        }
//    }
//}
//
