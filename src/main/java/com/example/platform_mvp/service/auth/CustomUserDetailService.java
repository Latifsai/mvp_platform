package com.example.platform_mvp.service.auth;

import com.example.platform_mvp.repository.UserRepository;
import com.example.platform_mvp.validation.ExceptionMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository repository;

    public CustomUserDetailService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Load User by username
     * @param username String
     * @return UserDetails
     */

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(ExceptionMessage.NOT_FOUND_USER_MESSAGE, username)));
    }
}
