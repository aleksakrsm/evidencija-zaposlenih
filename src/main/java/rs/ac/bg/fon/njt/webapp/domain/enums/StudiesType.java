/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.domain.enums;

import java.io.Serializable;

/**
 * * Enumeration representing the types of studies available.
 * This enum can be used to specify the type of study program in various entities such as Subject.
 * 
 * @author aleks
 */
public enum StudiesType implements Serializable{
    /**
     * Represents undergraduate studies.
     */
    UNDERGRADUATE,
    /**
     * Represents master's studies.
     */
    MASTER,
    /**
     * Represents PhD studies.
     */
    PhD,
    /**
     * Represents specialized studies.
     */
    SPECIALIZED
}
