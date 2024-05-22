/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.njt.webapp.controller.customRequestBodyAnotations.JsonArgEmployeeSubjectList;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeSubjectDto;
import rs.ac.bg.fon.njt.webapp.service.EmployeeSubjectService;

/**
 * Controller for handling operations related to Employee-Subject associations.
 * Allows CRUD operations for employee-subject relationships. Requires ADMIN
 * role authorization for certain operations. Cross-origin requests are
 * permitted from all origins.
 *
 * @author aleks
 */
@RestController
@RequestMapping("/employeeSubject")
@CrossOrigin("*")
public class EmployeeSubjectController {

    @Autowired
    private EmployeeSubjectService employeeSubjectService;

    /**
     * Endpoint to save an employee-subject association.
     *
     * @param employeeSubjectDto The EmployeeSubjectDto object containing data
     * for the association.
     * @return ResponseEntity containing the saved employee-subject association
     * and HTTP status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody EmployeeSubjectDto employeeSubjectDto) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeSubjectService.save(employeeSubjectDto));
    }

    /**
     * Endpoint to update an existing employee-subject association.
     *
     * @param dto The EmployeeSubjectDto object containing updated data for the
     * association.
     * @return ResponseEntity containing the updated employee-subject
     * association and HTTP status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody EmployeeSubjectDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeSubjectService.edit(dto));
    }

    /**
     * Endpoint to get employee-subject associations by employee ID.
     *
     * @param id The ID of the employee.
     * @return ResponseEntity containing the list of employee-subject
     * associations for the specified employee and HTTP status OK.
     */
    @GetMapping("/get/employee/{id}")
    public ResponseEntity getByEmployee(@PathVariable Long id) {
        return new ResponseEntity(employeeSubjectService.findByEmployee(id), HttpStatus.OK);
    }

    /**
     * Endpoint to get employee-subject associations by subject ID.
     *
     * @param id The ID of the subject.
     * @return ResponseEntity containing the list of employee-subject
     * associations for the specified subject and HTTP status OK.
     */
    @GetMapping("/get/subject/{id}")
    public ResponseEntity getBySubject(@PathVariable Long id) {
        return new ResponseEntity(employeeSubjectService.findBySubject(id), HttpStatus.OK);
    }

    /**
     * Endpoint to delete an employee-subject association.
     *
     * @param dto The EmployeeSubjectDto object containing data for the
     * association to delete.
     * @return ResponseEntity containing a success message and HTTP status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity delete(@Valid @RequestBody EmployeeSubjectDto dto) {
        employeeSubjectService.delete(dto);
        return ResponseEntity.status(HttpStatus.OK).
                body("izbrisan je EmployeeSubject predmet: " + dto.getId().getSubject().getName() + " zaposlenog: " + dto.getId().getEmployee().getFirstname());
    }

    /**
     * Endpoint to save changes to employee-subject associations, including both
     * saving and deleting associations.
     *
     * @param toSave List of EmployeeSubjectDto objects to save.
     * @param toDelete List of EmployeeSubjectDto objects to delete.
     * @return ResponseEntity containing the result of the operation and HTTP
     * status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveChanges")
    public ResponseEntity saveChanges(@JsonArgEmployeeSubjectList(path = "/toSave") List<EmployeeSubjectDto> toSave, @JsonArgEmployeeSubjectList(path = "/toDelete") List<EmployeeSubjectDto> toDelete) {
        System.out.println("/save changes===========================");
        System.out.println(toSave);
        System.out.println(toDelete);
        return ResponseEntity.status(HttpStatus.OK).body(employeeSubjectService.saveChanges(toSave, toDelete));
    }

}
