package com.kibe.AuthProjectEmail.user;

import com.kibe.AuthProjectEmail.events.RegistrationCompleteEvent;
import com.kibe.AuthProjectEmail.registration.RegistrationRequest;
import com.kibe.AuthProjectEmail.registration.token.VerificationToken;
import com.kibe.AuthProjectEmail.registration.token.VerificationTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;

    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request) {
        User user = userService.registerUser(registrationRequest);
        // Publish a registration event
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Successfully Registered! Please check your email to complete registration and confirmation!";
    }
    // register a user
    @PostMapping("/one")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/verifyEmail")
    private String verifyEmail(@RequestParam String token){
        VerificationToken verificationToken  = tokenRepository.findByToken(token);
        if (verificationToken.getUser().isEnabled()){
            return "This account has already been verified. You can login";
        }
        String verificationResult = userService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("User verified!")){
            return "This account has been verified. You can Now login!";
        }
        return verificationResult;
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}




