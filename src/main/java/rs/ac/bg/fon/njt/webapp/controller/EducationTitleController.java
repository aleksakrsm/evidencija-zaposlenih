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
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;
import rs.ac.bg.fon.njt.webapp.service.EducationTitleService;

/**
 * Controller for handling operations related to education titles. This
 * controller provides endpoints for retrieving, creating, updating, and
 * deleting education titles. Cross-Origin Resource Sharing (CORS) is enabled
 * for all endpoints.
 *
 * @author aleks
 */
@RestController
@RequestMapping("/educationTitle")
@CrossOrigin("*")
public class EducationTitleController {

    @Autowired
    private EducationTitleService educationTitleService;

    /**
     * Endpoint to retrieve all education titles.
     *
     * @return ResponseEntity containing a list of all education titles and HTTP
     * status OK.
     */
    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(educationTitleService.findAll());
    }

    /**
     * Endpoint to retrieve an education title by its ID.
     *
     * @param id The ID of the education title to retrieve.
     * @return ResponseEntity containing the education title with the specified
     * ID and HTTP status OK.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(educationTitleService.findById(id));
    }

    /**
     * Endpoint to save a new education title.
     *
     * @param educationTitleDto The EducationTitleDto object containing the data
     * of the education title to be saved.
     * @return ResponseEntity containing the saved education title and HTTP
     * status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody EducationTitleDto educationTitleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(educationTitleService.save(educationTitleDto));
    }

    /**
     * Endpoint to retrieve an education title by its name.
     *
     * @param name The name of the education title to retrieve.
     * @return ResponseEntity containing the education title with the specified
     * name and HTTP status OK.
     */
    @GetMapping("/get/name/{name}")
    public ResponseEntity getByName(@PathVariable String name) {
        return new ResponseEntity(educationTitleService.findByName(name), HttpStatus.OK);
    }

    /**
     * Endpoint to delete an education title by its ID.
     *
     * @param id The ID of the education title to delete.
     * @return ResponseEntity containing a success message and HTTP status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        educationTitleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("izbrisana je titula sa id: " + id);
    }

    /**
     * Endpoint to update an existing education title.
     *
     * @param dto The EducationTitleDto object containing the updated data for
     * the education title.
     * @return ResponseEntity containing the updated education title and HTTP
     * status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody EducationTitleDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(educationTitleService.edit(dto));
    }

}
