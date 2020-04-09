package com.voronov.TravelingBotDemo.repo;

import com.voronov.TravelingBotDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

   @Query("SELECT u FROM User u WHERE u.notified = false")
    List<User> findNewUsers();

    User findByChatId(Long id);
}
