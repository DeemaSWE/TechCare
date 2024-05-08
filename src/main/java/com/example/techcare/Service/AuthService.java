package com.example.techcare.Service;

import com.example.techcare.Model.User;
import com.example.techcare.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    // 1- Sara: Admin get all users
    public List<User> getAllUsers() {
        return authRepository.findAll();
    }
}
