package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.entity.Advertisement;

public interface MessageService {
    void sendMessage(Advertisement advertisement);
}
