package com.example.basic.controller;
import com.example.basic.dto.MemberDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.entity.MemberEntity;
import com.example.basic.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    // 회원 정보 저장
    @PostMapping("/join/create")
    public Map<String, Object> create(@RequestBody MemberDTO memberDTO) {
        String result = memberService.processJoin(memberDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("message", result);
        return response;
    }
    
    // 회원 리스트 출력
    @GetMapping("/admin")
    public Object showAdminPage(@RequestParam(defaultValue="0") int page){

        int pageSize = 10;
        Page<MemberEntity> userPage = memberService.getUsersByPage(page, pageSize);

        Map<String, Object> response = new HashMap<>();
        response.put("users", userPage.getContent());
        response.put("currentPage", page);
        response.put("totalPages", userPage.getTotalPages());
        return response;
    }

    // 회원 삭제
    @DeleteMapping("/admin/del/{userid}")
    public Map<String, String> delUser(@PathVariable String userid){
        String result = memberService.delete(userid);

        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        return response;
    }

    // 회원 수정 - 수정할 정보 가져오기
    @GetMapping("/admin/edit/{userid}")
    public MemberEntity editUser(@PathVariable String userid){
        return memberService.getUserByUserid(userid);
    }

    // 회원 수정 - 가져온 정보 수정
    @PutMapping("/admin/update")
    public Map<String, String> updateUser(@RequestBody MemberDTO memberDTO) {
        String result = memberService.updateUser(memberDTO);

        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        return response;
    }
}