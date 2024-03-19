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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeSubjectDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeSubjectIdDto;
import rs.ac.bg.fon.njt.webapp.service.EmployeeSubjectService;


/**
 *
 * @author aleks
 */
@RestController
@RequestMapping("/employeeSubject")
public class EmployeeSubjectController {
    @Autowired
    private EmployeeSubjectService employeeSubjectService;
    
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody EmployeeSubjectDto employeeSubjectDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeSubjectService.save(employeeSubjectDto));
    }
    
//    @PostMapping("/saveAll")
//    public ResponseEntity save(@Valid @RequestBody List<EmployeeSubjectDto> employeeSubjectsDto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(employeeSubjectService.saveAll(employeeSubjectsDto));
//    }
    
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody EmployeeSubjectDto dto) {
            return ResponseEntity.status(HttpStatus.OK).body(employeeSubjectService.edit(dto));
    }

    @GetMapping("/get/employee/{id}")
    public ResponseEntity getByEmployee(@PathVariable Long employeeId) {
        return new ResponseEntity(employeeSubjectService.findByEmployee(employeeId), HttpStatus.OK);
    }
    
    @GetMapping("/get/subject/{id}")
    public ResponseEntity getBySubject(@PathVariable Long subjectId) {
        return new ResponseEntity(employeeSubjectService.findBySubject(subjectId), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity delete(@Valid @RequestBody EmployeeSubjectDto dto) {
            employeeSubjectService.delete(dto);
            return ResponseEntity.status(HttpStatus.OK).
                    body("izbrisan je EmployeeSubject predmet: " + dto.getId().getSubject().getName() + " zaposlenog: " + dto.getId().getEmployee().getFirstname());
    }
    
    
    
    
}
