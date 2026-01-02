package com.example.productioReady.productioReady.Auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //once we integrate spring security we can fetch the logged in user details from security context by following below steps
        //get security context
        //get authentication from security context
        //get principal from authentication
        //cast principal to UserDetails
        //get username from UserDetails
        return Optional.of("Abhinav Kumar Gupta");
    }
}
