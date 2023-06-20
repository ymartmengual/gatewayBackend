package com.gateway.gatewaybackend.services.Impl;

import com.gateway.gatewaybackend.entity.Gateway;
import com.gateway.gatewaybackend.repository.GatewayRepository;
import com.gateway.gatewaybackend.services.IGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GatewayServiceImpl implements IGatewayService {

    @Autowired
    GatewayRepository gatewayRepository;

    @Override
    public Gateway save(Gateway gateway) {
        return gatewayRepository.save(gateway);
    }

    @Override
    public List<Gateway> findAll() {
        return gatewayRepository.findAll();
    }

    @Override
    public Optional<Gateway> findById(Long idGateway) {
        return gatewayRepository.findById(idGateway);
    }
}
