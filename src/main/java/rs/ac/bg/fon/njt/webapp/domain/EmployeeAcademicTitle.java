/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import java.time.LocalDate;
import java.util.Objects;


/**
 *
 * @author aleks
 */
@Entity
public class EmployeeAcademicTitle {

    @EmbeddedId
    private HistoryItemID historyItemID;
    
    private LocalDate beginDate;
    private LocalDate endDate;

    public EmployeeAcademicTitle() {
    }

    public EmployeeAcademicTitle(HistoryItemID historyItemID, LocalDate beginDate, LocalDate endDate) {
        this.historyItemID = historyItemID;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public HistoryItemID getHistoryItemID() {
        return historyItemID;
    }

    public void setHistoryItemID(HistoryItemID historyItemID) {
        this.historyItemID = historyItemID;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.historyItemID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmployeeAcademicTitle other = (EmployeeAcademicTitle) obj;
        return Objects.equals(this.historyItemID, other.historyItemID);
    }
    
    @Override
    public String toString() {
        return historyItemID.getEmployee().getFirstname() + " is " + historyItemID.getAcademicTitle() + " Start date:" + beginDate + " - " + endDate+ "\n";
    }

}
