package com.example.onlinelearning.Service;

import com.example.onlinelearning.Entity.Order;
import com.example.onlinelearning.Entity.User;
import com.example.onlinelearning.POJO.PojoOrder;
import com.example.onlinelearning.POJO.PojoUser;
import com.example.onlinelearning.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public List<PojoUser> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<PojoUser> pojoUsers = new ArrayList<>();

        for (User user : users) {
            pojoUsers.add(PojoUser.fromEntity(user));
        }

        return pojoUsers;
    }

    public PojoUser createUser(PojoUser pojoUser) {
        User user = PojoUser.toEntity(pojoUser);
        User savedUser = userRepository.save(user);
        return PojoUser.fromEntity(savedUser);
    }

    public PojoUser updateUser(long userId, PojoUser pojoUser) {
        User user = PojoUser.toEntity(pojoUser);
        Optional<User> olduser = userRepository.findById(userId);
        if(olduser.isPresent()){
            olduser.get().setUsername(user.getUsername());
            olduser.get().setEmail(user.getEmail());
            olduser.get().setPassword(user.getPassword());

            User updatedUser = userRepository.save(olduser.get());
            return PojoUser.fromEntity(updatedUser);
        }

        return null;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<PojoUser> findUserByUsername(String username) {
        List<User> users = userRepository.findByUsername(username);
        List<PojoUser> pojoUsers = new ArrayList<>();

        for (User user : users) {
            pojoUsers.add(PojoUser.fromEntity(user));
        }

        return pojoUsers;
    }

//    public PojoUser findedUserByUsername(String username) {
//        List<User> users = userRepository.findByUsername(username);
//        if (!users.isEmpty()) {
//            return PojoUser.fromEntity(users.get(0));
//        }
//        return null;
//    }
    // Другие методы сервиса для выполнения операций над пользователями
}
