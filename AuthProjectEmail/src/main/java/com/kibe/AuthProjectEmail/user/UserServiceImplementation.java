package com.kibe.AuthProjectEmail.user;

import com.kibe.AuthProjectEmail.exceptions.UserAlreadyExistsException;
import com.kibe.AuthProjectEmail.registration.RegistrationRequest;
import com.kibe.AuthProjectEmail.registration.token.VerificationToken;
import com.kibe.AuthProjectEmail.registration.token.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(RegistrationRequest request) {
        Optional<User> user = this.findUserByEmail(request.email());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("User with email" + request.email() + "already exists!");
        }
        var newUser = new User();
        newUser.setEmail(request.email());
        newUser.setFirstName(request.firstName());
        newUser.setLastName(request.lastName());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(request.role());
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUserVerificationToken(User user, String token) {
        var verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String verificationToken) {
        VerificationToken token = tokenRepository.findByToken(verificationToken);
        if (token == null) {
            return "Invalid verification token";
        }
        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if (token.getExpirationTime().getTime() - calendar.getTime().getTime() < 0) {
            tokenRepository.delete(token);
            return "Expired verification token!";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "User verified!";
    }
}
