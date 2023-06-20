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
    public Gateway findById(Long idGateway) {
        Optional<Gateway> gatewayOptional = gatewayRepository.findById(idGateway);
        if(gatewayOptional.isPresent()){
            return  gatewayOptional.get();
        }
        return null;
    }

    @Override
    public boolean existGatewayBySerialNumber(String serialNumber) {
        return gatewayRepository.findAll().stream().anyMatch(gateway -> gateway.getSerialNumber().equalsIgnoreCase(serialNumber));
    }
}
