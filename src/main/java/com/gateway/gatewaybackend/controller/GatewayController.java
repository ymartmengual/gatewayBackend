package com.gateway.gatewaybackend.controller;

import com.gateway.gatewaybackend.constante.Constante;
import com.gateway.gatewaybackend.dto.ResultDataResDto;
import com.gateway.gatewaybackend.entity.Device;
import com.gateway.gatewaybackend.entity.Gateway;
import com.gateway.gatewaybackend.exceptions.GenericErrorException;
import com.gateway.gatewaybackend.services.IDeviceService;
import com.gateway.gatewaybackend.services.IGatewayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/gateway")
public class GatewayController {

    @Autowired
    private IGatewayService gatewayService;

    @Autowired
    IDeviceService deviceService;

    /**
     * @implNote create gateway
     * @param gateway
     * @return gateway if parameters is valid else it return error
     */
    @PostMapping("/create")
    public ResponseEntity<Object> save(@Valid @RequestBody Gateway gateway){
        try{
            List<Device> deviceList = new ArrayList<>();
            for (Device device : gateway.getDeviceList()) {
                if(device.getIdDevice() != null){
                    Device newDevice = deviceService.findById(device.getIdDevice());
                    if(newDevice != null){
                        deviceList.add(newDevice);
                    }else if(!deviceService.existDeviceByUid(device.getUid())){
                        device.setCreatedAt(LocalDate.now());
                        deviceList.add(deviceService.save(device));
                    }else{
                        throw new GenericErrorException("You are trying to create a Device with a UID that already exists in the database",
                                Constante.CODE_RESPONSE_ARGUMENT_NOT_VALID, Constante.NAME_CODE_RESPONSE_ARGUMENT_NOT_VALID);
                    }
                }else if(!deviceService.existDeviceByUid(device.getUid())){
                    device.setCreatedAt(LocalDate.now());
                    deviceList.add(deviceService.save(device));
                }else{
                    throw new GenericErrorException("You are trying to create a Device with a UID that already exists in the database",
                            Constante.CODE_RESPONSE_ARGUMENT_NOT_VALID, Constante.NAME_CODE_RESPONSE_ARGUMENT_NOT_VALID);
                }
            }
            if(deviceList.size() > 10){
                throw new GenericErrorException("Only 10 devices are allowed by gateway",
                        Constante.CODE_RESPONSE_ARGUMENT_NOT_VALID, Constante.NAME_CODE_RESPONSE_ARGUMENT_NOT_VALID);
            }
            if(gatewayService.existGatewayBySerialNumber(gateway.getSerialNumber())){
                throw new GenericErrorException("There is a Gateway with that serial number",
                        Constante.CODE_RESPONSE_ARGUMENT_NOT_VALID, Constante.NAME_CODE_RESPONSE_ARGUMENT_NOT_VALID);
            }
            gateway.setDeviceList(deviceList);
            return new ResponseEntity<>(gatewayService.save(gateway), HttpStatus.CREATED);
        }catch (GenericErrorException e){
            Logger.getLogger(GatewayController.class.getName()).log(Level.WARNING, e.getMessage());
            ResultDataResDto resultDataResDto = new ResultDataResDto();
            responseDto(resultDataResDto, e.getCodeError(),
                    e.getMessage(), e.getNameCodeError(), GatewayController.class.getName());
            return new ResponseEntity<>(resultDataResDto, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            Logger.getLogger(GatewayController.class.getName()).log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @implNote get list gateways
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<Gateway>> getAllGateways(){
        try{
            return new ResponseEntity<>(gatewayService.findAll(), HttpStatus.OK);
        }catch (Exception e){
            Logger.getLogger(GatewayController.class.getName()).log(Level.SEVERE, e.getMessage());
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @implNote Find by Id Gateway
     * @param idGateway
     * @return gateway if it is found else it returns error
     */
    @GetMapping("/{idGateway}")
    public ResponseEntity<Object> getGatewayById(@PathVariable Long idGateway){
        try{
            Gateway gateway = gatewayService.findById(idGateway);
            if(gateway != null){
                return new ResponseEntity<>(gateway, HttpStatus.OK);
            }
            throw new GenericErrorException("Gateway Id no found",
                    Constante.CODE_RESPONSE_ARGUMENT_NOT_VALID, Constante.NAME_CODE_RESPONSE_ARGUMENT_NOT_VALID);
        }catch (GenericErrorException e){
            Logger.getLogger(GatewayController.class.getName()).log(Level.WARNING, e.getMessage());
            ResultDataResDto resultDataResDto = new ResultDataResDto();
            responseDto(resultDataResDto, e.getCodeError(),
                    e.getMessage(), e.getNameCodeError(), GatewayController.class.getName());
            return new ResponseEntity<>(resultDataResDto, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            Logger.getLogger(GatewayController.class.getName()).log(Level.SEVERE, e.getMessage());
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @implNote delete Device from Gateway by Id Gateway an Id Device
     * @param idGateway
     * @param idDevice
     * @return
     */
    @DeleteMapping("/delete/{idGateway}/{idDevice}")
    public ResponseEntity<Object> deleteDeviceFromGatewayByIdGatewayAndIdDevice(@PathVariable Long idGateway, @PathVariable Long idDevice){
        try{
            Gateway gateway = gatewayService.findById(idGateway);
            Device device = deviceService.findById(idDevice);
            if(gateway!= null && device != null){
                List<Device> deviceList = gateway.getDeviceList();
                if(deviceList.stream().anyMatch(devic-> devic.equals(device))){
                    deviceList.remove(device);
                    gateway.setDeviceList(deviceList);
                    return new ResponseEntity<>(gatewayService.save(gateway), HttpStatus.OK);
                }else{
                    throw new GenericErrorException("Gateway selected have not that Device",
                            Constante.CODE_RESPONSE_ARGUMENT_NOT_VALID, Constante.NAME_CODE_RESPONSE_ARGUMENT_NOT_VALID);
                }
            }
            if(gateway == null){
                throw new GenericErrorException("Gateway Id not found",
                        Constante.CODE_RESPONSE_ARGUMENT_NOT_VALID, Constante.NAME_CODE_RESPONSE_ARGUMENT_NOT_VALID);
            }
            throw new GenericErrorException("Device Id not found",
                    Constante.CODE_RESPONSE_ARGUMENT_NOT_VALID, Constante.NAME_CODE_RESPONSE_ARGUMENT_NOT_VALID);

        }catch (GenericErrorException e){
            Logger.getLogger(GatewayController.class.getName()).log(Level.WARNING, e.getMessage());
            ResultDataResDto resultDataResDto = new ResultDataResDto();
            responseDto(resultDataResDto, e.getCodeError(),
                    e.getMessage(), e.getNameCodeError(), GatewayController.class.getName());
            return new ResponseEntity<>(resultDataResDto, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            Logger.getLogger(GatewayController.class.getName()).log(Level.SEVERE, e.getMessage());
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idGateway}/add-device/{idDevice}")
    public ResponseEntity<Object> addDeviceToGateway(@PathVariable Long idGateway, @PathVariable Long idDevice){
        try{
            Gateway gateway = gatewayService.findById(idGateway);
            Device newDevice = deviceService.findById(idDevice);
            if(gateway!= null && newDevice != null){
                List<Device> deviceList = gateway.getDeviceList();
                if(!deviceList.stream().anyMatch(devic-> devic.equals(newDevice))){
                    deviceList.add(newDevice);
                    if(deviceList.size() > 10){
                        throw new GenericErrorException("only 10 devices are allowed by Gateway",
                                Constante.CODE_RESPONSE_ARGUMENT_NOT_VALID, Constante.NAME_CODE_RESPONSE_ARGUMENT_NOT_VALID);
                    }
                    gateway.setDeviceList(deviceList);
                    return new ResponseEntity<>(gatewayService.save(gateway), HttpStatus.OK);
                }else{
                    throw new GenericErrorException("Device you are trying to add is already on the Gateway",
                            Constante.CODE_RESPONSE_ARGUMENT_NOT_VALID, Constante.NAME_CODE_RESPONSE_ARGUMENT_NOT_VALID);
                }
            }
            if(gateway == null){
                throw new GenericErrorException("Gateway Id not found",
                        Constante.CODE_RESPONSE_ARGUMENT_NOT_VALID, Constante.NAME_CODE_RESPONSE_ARGUMENT_NOT_VALID);
            }
            throw new GenericErrorException("Device Id not found",
                    Constante.CODE_RESPONSE_ARGUMENT_NOT_VALID, Constante.NAME_CODE_RESPONSE_ARGUMENT_NOT_VALID);

        }catch (GenericErrorException e){
            Logger.getLogger(GatewayController.class.getName()).log(Level.WARNING, e.getMessage());
            ResultDataResDto resultDataResDto = new ResultDataResDto();
            responseDto(resultDataResDto, e.getCodeError(),
                    e.getMessage(), e.getNameCodeError(), GatewayController.class.getName());
            return new ResponseEntity<>(resultDataResDto, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            Logger.getLogger(GatewayController.class.getName()).log(Level.SEVERE, e.getMessage());
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static void responseDto(ResultDataResDto resultData, String codeResponse, String descriptionResponse, String nameCodeResult, String source){
        resultData.setCode(codeResponse);
        resultData.setName(nameCodeResult);
        resultData.setDescription(descriptionResponse);
        resultData.setSource(source);
    }
}
