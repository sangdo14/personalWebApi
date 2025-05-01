package com.example.basic.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

import jakarta.persistence.Table;
import lombok.*;

@Table(name = "Member")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberEntity {
    @Id
    private String userid;

    private String password;
    private String name;
    private String email;
    private String intro;
    private LocalDateTime created_dt;
}