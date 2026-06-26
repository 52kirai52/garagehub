package com.garagehub.domain.auth.controller;

import com.garagehub.domain.auth.dto.SendCodeRequest;
import com.garagehub.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/send-code")
    public ResponseEntity<String> sendCode(@RequestBody @Valid SendCodeRequest request) {
        authService.sendVerificationCode(request.getPhone());
        return ResponseEntity.ok("인증번호가 발송되었습니다.");
    }
}