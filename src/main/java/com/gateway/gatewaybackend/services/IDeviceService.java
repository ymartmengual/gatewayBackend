package com.gateway.gatewaybackend.services;

import com.gateway.gatewaybackend.entity.Device;

import java.util.List;

public interface IDeviceService {

    Device save(Device device);

    List<Device> findAll();

    Device findById(Long idDevice);

    boolean existDeviceByUid(String uid);
}
