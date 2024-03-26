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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeAcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.HistoryItemIdDto;
import rs.ac.bg.fon.njt.webapp.service.EmployeeAcademicTitleService;

/**
 *
 * @author aleks
 */
@RestController
@RequestMapping("/historyItem")
@CrossOrigin("*")
public class EmployeeAcademicTitleController {
    @Autowired
    private EmployeeAcademicTitleService historyItemService;
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody EmployeeAcademicTitleDto historyItemDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(historyItemService.save(historyItemDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveAll")
    public ResponseEntity save(@Valid @RequestBody List<EmployeeAcademicTitleDto> historyItemsDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(historyItemService.saveAll(historyItemsDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody EmployeeAcademicTitleDto dto) {
            return ResponseEntity.status(HttpStatus.OK).body(historyItemService.edit(dto));
    }

    @PutMapping("/getById")
    public ResponseEntity getById(@Valid @RequestBody HistoryItemIdDto id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(historyItemService.findById(id));
    }
    

    @PostMapping("/get/employee/{id}")
    public ResponseEntity getByEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity(historyItemService.findByEmployee(employeeDto), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity delete(@Valid @RequestBody HistoryItemIdDto id) {
            historyItemService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).
                    body("izbrisan je titula: " + id.getAcademicTitle()+ " zaposlenog: " + id.getEmployee().getFirstname());
    }
    
}
