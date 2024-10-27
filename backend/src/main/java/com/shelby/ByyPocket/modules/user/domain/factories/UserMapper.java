package com.shelby.ByyPocket.modules.user.domain.factories;

import com.shelby.ByyPocket.modules.user.domain.entities.UserEntity;
import com.shelby.ByyPocket.modules.user.persistence.models.User;
import com.shelby.ByyPocket.modules.user.presentation.dto.response.UserResponse;

public class UserMapper {
    public static UserEntity toDomain(User user) {
        UserEntity entity = new UserEntity(user.getId().toString());
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.verifiedUser(user.isVerified());
        entity.updateProvider(user.getProvider());

        return entity;
    }

    public static UserEntity toDomain(UserResponse user) {
        UserEntity entity = new UserEntity(user.getId());
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());

        return entity;
    }

    public static User toPersistence(UserEntity userEntity) {
        User user = new User();
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        user.setProvider(userEntity.getProvider());

        return user;
    }

    public static UserResponse toResponse (UserEntity user) {
        final UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setVerified(user.getIsVerified());
        response.setProvider(user.getProvider().toString().toLowerCase());

        return response;
    }
}
