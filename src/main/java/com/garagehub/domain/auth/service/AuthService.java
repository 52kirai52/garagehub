package com.garagehub.domain.auth.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.garagehub.domain.user.repository.UserRepository;

import java.util.Random;

@Service
public class AuthService {

    private final SmsService smsService;
    private final StringRedisTemplate redisTemplate;
    private final UserRepository userRepository;

    public AuthService(SmsService smsService, StringRedisTemplate redisTemplate, UserRepository userRepository) {
        this.smsService = smsService;
        this.redisTemplate = redisTemplate;
        this.userRepository = userRepository;
    }

    public void sendVerificationCode(String phone) {
        if (userRepository.existsByPhone(phone)) {
            throw new RuntimeException("이미 가입된 전화번호입니다.");
        }

        String code = generateCode();

        try {
            smsService.sendSms(phone, "[GarageHub] 인증번호: " + code);
            redisTemplate.opsForValue().set(
                "sms:code:" + phone, code, java.time.Duration.ofMinutes(5)
            );
        } catch (Exception e) {
            throw new RuntimeException("인증번호 발송에 실패했습니다.");
        }
    }

    public boolean verifyCode(String phone, String code) {
        String stored = redisTemplate.opsForValue().get("sms:code:" + phone);
        if (stored == null || !stored.equals(code)) {
            return false;
        }
        redisTemplate.delete("sms:code:" + phone);
        redisTemplate.opsForValue().set(
            "sms:verified:" + phone, "true", java.time.Duration.ofMinutes(10)
        );
        return true;
    }

    private String generateCode() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }
}