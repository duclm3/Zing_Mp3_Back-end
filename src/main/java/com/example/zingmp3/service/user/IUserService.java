package com.example.zingmp3.service.user;

import com.example.zingmp3.model.User;
import com.example.zingmp3.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String username);
    User getCurrentUser();
}
