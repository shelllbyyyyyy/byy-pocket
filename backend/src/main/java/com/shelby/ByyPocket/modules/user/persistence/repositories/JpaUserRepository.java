package com.shelby.ByyPocket.modules.user.persistence.repositories;

import com.shelby.ByyPocket.modules.user.persistence.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.email = ?1")
    void deleteByEmail(String email);


    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isVerified = true WHERE u.email = ?1")
    void verifyUser(String email);

}
