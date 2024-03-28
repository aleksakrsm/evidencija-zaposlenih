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
 *
 * @author aleks
 */
@RestController
@RequestMapping("/educationTitle")
@CrossOrigin("*")
public class EducationTitleController {
    
    @Autowired
    private EducationTitleService educationTitleService;
    
    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(educationTitleService.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(educationTitleService.findById(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody EducationTitleDto educationTitleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(educationTitleService.save(educationTitleDto));
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity getByName(@PathVariable String name) {
        return new ResponseEntity(educationTitleService.findByName(name), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
            educationTitleService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("izbrisana je titula sa id: " + id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody EducationTitleDto dto) {
            return ResponseEntity.status(HttpStatus.OK).body(educationTitleService.edit(dto));
    }
    
    
}
