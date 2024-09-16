package edu.nibm.user_service.repository;


import edu.nibm.user_service.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
    UserEntity findByUsernameAndPassword(String username, String password);
}
