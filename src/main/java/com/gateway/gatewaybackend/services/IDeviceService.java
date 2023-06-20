package com.gateway.gatewaybackend.services;

import com.gateway.gatewaybackend.entity.Device;

import java.util.List;
import java.util.Optional;

public interface IDeviceService {

    Device save(Device device);

    List<Device> findAll();

    Optional<Device> findById(Long idDevice);

    boolean deleteDevice(Long idDevice);
}
