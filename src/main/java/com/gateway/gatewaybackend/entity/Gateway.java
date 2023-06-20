package com.gateway.gatewaybackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Gateway implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_gateway")
    private Long idGateway;

    @NotNull(message = "serialNumber field is mandatory")
    @NotBlank(message = "serialNumber field must not be empty")
    @Size(max = 100, message = "value not valid, 100 characters max")
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

    @Valid
    @NotNull(message = "devices field is mandatory")
    @JsonProperty("devices")
    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "gateway_device", joinColumns = @JoinColumn(name = "id_gateway"),
            inverseJoinColumns = @JoinColumn(name = "id_device"))
    private List<Device> deviceList;
}
