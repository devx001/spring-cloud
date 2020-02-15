package com.devx.userservice.service.impl;

import com.devx.commonuser.model.entity.User;
import com.devx.userservice.model.repository.IUserRepository;
import com.devx.userservice.service.IUserService;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(
        IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User product) {
        return userRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

}
