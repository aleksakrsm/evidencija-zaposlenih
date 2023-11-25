/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

/**
 *
 * @author aleks
 */
public class HistoryItemIdDto {
    
    private EmployeeDto employee;
    private AcademicTitleDto academicTitle;

    public HistoryItemIdDto() {
    }

    public HistoryItemIdDto(EmployeeDto employee, AcademicTitleDto academicTitle) {
        this.employee = employee;
        this.academicTitle = academicTitle;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public AcademicTitleDto getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitleDto academicTitle) {
        this.academicTitle = academicTitle;
    }
    
    
}
