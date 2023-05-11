package com.example.springsercurity.service.impl;

import com.example.springsercurity.dto.request.LoginRequest;
import com.example.springsercurity.dto.request.RegisterRequest;
import com.example.springsercurity.dto.response.AuthenticationResponse;
import com.example.springsercurity.exception.CustomException;
import com.example.springsercurity.model.Role;
import com.example.springsercurity.model.Users;
import com.example.springsercurity.repo.UsersRepository;
import com.example.springsercurity.service.AuthenticationService;
import com.example.springsercurity.service.JwtService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) throws IllegalAccessException {
        try {
            validateRequest(request);
            Users user = Users.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();
            Users saveSaved = usersRepository.save(user);
            String token = jwtService.generateToken(saveSaved);
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) throws IllegalAccessException {
        try {
            validateRequest(request);
            Users user = usersRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "User %s not found".formatted(request.getUsername())));
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            String token = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            throw e;
        }
    }

    private void validateRequest(Object request) throws IllegalAccessException {
        Field[] fields = request.getClass().getDeclaredFields();
        for (Field field : fields) {
            Class<?> type = field.getType();
            if (type.isAssignableFrom(String.class)) {
                field.setAccessible(true);
                String value = String.valueOf(field.get(request));
                if (StringUtils.isBlank(value)) {
                    throw new CustomException(HttpStatus.BAD_REQUEST, "%s can not be null".formatted(field.getName()));
                }
            }
        }
    }
}
