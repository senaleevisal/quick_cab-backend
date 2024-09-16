package edu.nibm.driver_service.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Driver {
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
