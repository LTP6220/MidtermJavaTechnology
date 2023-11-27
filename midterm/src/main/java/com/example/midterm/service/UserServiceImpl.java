package com.example.midterm.service;


import com.example.midterm.model.User;
import com.example.midterm.model.UserData;
import com.example.midterm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(User user) {
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email);
    }

    private void encodePassword(User userEntity, UserData user) {
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public User login(User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
