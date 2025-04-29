package com.example.basic.service;

import com.example.basic.dto.MemberDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.entity.MemberEntity;
import com.example.basic.repository.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepo memberRepo;

    // 회원가입 db저장
    public String processJoin(MemberDTO dto) {

        MemberEntity user = new MemberEntity(dto.getUserid(), dto.getPw1(), dto.getName(), dto.getEmail(), dto.getComments(), null);
        memberRepo.save(user);
        return "회원정보가 저장되었습니다";
    }

    // 페이지 단위로 db조회
    public Page<MemberEntity> getUsersByPage(int page, int size){
        return memberRepo.findAll(PageRequest.of(page, size));
    }

    // 회원 삭제
    public String delete(String userid){
        memberRepo.deleteById(userid);
        return "삭제 성공";
    }

    // 회원 수정 - 수정할 정보 가져오기
    public MemberEntity getUserByUserid(String userid){
        return memberRepo.findById(userid).orElseThrow(()-> new RuntimeException("해당 아이디의 유저 없음"));
    }

    // 회원 수정 - 가져온 정보 수정
    public String updateUser(MemberDTO dto){
        MemberEntity user = new MemberEntity(dto.getUserid(), dto.getPw1(), dto.getName(), dto.getEmail(), dto.getComments(), null);
        memberRepo.save(user);
        return "수정 성공";
    }
}
