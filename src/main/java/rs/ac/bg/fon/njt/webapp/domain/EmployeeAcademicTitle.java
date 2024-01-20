/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author aleks
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employeeacademictitle")
public class EmployeeAcademicTitle {

    @EmbeddedId
    private HistoryItemID historyItemID;
    
    private LocalDate beginDate;
    private LocalDate endDate;

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
