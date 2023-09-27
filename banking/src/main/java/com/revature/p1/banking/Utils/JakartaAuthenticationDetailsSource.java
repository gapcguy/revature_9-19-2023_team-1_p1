package com.revature.p1.banking.Utils;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import jakarta.servlet.http.HttpServletRequest;

public class JakartaAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, JakartaAuthenticationDetails> {

    @Override
    public JakartaAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new JakartaAuthenticationDetails(request);
    }
}
