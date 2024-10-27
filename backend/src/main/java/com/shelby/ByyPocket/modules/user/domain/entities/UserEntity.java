package com.shelby.ByyPocket.modules.user.domain.entities;


public class UserEntity {
    private final String id;
    private String username;
    private String email;
    private String password;
    private boolean isVerified = false;
    private UserProvider provider = UserProvider.local;

    public UserEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsVerified() {
        return this.isVerified;
    }

    public void verifiedUser(boolean data) {
        this.isVerified = data;
    }

    public UserProvider getProvider() {
        return this.provider;
    }

    public void updateProvider(UserProvider provider) {
        this.provider = provider;
    }

    public void changeEmail(String newEmail) {
        this.email = newEmail;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void changeUsername(String newUsername) {
        this.username = newUsername;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isVerified='" + isVerified + '\'' +
                ", provider='" + provider + '\'' +
                '}';
    }

}
