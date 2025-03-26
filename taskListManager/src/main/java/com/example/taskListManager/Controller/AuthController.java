package com.example.taskListManager.Controller;

import com.example.taskListManager.DTO.AuthRequest;
import com.example.taskListManager.DTO.AuthResponse;
import com.example.taskListManager.DTO.UserDTO;
import com.example.taskListManager.Service.AuthService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid AuthRequest authRequest){
        logger.info("Регистрация нового пользователя: {}" + authRequest.getEmail());
        UserDTO userDTO = authService.register(authRequest.getEmail(), authRequest.getPassword());
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest){
        logger.info("Авторизация пользователя: {}" + authRequest.getEmail());
        String token = authService.authenticate(authRequest.getEmail(), authRequest.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
