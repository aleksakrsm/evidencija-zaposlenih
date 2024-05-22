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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.njt.webapp.controller.customRequestBodyAnotations.JsonArgHistoryItemsList;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeAcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.HistoryItemIdDto;
import rs.ac.bg.fon.njt.webapp.service.EmployeeAcademicTitleService;

/**
 * Controller for handling operations related to employee academic titles. This
 * controller provides endpoints for saving, updating, deleting, and retrieving
 * employee academic titles. Cross-Origin Resource Sharing (CORS) is enabled for
 * all endpoints. Access to some endpoints requires ADMIN role authorization.
 *
 * @author aleks
 */
@RestController
@RequestMapping("/historyItem")
@CrossOrigin("*")
public class EmployeeAcademicTitleController {

    @Autowired
    private EmployeeAcademicTitleService historyItemService;

    /**
     * Endpoint to save a new employee academic title.
     *
     * @param historyItemDto The EmployeeAcademicTitleDto object containing the
     * data of the academic title to be saved.
     * @return ResponseEntity containing the saved academic title and HTTP
     * status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody EmployeeAcademicTitleDto historyItemDto) {
        return ResponseEntity.status(HttpStatus.OK).body(historyItemService.save(historyItemDto));
    }

    /**
     * Endpoint to save multiple employee academic titles.
     *
     * @param historyItemsDto List of EmployeeAcademicTitleDto objects
     * containing the data of academic titles to be saved.
     * @return ResponseEntity containing the saved academic titles and HTTP
     * status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveAll")
    public ResponseEntity save(@Valid @RequestBody List<EmployeeAcademicTitleDto> historyItemsDto) {
        return ResponseEntity.status(HttpStatus.OK).body(historyItemService.saveAll(historyItemsDto));
    }

    /**
     * Endpoint to save changes to employee academic titles.
     *
     * @param toSave List of EmployeeAcademicTitleDto objects to be saved.
     * @param toDelete List of EmployeeAcademicTitleDto objects to be deleted.
     * @return ResponseEntity containing the result of saving changes and HTTP
     * status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveChanges")
    public ResponseEntity saveChanges(@Valid @JsonArgHistoryItemsList(path = "/toSave") List<EmployeeAcademicTitleDto> toSave, @Valid @JsonArgHistoryItemsList(path = "/toDelete") List<EmployeeAcademicTitleDto> toDelete) {
        return ResponseEntity.status(HttpStatus.OK).body(historyItemService.saveChanges(toSave, toDelete));
    }

    /**
     * Endpoint to update an existing employee academic title.
     *
     * @param dto The EmployeeAcademicTitleDto object containing the updated
     * data for the academic title.
     * @return ResponseEntity containing the updated academic title and HTTP
     * status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody EmployeeAcademicTitleDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(historyItemService.edit(dto));
    }

    /**
     * Endpoint to retrieve an employee academic title by its ID.
     *
     * @param id The ID of the academic title to retrieve.
     * @return ResponseEntity containing the academic title with the specified
     * ID and HTTP status OK.
     */
    @PutMapping("/getById")
    public ResponseEntity getById(@Valid @RequestBody HistoryItemIdDto id) {
        return ResponseEntity.status(HttpStatus.OK).body(historyItemService.findById(id));
    }

    /**
     * Endpoint to retrieve employee academic titles by employee ID.
     *
     * @param id The ID of the employee.
     * @return ResponseEntity containing the academic titles associated with the
     * specified employee and HTTP status OK.
     */
    @GetMapping("/get/employee/{id}")
    public ResponseEntity getByEmployee(@Valid @PathVariable Long id) {
        return new ResponseEntity(historyItemService.findByEmployee(id), HttpStatus.OK);
    }

    /**
     * Endpoint to delete an employee academic title by its ID.
     *
     * @param id The ID of the academic title to delete.
     * @return ResponseEntity containing a success message and HTTP status OK.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity delete(@Valid @RequestBody HistoryItemIdDto id) {
        historyItemService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).
                body("izbrisan je titula: " + id.getAcademicTitle() + " zaposlenog: " + id.getEmployee().getFirstname());
    }

}
