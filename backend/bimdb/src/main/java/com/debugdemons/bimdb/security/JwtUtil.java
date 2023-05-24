package com.debugdemons.bimdb.security;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class JwtUtil {

    private static final String JWT_CLAIM_PREFERRED_USERNAME = "preferred_username";

    public String getUsernameFromJWT(Principal principal) {
        if (principal == null) {
            return null;
        }
        return ((JwtAuthenticationToken) principal)
                .getToken()
                .getClaimAsString(JWT_CLAIM_PREFERRED_USERNAME);
    }
}
