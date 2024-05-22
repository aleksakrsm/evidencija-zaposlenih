/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.domain;

import jakarta.persistence.Column;
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
 * Entity class representing the academic title of an employee. This class is
 * mapped to the 'employeeacademictitle' table in the database.
 *
 * <p>
 * Uses Lombok annotations for boilerplate code reduction:
 * <ul>
 * <li>{@link Data} generates getters, setters, and other utility methods.
 * <li>{@link Builder} provides a builder pattern implementation.
 * <li>{@link NoArgsConstructor} generates a no-args constructor.
 * <li>{@link AllArgsConstructor} generates an all-args constructor.
 * </ul>
 *
 * <p>
 * Includes overrides for {@code hashCode}, {@code equals}, and {@code toString}
 * methods.
 *
 * @see jakarta.persistence.Entity
 * @see jakarta.persistence.Table
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

    /**
     * The composite key for this entity, which includes the employee, academic
     * title, and begin date.
     */
    @EmbeddedId
    private HistoryItemID historyItemID;
    /**
     * The end date of the academic title.
     */
    @Column(name = "end_date")
    private LocalDate endDate;

    /**
     * Computes the hash code for this entity based on the composite key.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.historyItemID);
        return hash;
    }

    /**
     * Checks if this entity is equal to another object. Two
     * `EmployeeAcademicTitle` entities are considered equal if their composite
     * keys are equal.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
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

    /**
     * Returns a string representation of this entity.
     *
     * @return a string that contains the employee's first name, academic title,
     * start date, and end date
     */
    @Override
    public String toString() {
        return historyItemID.getEmployee().getFirstname() + " is " + historyItemID.getAcademicTitle() + " Start date:" + historyItemID.getBeginDate() + " - " + endDate + "\n";
    }

}
