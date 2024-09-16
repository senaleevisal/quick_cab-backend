package edu.nibm.driver_service.repository;


import edu.nibm.driver_service.dto.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DriverRepository extends JpaRepository<DriverEntity,Long> {
    DriverEntity findByUsernameAndPassword(String username, String password);
}
