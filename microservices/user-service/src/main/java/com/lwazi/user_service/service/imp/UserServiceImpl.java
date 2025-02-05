package com.lwazi.user_service.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lwazi.user_service.model.User;
import com.lwazi.user_service.repository.UserRepository;
import com.lwazi.user_service.service.UserService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Getter
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    
    @Override
    public User createUser(User user) {

        return userRepository.save(user);

    }

    @Override
    public User getUserById(Long id) throws Exception {

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        }

        throw new Exception("User not found with id: " + id);
        
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user, Long id) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new Exception("User not found with id: " + id);
        }

        return userRepository.save(user);
    }

    @Override
    public String deleteUser(Long id) throws Exception {
        
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new Exception("User not found with id: " + id);
        }

        userRepository.deleteById(id);
        return "User deleted successfully";
    }
    
}
