package com.gateway.gatewaybackend.repository;

import com.gateway.gatewaybackend.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository  extends JpaRepository<Device, Long> {
}
