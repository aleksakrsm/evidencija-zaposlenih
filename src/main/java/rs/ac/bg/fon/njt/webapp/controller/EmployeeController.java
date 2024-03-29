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
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeFilterDto;
import rs.ac.bg.fon.njt.webapp.service.EmployeeService;

/**
 *
 * @author aleks
 */
@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll());
    }
    @PostMapping("/filter")
    public ResponseEntity filterEmployees(@RequestBody EmployeeFilterDto filterDto) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.filter(filterDto));
    }
    @PostMapping("/filterPaginate")
    public ResponseEntity filterPaginate(
            @RequestBody EmployeeFilterDto filterDto,
            @RequestParam(name = "page",defaultValue = "0")int page,
            @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "firstname")String sortBy,
            @RequestParam(name = "sortDirection",defaultValue = "asc")String sortDirection) {
        Pageable pageable;
        if("asc".equals(sortDirection)){
            pageable = PageRequest.of(page, pageSize,Sort.by(sortBy).ascending());
        }else{
            pageable = PageRequest.of(page, pageSize,Sort.by(sortBy).descending());
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.filterPaginate(filterDto,pageable));
    }
    @PostMapping("/pageFilterPaginate")
    public ResponseEntity pageFilterPaginate(
            @RequestBody EmployeeFilterDto filterDto,
            @RequestParam(name = "page",defaultValue = "0")int page,
            @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "firstname")String sortBy,
            @RequestParam(name = "sortDirection",defaultValue = "asc")String sortDirection) {
        Pageable pageable;
        if("asc".equals(sortDirection)){
            pageable = PageRequest.of(page, pageSize,Sort.by(sortBy).ascending());
        }else{
            pageable = PageRequest.of(page, pageSize,Sort.by(sortBy).descending());
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.pageFilterPaginate(filterDto,pageable));
    }
    
    @GetMapping("/page")
    public ResponseEntity pageEmployees(
            @RequestParam(name = "page",defaultValue = "0")int page,
            @RequestParam(name = "pageSize",defaultValue = "10")int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "firstname")String sortBy,
            @RequestParam(name = "sortDirection",defaultValue = "asc")String sortDirection) {
        Pageable pageable;
        if("asc".equals(sortDirection)){
            pageable = PageRequest.of(page, pageSize,Sort.by(sortBy).ascending());
        }else{
            pageable = PageRequest.of(page, pageSize,Sort.by(sortBy).descending());
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll(pageable));
    }
    
    @GetMapping("/search/{term}")
    public ResponseEntity getById(@PathVariable(name = "term") String term) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.search(term));
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findById(id));
    }

    @PostMapping("/getByDepartmentDto")
    public ResponseEntity getByDepartmentDto(@Valid @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findByDepartmentDto(departmentDto));
    }

    @GetMapping("/getByDepartmentId/{departmentId}")
    public ResponseEntity getByDepartmentId(@PathVariable(name = "departmentId") Long departmentId) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findByDepartmentId(departmentId));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.save(employeeDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        //proveriti izuzetke sa restricted
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("izbrisan je zaposleni sa id: " + id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/delete/logically/{id}")
    public ResponseEntity deleteLogically(@PathVariable Long id) {
        EmployeeDto employeeDto = employeeService.findById(id);
        employeeDto.setStatus(Status.INACTIVE);
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.edit(employeeDto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody EmployeeDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.edit(dto));
    }

}
