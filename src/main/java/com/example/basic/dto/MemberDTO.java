package com.example.basic.dto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private String userid;
    private String pw1;
    private String name;
    private String email;
    private String comments;
}