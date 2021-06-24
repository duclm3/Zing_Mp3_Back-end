package com.example.zingmp3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
//tao ra lop DTO de trả ve cho nguoi dùng
public class JwtResponse {
    private Long id;

    private String username;

    private String type = "Bearer";

    private String token;

    private String avatar;

    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(Long id, String username, String token, String avatar, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.avatar = avatar;
        this.roles = roles;
    }

//    public JwtResponse(Long id, String username, String token, Collection<? extends GrantedAuthority> roles) {
//        this.id = id;
//        this.username = username;
//        this.token = token;
//        this.roles = roles;
//    }
}
