/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.security.auth.customClasses;

import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author aleks
 */
public class CustomWebAuthenticationDetails {

    private static final long serialVersionUID = 540L;
    private final String remoteAddress;
    private final String sessionId;

    public CustomWebAuthenticationDetails(java.lang.String remoteAddress, java.lang.String sessionId) {
        this.remoteAddress = remoteAddress;
        this.sessionId = sessionId;
    }

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        this(request.getRemoteAddr(), request.getSession().getId());
    }

}
