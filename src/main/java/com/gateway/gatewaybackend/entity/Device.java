package com.gateway.gatewaybackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_device")
    private Long idDevice;

    @NotNull(message = "UID is mandatory")
    @Digits(integer = 20, fraction = 0, message = "value not valid, only numbers.")
    private String uid;

    @NotNull(message = "vendor field is mandatory")
    @NotBlank(message = "vendor field must not be empty")
    @Size(max = 100, message = "value not valid, 100 characters max")
    private String vendor;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @NotNull(message = "status field is mandatory")
    private boolean status;
}
