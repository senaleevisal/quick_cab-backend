package edu.nibm.driver_service.controller;

import edu.nibm.driver_service.dao.Driver;
import edu.nibm.driver_service.dto.ResponseBody.ResponseBody;
import edu.nibm.driver_service.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/register")
    public ResponseBody registerDriver(@RequestBody Driver driver) {
        Driver registeredDriver = driverService.registerDriver(driver);
        ResponseBody responseBody = new ResponseBody();
        responseBody.addResponse("success", "Driver registered successfully");
        responseBody.addDriver("driver", registeredDriver);
        return responseBody;
    }

    @PutMapping("/update")
    public ResponseBody updateDriver(@RequestBody Driver driver) {
        Driver updatedDriver = driverService.updateDriver(driver);
        ResponseBody responseBody = new ResponseBody();
        responseBody.addResponse("success", "Driver updated successfully");
        responseBody.addDriver("driver", updatedDriver);
        return responseBody;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseBody deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        ResponseBody responseBody = new ResponseBody();
        responseBody.addResponse("success", "Driver deleted successfully");
        return responseBody;
    }

    @PostMapping("/login")
    public ResponseBody loginDriver(@RequestParam String username, @RequestParam String password) {
        String loginMessage = driverService.loginDriver(username, password);
        ResponseBody responseBody = new ResponseBody();
        responseBody.addResponse("success", loginMessage);
        return responseBody;
    }

    @PutMapping("/change-password")
    public ResponseBody changePassword(@RequestParam Long id, @RequestParam String newPassword) {
        String changePasswordMessage = driverService.changePassword(id, newPassword);
        ResponseBody responseBody = new ResponseBody();
        responseBody.addResponse("success", changePasswordMessage);
        return responseBody;
    }
}