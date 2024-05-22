/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service;

import java.util.List;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;

/**
 * Service interface for managing academic titles. Provides methods for saving,
 * editing, finding, and deleting academic titles.
 *
 * @see AcademicTitleDto
 *
 * @author aleks
 */
public interface AcademicTitleService {

    /**
     * Saves a new academic title.
     *
     * @param academicTitleDto The DTO of the academic title to save.
     * @return The saved AcademicTitleDto.
     */
    AcademicTitleDto save(AcademicTitleDto academicTitleDto);

    /**
     * Edits an existing academic title.
     *
     * @param academicTitleDto The DTO of the academic title to edit.
     * @return The edited AcademicTitleDto.
     */
    AcademicTitleDto edit(AcademicTitleDto academicTitleDto);

    /**
     * Finds all academic titles.
     *
     * @return A list of AcademicTitleDto.
     */
    List<AcademicTitleDto> findAll();

    /**
     * Finds an academic title by its ID.
     *
     * @param id The ID of the academic title.
     * @return The AcademicTitleDto with the given ID.
     */
    AcademicTitleDto findById(Long id);

    /**
     * Finds an academic title by its name.
     *
     * @param name The name of the academic title.
     * @return The AcademicTitleDto with the given name.
     */
    AcademicTitleDto findByName(String name);

    /**
     * Deletes an academic title by its ID.
     *
     * @param id The ID of the academic title to delete.
     */
    void delete(Long id);

}
