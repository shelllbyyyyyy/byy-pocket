package com.shelby.ByyPocket.modules.user.domain.repositories;

import com.shelby.ByyPocket.modules.user.domain.entities.UserEntity;
import java.util.Optional;

public interface UserRepository {
     UserEntity save(UserEntity user);
     Optional<UserEntity> findByEmail(String email);
     void delete(String email);
     void verifyUser(UserEntity data);
}
