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
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.service.AcademicTitleService;

/**
 * Controller for handling operations related to Academic Titles. This
 * controller provides endpoints for retrieving, creating, updating, and
 * deleting academic titles. Access to certain endpoints is restricted to users
 * with the 'ADMIN' role.
 *
 * @author aleks
 */
@RestController
@RequestMapping("/academicTitle")
@CrossOrigin("*")
public class AcademicTitleController {

    @Autowired
    private AcademicTitleService academicTitleService;

    /**
     * Endpoint to get all academic titles.
     *
     * @return ResponseEntity containing a list of all academic titles and HTTP
     * status OK.
     */
    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        System.out.println("--------controller--------");
        System.out.println(academicTitleService.findAll());
        System.out.println("----------------");
        return ResponseEntity.status(HttpStatus.OK).body(academicTitleService.findAll());
    }

    /**
     * Endpoint to get an academic title by its ID.
     *
     * @param id The ID of the academic title.
     * @return ResponseEntity containing the academic title with the specified
     * ID and HTTP status OK.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(academicTitleService.findById(id));
    }

    /**
     * Endpoint to save a new academic title.
     *
     * @param academicTitleDto The DTO representing the academic title to be
     * saved.
     * @return ResponseEntity containing the saved academic title and HTTP
     * status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody AcademicTitleDto academicTitleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(academicTitleService.save(academicTitleDto));
    }

    /**
     * Endpoint to get an academic title by its name.
     *
     * @param name The name of the academic title.
     * @return ResponseEntity containing the academic title with the specified
     * name and HTTP status OK.
     */
    @GetMapping("/get/name/{name}")
    public ResponseEntity getByName(@PathVariable String name) {
        return new ResponseEntity(academicTitleService.findByName(name), HttpStatus.OK);
    }

    /**
     * Endpoint to delete an academic title by its ID.
     *
     * @param id The ID of the academic title to be deleted.
     * @return ResponseEntity with a success message and HTTP status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        academicTitleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("izbrisana je titula sa id: " + id);
    }

    /**
     * Endpoint to update an existing academic title.
     *
     * @param dto The DTO representing the updated academic title.
     * @return ResponseEntity containing the updated academic title and HTTP
     * status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody AcademicTitleDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(academicTitleService.edit(dto));
    }
}
