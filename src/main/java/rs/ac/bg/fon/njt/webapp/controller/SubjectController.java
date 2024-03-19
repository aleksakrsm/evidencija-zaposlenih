/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.njt.webapp.dto.SubjectDto;
import rs.ac.bg.fon.njt.webapp.service.SubjectService;

/**
 *
 * @author aleks
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(subjectService.findById(id));
    }
    
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody SubjectDto subjectDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.save(subjectDto));
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity getByName(@PathVariable String name) {
        return new ResponseEntity(subjectService.findByName(name), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
            subjectService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("izbrisan je subject sa id: " + id);
    }
    
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody SubjectDto subjectDto) {
            return ResponseEntity.status(HttpStatus.OK).body(subjectService.edit(subjectDto));
    }

}
