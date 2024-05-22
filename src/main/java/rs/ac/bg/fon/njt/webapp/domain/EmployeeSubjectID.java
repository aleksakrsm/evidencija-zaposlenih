/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 ** Composite key class for the {@link EmployeeSubject} entity. This class is
 * used as an embedded ID and represents the relationship between an employee
 * and a subject.
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
 * @see jakarta.persistence.Embeddable
 * @see EmployeeSubject
 * @author aleks
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EmployeeSubjectID {

    /**
     * The employee associated with the subject. This is a many-to-one
     * relationship, fetched lazily and merged on cascade.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "employee", referencedColumnName = "id")
    private Employee employee;
    /**
     * The subject associated with the employee. This is a many-to-one
     * relationship, fetched lazily and merged on cascade.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "subject", referencedColumnName = "id")
    private Subject subject;
}
