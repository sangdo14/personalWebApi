package com.example.basic.controller;

import com.example.basic.dto.LoginDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.entity.MemberEntity;
import com.example.basic.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {
    private final LoginService loginService;

    //접속시 세션에 담겨 있는 사용자의 정보 유무 파악하여 인증 결과 반환
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginDTO loginDTO,  HttpSession session) {
        MemberEntity loginUser = loginService.checkUser(loginDTO.getUserid(), loginDTO.getPassword());

        Map<String, Object> response = new HashMap<>();

        if (loginUser != null) {
            session.setAttribute("loginUser", loginUser);
            response.put("isLogin", true);
            response.put("username", loginUser.getName());
        } else {
            response.put("isLogin", false);
        }
        return response;
    }

    @GetMapping("/check")
    public Map<String, Object> getSessionUser(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        MemberEntity loginUser = (MemberEntity) session.getAttribute("loginUser");

        if (loginUser != null) {
            response.put("isLogin", true);
            response.put("username", loginUser.getName());
            response.put("intro", loginUser.getIntro());
            // 필요에 따라 더 추가 가능
        } else {
            response.put("isLogin", false);
        }
        return response;
    }

    //강제로 세션에서 사용자 정보를 제거해 로그아웃 처리
    @GetMapping("/logout")
    public Map<String, String> logout(HttpSession session) {
        session.invalidate();  // 세션 초기화 (로그아웃)

        Map<String, String> response = new HashMap<>();
        response.put("message","로그아웃 되었습니다.");
        return response;
    }
}



