package com.webshop.webshopbackend.common.security.provider;

import com.webshop.webshopbackend.domain.DAO.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceProvider {

    private final UserDAO userDao;

    @Bean
    public UserDetailsService userDetailsService() {
        return userDao::getByEmail;
    }
}
