package com.gateway.gatewaybackend.repository;

import com.gateway.gatewaybackend.entity.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway, Long> {
}
