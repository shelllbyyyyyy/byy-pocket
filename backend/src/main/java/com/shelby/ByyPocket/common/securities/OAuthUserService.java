package com.shelby.ByyPocket.common.securities;

import com.shelby.ByyPocket.modules.user.domain.entities.UserEntity;
import com.shelby.ByyPocket.modules.user.domain.factories.UserMapper;
import com.shelby.ByyPocket.modules.user.domain.services.UserService;
import com.shelby.ByyPocket.modules.user.persistence.models.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class OAuthUserService extends DefaultOAuth2UserService {

    @Autowired
    private UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String providerId = userRequest.getClientRegistration().getClientId();
        String provider = userRequest.getClientRegistration().getClientName();

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        Optional<UserEntity> entity = userService.findByEmail(email);
        User user;
        if(entity.isPresent()){
            user = UserMapper.toPersistence(entity.get());
        } else {
            UserEntity data = userService.createUser(name, email, "");

            user = UserMapper.toPersistence(data);
        }

        return new OAuthUser(oAuth2User, user);
    }
}


