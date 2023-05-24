package com.debugdemons.bimdb.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JwtUtilTest {


    @Test
    void getUsernameFromJWTNull() {
        JwtUtil jwtUtil = new JwtUtil();
        Assertions.assertEquals(null, jwtUtil.getUsernameFromJWT(null));
    }
}