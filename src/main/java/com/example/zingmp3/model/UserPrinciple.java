package com.example.zingmp3.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
// xay dung lai lop UserPrinciple theo y minh
// bất kỳ đâu trong ứng dụng bạn cũng có thể lôi UserPrinciple này ra
@Data
public class UserPrinciple implements UserDetails {
    private Long id;

    private String username;

    private String password;

    private String avatar;

    private Collection<? extends GrantedAuthority> roles;

    public UserPrinciple(Long id, String username, String password, String avatar,Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.roles = roles;
    }
// buid 1 Userprinciple từ user
    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new UserPrinciple(user.getId(), user.getUsername(),
                user.getPassword(), user.getAvatar(),authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
