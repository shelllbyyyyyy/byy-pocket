package com.shelby.ByyPocket.modules.user.persistence.models;

import com.shelby.ByyPocket.modules.user.domain.entities.UserProvider;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = {"username"})
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", updatable = false, nullable = false)
    private UUID id;

    @Column(length = 20, nullable = false)
    private String username;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isVerified;

    @Enumerated(EnumType.STRING) // Ensures the enum is stored as a string
    @Column(nullable = false)
    private UserProvider provider;
}
