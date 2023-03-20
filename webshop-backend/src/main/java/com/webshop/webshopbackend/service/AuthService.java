package com.webshop.webshopbackend.service;

import com.webshop.webshopbackend.common.util.JWTUtil;
import com.webshop.webshopbackend.domain.DAO.UserDAO;
import com.webshop.webshopbackend.domain.entity.User;
import com.webshop.webshopbackend.domain.entity.VerificationToken;
import com.webshop.webshopbackend.domain.event.OnRegistrationCompleteEvent;
import com.webshop.webshopbackend.domain.model.JwtResponse;
import com.webshop.webshopbackend.domain.model.LoginCredentials;
import com.webshop.webshopbackend.domain.model.RegisterRequest;
import com.webshop.webshopbackend.domain.model.Role;
import com.webshop.webshopbackend.exception.Conflict;
import com.webshop.webshopbackend.exception.NotFound;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final ApplicationEventPublisher eventPublisher;

    public void register(RegisterRequest registerRequest, HttpServletRequest request) throws Conflict {
        if(userDAO.existsByEmail(registerRequest.getEmail())) {
            throw new Conflict("User", "email");
        } else {
            User user = User.builder()
                    .name(registerRequest.getName())
                    .lastName(registerRequest.getLastName())
                    .email(registerRequest.getEmail())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .role(Role.USER)
                    .build();
            User registeredUser = userDAO.saveToDatabase(user);
            String appUrl = request.getRequestURL().toString();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, request.getLocale(), appUrl));
        }
    }

    public void confirmRegistration(String token) throws NotFound {
        VerificationToken verificationToken = userDAO.getVerificationToken(token);
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return;
        }
        user.setEnabled(true);
        userDAO.saveToDatabase(user);
    }

    public JwtResponse authenticate(LoginCredentials request) throws AuthenticationException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userDAO.getByEmail(request.getEmail());
        var jwtToken = jwtUtil.generateToken(user);
        return JwtResponse.builder()
                .authToken(jwtToken)
                .build();
    }
}
