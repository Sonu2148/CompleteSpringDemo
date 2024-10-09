package com.example.CompleteSpringDemo.service;

import com.example.CompleteSpringDemo.model.User;
import com.example.CompleteSpringDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // all active active users
    public List<User> getAllActiveUsers() {
        return userRepository.findAll().stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
    }

    //all users active and deleted users
    public List<User> getAllUsersIncludingSoftDeleted() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        user.setActive(true);
        return userRepository.save(user);
    }

    public List<User> getUsersSortedByAge() {
        return userRepository.findAll().stream()
                .filter(User::isActive)
                .sorted(Comparator.comparingInt(User::getAge))
                .collect(Collectors.toList());
    }
    public User updateUserById(int id, User updatedUser) {
        User existingUserOptional = userRepository.findById(id).orElse(null);
        if (!Objects.isNull(existingUserOptional) ) {
//            User existingUser = existingUserOptional;
            if (updatedUser.getName() != null) {
                existingUserOptional.setName(updatedUser.getName());
            }
            if (updatedUser.getAge() != 0) {
                existingUserOptional.setAge(updatedUser.getAge());
            }
            return userRepository.save(existingUserOptional);
        } else {
            return null;
        }
    }

    public void softDeleteUser(int id) {
        User user = userRepository.findByIdAndActive(id,true);
        if (user != null) {
            user.setActive(false);
            userRepository.save(user);
        }
    }

    public void test() {
        System.out.println("working");
    }
}
