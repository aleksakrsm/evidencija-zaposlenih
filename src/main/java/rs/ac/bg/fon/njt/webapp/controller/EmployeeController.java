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
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeFilterDto;
import rs.ac.bg.fon.njt.webapp.service.EmployeeService;

/**
 *
 * @author aleks
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll());
    }
    @PostMapping("/status")
    public ResponseEntity status(@RequestBody Status status) {
        System.out.println("-------");
        System.out.println(status);
        System.out.println("-------");
        //posaljem samo kao json "INACTIVE"
        System.out.println("");
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }
    @PostMapping("/filter")
    public ResponseEntity filterEmployees(@RequestBody EmployeeFilterDto filterDto) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.filter(filterDto));
    }

    @GetMapping("/search/{term}")
    public ResponseEntity getById(@PathVariable(name = "term") String term) {
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.search(term));
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.findById(id));
    }

    @PostMapping("/getByDepartmentDto")
    public ResponseEntity getByDepartmentDto(@Valid @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.findByDepartmentDto(departmentDto));
    }

    @GetMapping("/getByDepartmentId/{departmentId}")
    public ResponseEntity getByDepartmentId(@PathVariable(name = "departmentId") Long departmentId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.findByDepartmentId(departmentId));
    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employeeDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        //proveriti izuzetke sa restricted
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("izbrisan je zaposleni sa id: " + id);
    }

    @PutMapping("/delete/logically/{id}")
    public ResponseEntity deleteLogically(@PathVariable Long id) {
        EmployeeDto employeeDto = employeeService.findById(id);
        employeeDto.setStatus(Status.INACTIVE);
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.edit(employeeDto));
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody EmployeeDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.edit(dto));
    }

}
