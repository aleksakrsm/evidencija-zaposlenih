/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import java.time.LocalDate;

/**
 *
 * @author aleks
 */
public class EmployeeAcademicTitleDto {

    private HistoryItemIdDto historyItemIdDto;
    private LocalDate beginDate;
    private LocalDate endDate;

    public EmployeeAcademicTitleDto() {
    }

    public EmployeeAcademicTitleDto(HistoryItemIdDto historyItemID, LocalDate beginDate, LocalDate endDate) {
        this.historyItemIdDto = historyItemID;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public HistoryItemIdDto getHistoryItemID() {
        return historyItemIdDto;
    }

    public void setHistoryItemID(HistoryItemIdDto historyItemID) {
        this.historyItemIdDto = historyItemID;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
}
