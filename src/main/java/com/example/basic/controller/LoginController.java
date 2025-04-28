package com.example.basic.controller;

import com.example.basic.dto.LoginDTO;
import com.example.basic.entity.JoinEntity;
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
    public Map<String, String> login(@RequestBody LoginDTO loginDTO,  HttpSession session) {
        JoinEntity user = loginService.checkUser(loginDTO.getUname(), loginDTO.getEmail());
        System.out.println(user);

        Map<String, String> response = new HashMap<>();

        if (user != null) {
            session.setAttribute("loginUser", user);
            System.out.println(session);
            response.put("message","로그인 성공");
            response.put("username", user.getUname());
            return response;
        } else {
            response.put("message","로그인 실패");
            return response;
        }
    }

    @GetMapping("/check")
    public Map<String, Object> getSessionUser(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        // Session 의 값을 바로 읽거나 반환터리 불가
        // getAttribute라는 전용 메서드를 통해서만 loginUser 키로 접근해 담긴 값을 가져옴
        // (JoinEntity) 이때 session에 담긴 값을 강제로 Entity 타입으로 형변환 해서 loginUser에 담음
        // 굳이 이렇게 번거롭게 컨트롤러를 추가 하는 이유는 단지 로그인 유무만 판단하기 위함인데 다시 DB 탐색을 해야하는 소비비용을 없애기 위함
        JoinEntity loginUser = (JoinEntity) session.getAttribute("loginUser");

        if (loginUser != null) {
            response.put("isLogin", true);
            // 엔티티타입이므로 전용 getter로 값 추출해서 응답 객체에 전달
            response.put("userId", loginUser.getId());
            response.put("uname", loginUser.getUname());
            response.put("email", loginUser.getEmail());
            // 필요에 따라 더 추가 가능
        } else {
            response.put("isLogin", false);
            response.put("message", "로그인된 사용자가 없습니다.");
        }

        return response;
    }



    //강제로 세션에서 사용자 정보를 제거해 로그아웃 처리
    @GetMapping("/logout")
    public Map<String, String> logout(HttpSession session) {
        session.invalidate();  // 세션 초기화 (로그아웃)
        Map<String, String> response = new HashMap<>();
        response.put("message","로그아웃 완료");
        return response;
    }
}



