package edu.nibm.user_service.dto.ResponseBody;

import edu.nibm.user_service.dto.User;
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

    public void addUser(String user, User user1) {
        this.response.computeIfAbsent(user, k -> new ArrayList<>()).add(user1);
    }

}