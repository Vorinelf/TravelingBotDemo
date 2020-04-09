package com.voronov.TravelingBotDemo.service;

import com.voronov.TravelingBotDemo.model.User;
import org.springframework.stereotype.Service;
import com.voronov.TravelingBotDemo.repo.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User findByChatId(long id) {
        return userRepository.findByChatId(id);
    }

    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> findNewUsers(){
        List<User> users = userRepository.findNewUsers();

        users.forEach((user) ->user.setNotified(true));
        userRepository.saveAll(users);
        return users;
    }

    @Transactional
    public void addUser (User user) {
        user.setAdmin(userRepository.count()==0);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser (User user) {
        userRepository.save(user);
    }







}
