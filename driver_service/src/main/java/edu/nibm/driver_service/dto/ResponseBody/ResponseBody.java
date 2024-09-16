package edu.nibm.driver_service.dto.ResponseBody;


import edu.nibm.driver_service.dao.Driver;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class ResponseBody {
    private HashMap<String , List<Object>> response = new HashMap<>();

    public void addResponse(String status, String message){
        this.response.computeIfAbsent(status, k -> new ArrayList<>()).add(message);
    }

    public void addDriver(String user, Driver driver) {
        this.response.computeIfAbsent(user, k -> new ArrayList<>()).add(driver);
    }

}