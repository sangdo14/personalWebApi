package com.example.basic.repository;

import com.example.basic.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<MemberEntity, String> {
    MemberEntity findByUseridAndPassword(String userid, String password);
}