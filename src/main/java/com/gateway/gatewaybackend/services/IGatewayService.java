package com.gateway.gatewaybackend.services;

import com.gateway.gatewaybackend.entity.Gateway;

import java.util.List;
import java.util.Optional;

public interface IGatewayService {

    Gateway save(Gateway gateway);

    List<Gateway> findAll();

    Optional<Gateway> findById(Long idGateway);
}
