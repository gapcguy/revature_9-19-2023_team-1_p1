package com.revature.p1.banking.Utils;

import jakarta.servlet.http.HttpServletRequest;

public class JakartaAuthenticationDetails {

    private final String remoteAddress;
    private final String sessionId;

    public JakartaAuthenticationDetails(HttpServletRequest request) {
        this.remoteAddress = request.getRemoteAddr();
        this.sessionId = request.getSession(false) != null ? request.getSession().getId() : null;
    }
}
