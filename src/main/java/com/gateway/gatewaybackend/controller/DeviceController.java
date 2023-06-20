package com.gateway.gatewaybackend.controller;


import com.gateway.gatewaybackend.constante.Constante;
import com.gateway.gatewaybackend.dto.ResultDataResDto;
import com.gateway.gatewaybackend.entity.Device;
import com.gateway.gatewaybackend.exceptions.GenericErrorException;
import com.gateway.gatewaybackend.services.IDeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    private IDeviceService deviceService;

    @PostMapping("/create")
    public ResponseEntity<Object> save(@Valid @RequestBody Device device){
        try{
            if(deviceService.existDeviceByUid(device.getUid())){
                throw new GenericErrorException("You are trying to create a Device with a UID that already exists in the database",
                        Constante.CODE_RESPONSE_ARGUMENT_NOT_VALID, Constante.NAME_CODE_RESPONSE_ARGUMENT_NOT_VALID);
            }
            device.setCreatedAt(LocalDate.now());
            return new ResponseEntity<>(deviceService.save(device), HttpStatus.CREATED);
        }catch (GenericErrorException e){
            Logger.getLogger(DeviceController.class.getName()).log(Level.WARNING, e.getMessage());
            ResultDataResDto resultDataResDto = new ResultDataResDto();
            responseDto(resultDataResDto, e.getCodeError(),
                    e.getMessage(), e.getNameCodeError(), DeviceController.class.getName());
            return new ResponseEntity<>(resultDataResDto, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            Logger.getLogger(DeviceController.class.getName()).log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static void responseDto(ResultDataResDto resultData, String codeResponse, String descriptionResponse, String nameCodeResult, String source){
        resultData.setCode(codeResponse);
        resultData.setName(nameCodeResult);
        resultData.setDescription(descriptionResponse);
        resultData.setSource(source);
    }
}
