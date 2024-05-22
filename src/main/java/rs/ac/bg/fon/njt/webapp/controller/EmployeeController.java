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
 * Controller for handling operations related to employees. This controller
 * provides endpoints for retrieving, filtering, paginating, searching,
 * creating, updating, and deleting employees. Cross-Origin Resource Sharing
 * (CORS) is enabled for all endpoints. Access to some endpoints requires ADMIN
 * role authorization.
 *
 * @author aleks
 */
@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Endpoint to retrieve all employees.
     *
     * @return ResponseEntity containing all employees and HTTP status OK.
     */
    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll());
    }

    /**
     * Endpoint to filter employees based on specified criteria.
     *
     * @param filterDto The EmployeeFilterDto object containing filter criteria.
     * @return ResponseEntity containing filtered employees and HTTP status OK.
     */
    @PostMapping("/filter")
    public ResponseEntity filterEmployees(@RequestBody EmployeeFilterDto filterDto) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.filter(filterDto));
    }

    /**
     * Endpoint to filter and paginate employees based on specified criteria.
     *
     * @param filterDto The EmployeeFilterDto object containing filter criteria.
     * @param page Page number.
     * @param pageSize Number of items per page.
     * @param sortBy Field to sort by.
     * @param sortDirection Sort direction (asc or desc).
     * @return ResponseEntity containing filtered and paginated employees and
     * HTTP status OK.
     */
    @PostMapping("/filterPaginate")
    public ResponseEntity filterPaginate(
            @RequestBody EmployeeFilterDto filterDto,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "firstname") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {
        Pageable pageable;
        if ("asc".equals(sortDirection)) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.filterPaginate(filterDto, pageable));
    }

    /**
     * Endpoint to filter, paginate, and retrieve employees based on specified
     * criteria.
     *
     * @param filterDto The EmployeeFilterDto object containing filter criteria.
     * @param page Page number.
     * @param pageSize Number of items per page.
     * @param sortBy Field to sort by.
     * @param sortDirection Sort direction (asc or desc).
     * @return ResponseEntity containing filtered, paginated, and retrieved
     * employees and HTTP status OK.
     */
    @PostMapping("/pageFilterPaginate")
    public ResponseEntity pageFilterPaginate(
            @RequestBody EmployeeFilterDto filterDto,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "firstname") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {
        Pageable pageable;
        if ("asc".equals(sortDirection)) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.pageFilterPaginate(filterDto, pageable));
    }

    /**
     * Endpoint to retrieve employees based on pagination.
     *
     * @param page Page number.
     * @param pageSize Number of items per page.
     * @param sortBy Field to sort by.
     * @param sortDirection Sort direction (asc or desc).
     * @return ResponseEntity containing paginated employees and HTTP status OK.
     */
    @GetMapping("/page")
    public ResponseEntity pageEmployees(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "firstname") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {
        Pageable pageable;
        if ("asc".equals(sortDirection)) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll(pageable));
    }

    /**
     * Endpoint to search for employees by a given term.
     *
     * @param term The search term.
     * @return ResponseEntity containing the search results and HTTP status OK.
     */
    @GetMapping("/search/{term}")
    public ResponseEntity getById(@PathVariable(name = "term") String term) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.search(term));
    }

    /**
     * Endpoint to retrieve an employee by ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return ResponseEntity containing the employee with the specified ID and
     * HTTP status OK.
     */
    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findById(id));
    }

    /**
     * Endpoint to retrieve employees by department DTO.
     *
     * @param departmentDto The DepartmentDto object representing the
     * department.
     * @return ResponseEntity containing employees associated with the specified
     * department and HTTP status OK.
     */
    @PostMapping("/getByDepartmentDto")
    public ResponseEntity getByDepartmentDto(@Valid @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findByDepartmentDto(departmentDto));
    }

    /**
     * Endpoint to retrieve employees by department ID.
     *
     * @param departmentId The ID of the department.
     * @return ResponseEntity containing employees associated with the specified
     * department ID and HTTP status OK.
     */
    @GetMapping("/getByDepartmentId/{departmentId}")
    public ResponseEntity getByDepartmentId(@PathVariable(name = "departmentId") Long departmentId) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findByDepartmentId(departmentId));
    }

    /**
     * Endpoint to save a new employee.
     *
     * @param employeeDto The EmployeeDto object containing data for the new
     * employee.
     * @return ResponseEntity containing the saved employee and HTTP status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.save(employeeDto));
    }

    /**
     * Endpoint to delete an employee by ID.
     *
     * @param id The ID of the employee to delete.
     * @return ResponseEntity containing a success message and HTTP status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        //proveriti izuzetke sa restricted
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("izbrisan je zaposleni sa id: " + id);
    }

    /**
     * Endpoint to logically delete an employee by setting their status to
     * INACTIVE.
     *
     * @param id The ID of the employee to logically delete.
     * @return ResponseEntity containing the updated employee status and HTTP
     * status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleteLogically/{id}")
    public ResponseEntity deleteLogically(@PathVariable Long id) {
        EmployeeDto employeeDto = employeeService.findById(id);
        employeeDto.setStatus(Status.INACTIVE);
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.edit(employeeDto));
    }

    /**
     * Endpoint to update an existing employee.
     *
     * @param dto The EmployeeDto object containing updated data for the
     * employee.
     * @return ResponseEntity containing the updated employee and HTTP status
     * OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody EmployeeDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.edit(dto));
    }

    /**
     * Endpoint to count employees by academic title and department.
     *
     * @param academicTitleId The ID of the academic title.
     * @param departmentId The ID of the department.
     * @return ResponseEntity containing the count of employees based on
     * academic title and department and HTTP status OK.
     */
    @GetMapping("/count")
    public ResponseEntity countByAcademicTitleAndDepartment(
            @RequestParam(name = "academicTitleId", defaultValue = "-1") long academicTitleId,
            @RequestParam(name = "departmentId", defaultValue = "-1") long departmentId) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.countByAcademicTitleAndDepartment(academicTitleId, departmentId));
    }

}
