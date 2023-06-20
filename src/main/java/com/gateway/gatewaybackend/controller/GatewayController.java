package com.gateway.gatewaybackend.controller;

import com.gateway.gatewaybackend.services.IGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/gateway")
public class GatewayController {

    @Autowired
    private IGatewayService gatewayService;


}
