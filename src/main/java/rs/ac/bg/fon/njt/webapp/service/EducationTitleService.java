/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service;

import java.util.List;
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;

/**
 * Service interface for managing education titles. Provides methods for saving,
 * editing, finding, and deleting education titles.
 *
 * @see EducationTitleDto
 *
 * This interface defines the contract for education title-related operations.
 *
 * @author aleks
 */
public interface EducationTitleService {

    /**
     * Saves a new education title.
     *
     * @param educationTitleDto The DTO of the education title to save.
     * @return The saved EducationTitleDto.
     */
    EducationTitleDto save(EducationTitleDto educationTitleDto);

    /**
     * Edits an existing education title.
     *
     * @param educationTitleDto The DTO of the education title to edit.
     * @return The edited EducationTitleDto.
     */
    EducationTitleDto edit(EducationTitleDto educationTitleDto);

    /**
     * Finds all education titles.
     *
     * @return A list of EducationTitleDto.
     */
    List<EducationTitleDto> findAll();

    /**
     * Finds an education title by its ID.
     *
     * @param id The ID of the education title.
     * @return The EducationTitleDto with the given ID.
     */
    EducationTitleDto findById(Long id);

    /**
     * Finds an education title by its name.
     *
     * @param name The name of the education title.
     * @return The EducationTitleDto with the given name.
     */
    EducationTitleDto findByName(String name);

    /**
     * Deletes an education title by its ID.
     *
     * @param id The ID of the education title to delete.
     */
    void delete(Long id);

}
