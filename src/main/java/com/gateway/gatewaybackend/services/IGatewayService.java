package com.gateway.gatewaybackend.services;

import com.gateway.gatewaybackend.entity.Gateway;

import java.util.List;

public interface IGatewayService {

    Gateway save(Gateway gateway);

    List<Gateway> findAll();

    Gateway findById(Long idGateway);

    boolean existGatewayBySerialNumber(String serialNumber);
}
