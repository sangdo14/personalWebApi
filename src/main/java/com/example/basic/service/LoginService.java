package com.example.basic.service;

import com.example.basic.entity.MemberEntity;
import com.example.basic.repository.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepo memberRepo;

    // id와 pw를 전달 받아 매칭된 데이터 확인
    public MemberEntity checkUser(String userid, String password) {
        return memberRepo.findByUseridAndPassword(userid, password);
    }
}
