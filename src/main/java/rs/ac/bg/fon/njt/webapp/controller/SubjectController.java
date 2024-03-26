/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.controller;

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
import rs.ac.bg.fon.njt.webapp.service.SubjectService;

/**
 *
 * @author aleks
 */
@RestController
@RequestMapping("/subject")
@CrossOrigin("*")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.findAll());
    }
    
    @GetMapping("/page")
    public ResponseEntity pageEmployees(
            @RequestParam(name = "page",defaultValue = "0")int page,
            @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "name")String sortBy,
            @RequestParam(name = "sortDirection",defaultValue = "asc")String sortDirection) {
        Pageable pageable;
        if("asc".equals(sortDirection)){
            pageable = PageRequest.of(page, pageSize,Sort.by(sortBy).ascending());
        }else{
            pageable = PageRequest.of(page, pageSize,Sort.by(sortBy).descending());
        }
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.findAll(pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(subjectService.findById(id));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody SubjectDto subjectDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.save(subjectDto));
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity getByName(@PathVariable String name) {
        return new ResponseEntity(subjectService.findByName(name), HttpStatus.OK);
    }
    
    @GetMapping("/search/name/{term}")
    public ResponseEntity search(@PathVariable String term) {
        return new ResponseEntity(subjectService.search(term), HttpStatus.OK);
    }
    
    @GetMapping("/filter/studiesType/{studiesType}")
    public ResponseEntity filter(@Valid @PathVariable StudiesType studiesType) {
        return new ResponseEntity(subjectService.filter(studiesType), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
            subjectService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("izbrisan je subject sa id: " + id);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody SubjectDto subjectDto) {
            return ResponseEntity.status(HttpStatus.OK).body(subjectService.edit(subjectDto));
    }

}
