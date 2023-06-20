package com.gateway.gatewaybackend.services.Impl;

import com.gateway.gatewaybackend.entity.Device;
import com.gateway.gatewaybackend.services.IDeviceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements IDeviceService {

    @Override
    public Device save(Device device) {
        return null;
    }

    @Override
    public List<Device> findAll() {
        return null;
    }

    @Override
    public Optional<Device> findById(Long idDevice) {
        return Optional.empty();
    }

    @Override
    public boolean deleteDevice(Long idDevice) {
        return false;
    }
}
