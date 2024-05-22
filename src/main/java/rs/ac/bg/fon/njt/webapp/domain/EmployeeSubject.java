/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.njt.webapp.domain.enums.ClassType;

/**
 *
 *
 * Entity class representing the relationship between an employee and a subject.
 * This class is mapped to the 'employeesubject' table in the database.
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
 * @see jakarta.persistence.Entity
 * @see jakarta.persistence.Table
 *
 *
 * @author aleks
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employeesubject")
public class EmployeeSubject {

    /**
     * The composite key for this entity, which includes the employee and the
     * subject.
     */
    @EmbeddedId
    private EmployeeSubjectID id;
    /**
     * The type of class the employee is teaching.
     */
    @Column(name = "class_type")
    @Enumerated(EnumType.STRING)
    private ClassType classType;

    /**
     * Computes the hash code based on the composite key.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Checks if this entity is equal to another object. Two `EmployeeSubject`
     * entities are considered equal if their composite keys are equal.
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
        final EmployeeSubject other = (EmployeeSubject) obj;
        return Objects.equals(this.id, other.id);
    }

    /**
     * Returns a string representation of this entity.
     *
     * @return a string that contains the composite key and class type
     */
    @Override
    public String toString() {
        return "EmployeeSubject{" + "id=" + id + ", classType=" + classType + '}';
    }

}
