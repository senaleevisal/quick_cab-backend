package edu.nibm.driver_service.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String contact;
    private String address;
    private String license;
    private String nic;
    private String status;
    private String vehicle;
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleColor;
    private String vehicleModel;
    private String vehicleYear;
    private String vehicleStatus;
    private String vehicleOwner;
    private String vehicleOwnerContact;
    private String vehicleOwnerAddress;
    private String vehicleOwnerNic;
    private String vehicleOwnerEmail;
}
