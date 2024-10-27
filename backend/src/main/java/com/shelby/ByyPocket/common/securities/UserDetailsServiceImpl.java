package com.shelby.ByyPocket.common.securities;

import com.shelby.ByyPocket.modules.user.application.usecase.FindByEmail;
import com.shelby.ByyPocket.modules.user.domain.entities.UserEntity;
import com.shelby.ByyPocket.modules.user.domain.factories.UserMapper;
import com.shelby.ByyPocket.modules.user.presentation.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final FindByEmail findByEmail;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserResponse> user = findByEmail.execute(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        UserEntity userEntity = UserMapper.toDomain(user.get());

        return UserDetailsImpl.build(userEntity);
    }
}

