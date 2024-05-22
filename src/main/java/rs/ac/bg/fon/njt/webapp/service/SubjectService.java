/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;
import rs.ac.bg.fon.njt.webapp.dto.SubjectDto;
import rs.ac.bg.fon.njt.webapp.dto.SubjectFilterDto;

/**
 * Service interface for managing subjects. Provides methods for saving,
 * editing, finding, filtering, and deleting subjects.
 *
 * @see SubjectDto
 * @see SubjectFilterDto
 *
 * This interface defines the contract for subject-related operations.
 * Implementations are responsible for the actual logic of these operations.
 *
 * @author aleks
 */
public interface SubjectService {

    /**
     * Saves a new subject.
     *
     * @param subjectDto The DTO of the subject to save.
     * @return The saved SubjectDto.
     */
    SubjectDto save(SubjectDto subjectDto);

    /**
     * Edits an existing subject.
     *
     * @param subjectDto The DTO of the subject to edit.
     * @return The edited SubjectDto.
     */
    SubjectDto edit(SubjectDto subjectDto);

    /**
     * Finds all subjects.
     *
     * @return A list of SubjectDto.
     */
    List<SubjectDto> findAll();

    /**
     * Paginates and filters subjects based on the given filter criteria and
     * pagination information.
     *
     * @param filterDto The filter criteria.
     * @param pageable The pagination information.
     * @return A Page of SubjectDto matching the filter criteria and pagination
     * information.
     */
    Page<SubjectDto> pageFilterPaginate(SubjectFilterDto filterDto, Pageable pageable);

    /**
     * Finds all subjects with pagination.
     *
     * @param pageable The pagination information.
     * @return A Page of SubjectDto.
     */
    Page<SubjectDto> findAll(Pageable pageable);

    /**
     * Searches subjects based on the given search name.
     *
     * @param searchName The name to search.
     * @return A list of SubjectDto matching the search name.
     */
    List<SubjectDto> search(String searchName);

    /**
     * Finds a subject by its ID.
     *
     * @param id The ID of the subject.
     * @return The SubjectDto with the given ID.
     */
    SubjectDto findById(Long id);

    /**
     * Finds a subject by its name.
     *
     * @param name The name of the subject.
     * @return The SubjectDto with the given name.
     */
    SubjectDto findByName(String name);

    /**
     * Deletes a subject by its ID.
     *
     * @param id The ID of the subject to delete.
     */
    void delete(Long id);

}
