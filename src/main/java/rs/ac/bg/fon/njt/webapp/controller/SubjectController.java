/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;
import rs.ac.bg.fon.njt.webapp.dto.SubjectDto;
import rs.ac.bg.fon.njt.webapp.dto.SubjectFilterDto;
import rs.ac.bg.fon.njt.webapp.service.SubjectService;

/**
 * Controller for handling operations related to subjects. Provides endpoints
 * for CRUD operations on subjects. Requires ADMIN role authorization for
 * certain operations. Cross-origin requests are permitted from all origins.
 *
 * @author aleks
 */
@RestController
@RequestMapping("/subject")
@CrossOrigin("*")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    /**
     * Endpoint to retrieve all subjects.
     *
     * @return ResponseEntity containing the list of all subjects and HTTP
     * status OK.
     */
    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.findAll());
    }

    /**
     * Endpoint to retrieve a page of subjects.
     *
     * @param page Page number.
     * @param pageSize Number of items per page.
     * @param sortBy Field to sort by.
     * @param sortDirection Sort direction (asc or desc).
     * @return ResponseEntity containing the page of subjects and HTTP status
     * OK.
     */
    @GetMapping("/page")
    public ResponseEntity pageSubjects(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {
        Pageable pageable;
        if ("asc".equals(sortDirection)) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        }
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.findAll(pageable));
    }

    /**
     * Endpoint to retrieve a paginated and filtered list of subjects.
     *
     * @param subjectFilter The SubjectFilterDto object containing filter
     * criteria.
     * @param page Page number.
     * @param pageSize Number of items per page.
     * @param sortBy Field to sort by.
     * @param sortDirection Sort direction (asc or desc).
     * @return ResponseEntity containing the paginated and filtered list of
     * subjects and HTTP status OK.
     */
    @PostMapping("/pageFilterPaginate")
    public ResponseEntity pageFilterPaginate(
            @RequestBody SubjectFilterDto subjectFilter,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {
        Pageable pageable;
        if ("asc".equals(sortDirection)) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        }
        System.out.println(subjectFilter);
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.pageFilterPaginate(subjectFilter, pageable));
    }

    /**
     * Endpoint to retrieve a subject by ID.
     *
     * @param id The ID of the subject.
     * @return ResponseEntity containing the subject with the specified ID and
     * HTTP status OK.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.findById(id));
    }

    /**
     * Endpoint to save a new subject.
     *
     * @param subjectDto The SubjectDto object containing data for the new
     * subject.
     * @return ResponseEntity containing the saved subject and HTTP status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody SubjectDto subjectDto) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.save(subjectDto));
    }

    /**
     * Endpoint to retrieve a subject by name.
     *
     * @param name The name of the subject.
     * @return ResponseEntity containing the subject with the specified name and
     * HTTP status OK.
     */
    @GetMapping("/get/name/{name}")
    public ResponseEntity getByName(@PathVariable String name) {
        return new ResponseEntity(subjectService.findByName(name), HttpStatus.OK);
    }

    /**
     * Endpoint to search for subjects by name.
     *
     * @param term The search term.
     * @return ResponseEntity containing the list of subjects matching the
     * search term and HTTP status OK.
     */
    @GetMapping("/search/name/{term}")
    public ResponseEntity search(@PathVariable String term) {
        return new ResponseEntity(subjectService.search(term), HttpStatus.OK);
    }

    /**
     * Endpoint to delete a subject by ID.
     *
     * @param id The ID of the subject to delete.
     * @return ResponseEntity containing a success message and HTTP status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        subjectService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("izbrisan je subject sa id: " + id);
    }

    /**
     * Endpoint to update an existing subject.
     *
     * @param subjectDto The SubjectDto object containing updated data for the
     * subject.
     * @return ResponseEntity containing the updated subject and HTTP status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody SubjectDto subjectDto) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.edit(subjectDto));
    }

}
