/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.domain.enums;

import java.io.Serializable;

/**
 *
 * Enumeration representing the roles of users in the application.
 * This enum can be used to specify the role of a User.
 * 
 * @author aleks
 */
public enum Role implements Serializable{
    /**
     * Represents a user role with standard permissions.
     */
    USER,
    /**
     * Represents an admin role with create, update and delete permissions.
     */
    ADMIN
}
