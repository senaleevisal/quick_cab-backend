package edu.nibm.driver_service.service.IMPL;

import edu.nibm.driver_service.dao.Driver;
import edu.nibm.driver_service.dto.DriverEntity;
import edu.nibm.driver_service.repository.DriverRepository;
import edu.nibm.driver_service.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceIMPL implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Driver registerDriver(Driver driver) {
        DriverEntity entity = DriverEntity.builder()
                .id(driver.getId())
                .name(driver.getName())
                .username(driver.getUsername())
                .password(driver.getPassword())
                .email(driver.getEmail())
                .contact(driver.getContact())
                .address(driver.getAddress())
                .license(driver.getLicense())
                .nic(driver.getNic())
                .status(driver.getStatus())
                .vehicle(driver.getVehicle())
                .vehicleNumber(driver.getVehicleNumber())
                .vehicleType(driver.getVehicleType())
                .vehicleColor(driver.getVehicleColor())
                .vehicleModel(driver.getVehicleModel())
                .vehicleYear(driver.getVehicleYear())
                .vehicleStatus(driver.getVehicleStatus())
                .vehicleOwner(driver.getVehicleOwner())
                .vehicleOwnerContact(driver.getVehicleOwnerContact())
                .vehicleOwnerAddress(driver.getVehicleOwnerAddress())
                .vehicleOwnerNic(driver.getVehicleOwnerNic())
                .vehicleOwnerEmail(driver.getVehicleOwnerEmail())
                .build();
        DriverEntity savedEntity = driverRepository.save(entity);
        return Driver.builder()
                .id(savedEntity.getId())
                .name(savedEntity.getName())
                .username(savedEntity.getUsername())
                .password(savedEntity.getPassword())
                .email(savedEntity.getEmail())
                .contact(savedEntity.getContact())
                .address(savedEntity.getAddress())
                .license(savedEntity.getLicense())
                .nic(savedEntity.getNic())
                .status(savedEntity.getStatus())
                .vehicle(savedEntity.getVehicle())
                .vehicleNumber(savedEntity.getVehicleNumber())
                .vehicleType(savedEntity.getVehicleType())
                .vehicleColor(savedEntity.getVehicleColor())
                .vehicleModel(savedEntity.getVehicleModel())
                .vehicleYear(savedEntity.getVehicleYear())
                .vehicleStatus(savedEntity.getVehicleStatus())
                .vehicleOwner(savedEntity.getVehicleOwner())
                .vehicleOwnerContact(savedEntity.getVehicleOwnerContact())
                .vehicleOwnerAddress(savedEntity.getVehicleOwnerAddress())
                .vehicleOwnerNic(savedEntity.getVehicleOwnerNic())
                .vehicleOwnerEmail(savedEntity.getVehicleOwnerEmail())
                .build();
    }

    @Override
    public Driver updateDriver(Driver driver) {
        DriverEntity entity = DriverEntity.builder()
                .id(driver.getId())
                .name(driver.getName())
                .username(driver.getUsername())
                .password(driver.getPassword())
                .email(driver.getEmail())
                .contact(driver.getContact())
                .address(driver.getAddress())
                .license(driver.getLicense())
                .nic(driver.getNic())
                .status(driver.getStatus())
                .vehicle(driver.getVehicle())
                .vehicleNumber(driver.getVehicleNumber())
                .vehicleType(driver.getVehicleType())
                .vehicleColor(driver.getVehicleColor())
                .vehicleModel(driver.getVehicleModel())
                .vehicleYear(driver.getVehicleYear())
                .vehicleStatus(driver.getVehicleStatus())
                .vehicleOwner(driver.getVehicleOwner())
                .vehicleOwnerContact(driver.getVehicleOwnerContact())
                .vehicleOwnerAddress(driver.getVehicleOwnerAddress())
                .vehicleOwnerNic(driver.getVehicleOwnerNic())
                .vehicleOwnerEmail(driver.getVehicleOwnerEmail())
                .build();
        DriverEntity updatedEntity = driverRepository.save(entity);
        return Driver.builder()
                .id(updatedEntity.getId())
                .name(updatedEntity.getName())
                .username(updatedEntity.getUsername())
                .password(updatedEntity.getPassword())
                .email(updatedEntity.getEmail())
                .contact(updatedEntity.getContact())
                .address(updatedEntity.getAddress())
                .license(updatedEntity.getLicense())
                .nic(updatedEntity.getNic())
                .status(updatedEntity.getStatus())
                .vehicle(updatedEntity.getVehicle())
                .vehicleNumber(updatedEntity.getVehicleNumber())
                .vehicleType(updatedEntity.getVehicleType())
                .vehicleColor(updatedEntity.getVehicleColor())
                .vehicleModel(updatedEntity.getVehicleModel())
                .vehicleYear(updatedEntity.getVehicleYear())
                .vehicleStatus(updatedEntity.getVehicleStatus())
                .vehicleOwner(updatedEntity.getVehicleOwner())
                .vehicleOwnerContact(updatedEntity.getVehicleOwnerContact())
                .vehicleOwnerAddress(updatedEntity.getVehicleOwnerAddress())
                .vehicleOwnerNic(updatedEntity.getVehicleOwnerNic())
                .vehicleOwnerEmail(updatedEntity.getVehicleOwnerEmail())
                .build();
    }

    @Override
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public String loginDriver(String username, String password) {
        DriverEntity entity = driverRepository.findByUsernameAndPassword(username, password);
        if (entity != null) {
            return "Login successful";
        } else {
            return "Invalid credentials";
        }
    }

    @Override
    public String changePassword(Long id, String newPassword) {
        DriverEntity entity = driverRepository.findById(id).orElse(null);
        if (entity != null) {
            entity.setPassword(newPassword);
            driverRepository.save(entity);
            return "Password changed successfully";
        } else {
            return "Driver not found";
        }
    }
}