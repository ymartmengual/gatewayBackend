package com.gateway.gatewaybackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Gateway implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_gateway")
    private Long idGateway;

    @NotNull(message = "serialNumber field is mandatory")
    @NotBlank(message = "serialNumber field must not be empty")
    @Size(max = 100, message = "value not valid, 100 characters max")
    @JsonProperty("serialNumber")
    @Column(name = "serial_number")
    private String serialNumber;

    @NotNull(message = "name field is mandatory")
    @NotBlank(message = "name field must not be empty")
    @Size(max = 100, message = "value not valid, 100 characters max")
    private String name;

    @NotNull(message = "name field is mandatory")
    @NotBlank(message = "name field must not be empty")
    @Pattern(regexp = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$", message = "value not valid, allowed only ipv4 address")
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGateway")
    private List<Device> deviceList;
}
