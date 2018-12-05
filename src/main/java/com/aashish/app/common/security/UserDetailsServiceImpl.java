package com.aashish.app.common.security;

import com.aashish.app.auth.models.AuthModel;
import com.aashish.app.auth.repos.AuthRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private AuthRepo applicationUserRepository;

    public UserDetailsServiceImpl(AuthRepo applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    /**
     * Method is used to load user by user email.
     *
     * @param userEmail - user email
     * @return
     * @throws UsernameNotFoundException - exception
     */
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        AuthModel applicationUser = applicationUserRepository.findByUserEmail(userEmail);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(userEmail);
        }
        return new User(applicationUser.getUserEmail(), applicationUser.getUserPassword(), emptyList());
    }
}