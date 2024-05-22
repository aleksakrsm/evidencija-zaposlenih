/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.domain.enums;

import java.io.Serializable;

/**
 ** Enumeration representing the types of classes that an employee can teach.
 * This enum can be used in the EmployeeSubject entity to specify the class type.
 * 
 * @author aleks
 */
public enum ClassType implements Serializable{
    /**
     * Represents a lecture class type.
     */
    LECTURES,
    /**
     * Represents a practical class type.
     */
    PRACTICALS
}
