package com.shelby.ByyPocket.modules.user.domain.services;

import com.shelby.ByyPocket.modules.user.domain.entities.UserEntity;
import com.shelby.ByyPocket.modules.user.domain.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity createUser(String username, String email, String password) {
        final UserEntity user = new UserEntity(null);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        return userRepository.save(user);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void delete(String email) {
        userRepository.delete(email);
    }

    public String verifyUser(UserEntity data) {
        data.verifiedUser(true);
        userRepository.verifyUser(data);

        return data.getId();
    }
}
