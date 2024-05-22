/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.controller;

import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;
import rs.ac.bg.fon.njt.webapp.service.DepartmentService;

/**
 * Controller for handling operations related to departments. This controller
 * provides endpoints for retrieving, creating, updating, and deleting
 * departments. Cross-Origin Resource Sharing (CORS) is enabled for all
 * endpoints.
 *
 * @author aleks
 */
@RestController
@RequestMapping("/department")
@CrossOrigin("*")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * Endpoint to retrieve all departments.
     *
     * @return ResponseEntity containing a list of all departments and HTTP
     * status OK.
     */
    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.findAll());
    }

    /**
     * Endpoint to retrieve a department by its ID.
     *
     * @param id The ID of the department to retrieve.
     * @return ResponseEntity containing the department with the specified ID
     * and HTTP status OK.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.findById(id));
    }

    /**
     * Endpoint to save a new department.
     *
     * @param departmentDto The DepartmentDto object containing the data of the
     * department to be saved.
     * @return ResponseEntity containing the saved department and HTTP status
     * OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.save(departmentDto));
    }

    /**
     * Endpoint to retrieve a department by its name.
     *
     * @param name The name of the department to retrieve.
     * @return ResponseEntity containing the department with the specified name
     * and HTTP status OK.
     */
    @GetMapping("/get/name/{name}")
    public ResponseEntity getByName(@PathVariable String name) {
        return new ResponseEntity(departmentService.findByName(name), HttpStatus.OK);
    }

    /**
     * Endpoint to delete a department by its ID.
     *
     * @param id The ID of the department to delete.
     * @return ResponseEntity containing a success message and HTTP status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("izbrisana je katedra sa id: " + id);
    }

    /**
     * Endpoint to update an existing department.
     *
     * @param dto The DepartmentDto object containing the updated data for the
     * department.
     * @return ResponseEntity containing the updated department and HTTP status
     * OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody DepartmentDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.edit(dto));
    }

}
