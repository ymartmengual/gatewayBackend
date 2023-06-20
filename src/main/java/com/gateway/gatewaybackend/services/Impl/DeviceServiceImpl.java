package com.gateway.gatewaybackend.services.Impl;

import com.gateway.gatewaybackend.entity.Device;
import com.gateway.gatewaybackend.repository.DeviceRepository;
import com.gateway.gatewaybackend.services.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public Device save(Device device) {
        device.setIdDevice(null);
        return deviceRepository.save(device);
    }

    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Device findById(Long idDevice) {
        Optional<Device> device = deviceRepository.findById(idDevice);
        if(device.isPresent())
            return device.get();
        return null;
    }

    @Override
    public boolean existDeviceByUid(String uid) {
        return deviceRepository.findAll().stream().anyMatch(device-> device.getUid().equalsIgnoreCase(uid));
    }
}
