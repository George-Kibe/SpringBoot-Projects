package com.kibe.AuthProjectEmail.user;

import com.kibe.AuthProjectEmail.registration.RegistrationRequest;
import com.kibe.AuthProjectEmail.registration.token.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    User registerUser(RegistrationRequest request);
    Optional<User> findUserByEmail(String email);

    void saveUserVerificationToken(User user, String verificationToken);

    String validateToken(String verificationToken);
}
