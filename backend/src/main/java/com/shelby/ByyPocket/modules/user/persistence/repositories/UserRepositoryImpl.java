package com.shelby.ByyPocket.modules.user.persistence.repositories;

import com.shelby.ByyPocket.modules.user.domain.entities.UserEntity;
import com.shelby.ByyPocket.modules.user.domain.factories.UserMapper;
import com.shelby.ByyPocket.modules.user.domain.repositories.UserRepository;
import com.shelby.ByyPocket.modules.user.persistence.models.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaDataUserRepository;

    @Override
    public UserEntity save(UserEntity user) {
        User data = UserMapper.toPersistence(user);
        User result = jpaDataUserRepository.save(data);

        return UserMapper.toDomain(result);
    };

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        Optional<User> result = jpaDataUserRepository.findByEmail(email);

        return result.map(UserMapper::toDomain);
    };

    @Override
    public void delete(String email) {
        jpaDataUserRepository.deleteByEmail(email);
    }

    @Override
    public void verifyUser(UserEntity data) {
        jpaDataUserRepository.verifyUser(data.getEmail());
    }
}
