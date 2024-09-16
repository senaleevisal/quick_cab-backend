package edu.nibm.driver_service.service;

import edu.nibm.driver_service.dao.Driver;

public interface DriverService {
    Driver registerDriver(Driver driver);
    Driver updateDriver(Driver driver);
    void deleteDriver(Long id);
    String loginDriver(String username, String password);
    String changePassword(Long id, String newPassword);
}