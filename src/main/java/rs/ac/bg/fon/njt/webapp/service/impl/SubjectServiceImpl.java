/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.webapp.converter.SubjectMapper;
import rs.ac.bg.fon.njt.webapp.domain.Subject;
import rs.ac.bg.fon.njt.webapp.dto.SubjectDto;
import rs.ac.bg.fon.njt.webapp.dto.SubjectFilterDto;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.SubjectRepository;
import rs.ac.bg.fon.njt.webapp.repository.specifications.SubjectSpecification;
import rs.ac.bg.fon.njt.webapp.service.SubjectService;

/**
 * Service implementation for managing subjects.
 *
 * @author aleks
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectMapper subjectMapper;

    /**
     * Saves a subject.
     *
     * This method saves the provided SubjectDto in the database.
     *
     * @param subjectDto The SubjectDto to be saved.
     * @return The saved SubjectDto.
     * @throws InvalidDataException if the provided dto is null, or if a subject
     * with the same ID or name already exists.
     */
    @Override
    public SubjectDto save(SubjectDto subjectDto) {
        Optional<Subject> optional;
        if (subjectDto.getId() != null) {
            optional = subjectRepository.findById(subjectDto.getId());
            if (optional.isPresent()) {
                throw new InvalidDataException("vec postoji subject sa ovim id!");
            }
        }
        optional = null;
        optional = subjectRepository.findByName(subjectDto.getName());
        if (optional.isPresent()) {
            throw new InvalidDataException("vec postoji sa tim nazivom");
        }
        Subject subject = subjectMapper.subjectDtoToSubject(subjectDto);
//        subject.setId(null);
        return subjectMapper.subjectToSubjectDto(
                subjectRepository.save(subject)
        );
    }

    /**
     * Edits a subject.
     *
     * This method edits the provided SubjectDto in the database.
     *
     * @param subjectDto The SubjectDto to be edited.
     * @return The edited SubjectDto.
     * @throws InvalidDataException if the provided dto is null, or if the
     * subject with the specified ID does not exist, or if a subject with the
     * same name already exists.
     */
    @Override
    public SubjectDto edit(SubjectDto subjectDto) {

        Optional<Subject> optional = subjectRepository.findById(subjectDto.getId());
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji sa tim ID");
        }
        optional = null;
        optional = subjectRepository.findByName(subjectDto.getName());
        if (optional.isPresent() && optional.get().getId() != subjectDto.getId()) {
            throw new InvalidDataException("vec postoji sa tim nazivom");
        }
        Subject subject = subjectMapper.subjectDtoToSubject(subjectDto);
        return subjectMapper.subjectToSubjectDto(
                subjectRepository.save(subject)
        );
    }

    /**
     * Retrieves a page of subjects based on filtering criteria.
     *
     * This method retrieves a page of subjects from the database based on the
     * provided filtering criteria and pagination parameters.
     *
     * @param filterDto The filtering criteria.
     * @param pageable The pagination parameters.
     * @return A page of SubjectDto objects.
     * @throws InvalidDataException if the requested page does not exist.
     */
    @Override
    public Page<SubjectDto> pageFilterPaginate(SubjectFilterDto filterDto, Pageable pageable) {
        Page<Subject> pageSub = subjectRepository.findAll(SubjectSpecification.filter(filterDto), pageable);
        if (pageSub.getTotalPages() <= pageable.getPageNumber() && pageable.getPageNumber() != 0) {
            throw new InvalidDataException("ne postoji strana");
        }
        Page<SubjectDto> dtoPage = pageSub.map(this::convertToDto);
        return dtoPage;
    }

    private SubjectDto convertToDto(Subject subject) {
        return subjectMapper.subjectToSubjectDto(subject);
    }

    /**
     * Retrieves all subjects.
     *
     * This method retrieves all subjects from the database.
     *
     * @return A list of all SubjectDto objects.
     */
    @Override
    public List<SubjectDto> findAll() {
        return subjectRepository.findAll().stream().map(
                dao -> subjectMapper.subjectToSubjectDto(dao)
        ).collect(Collectors.toList());
    }

    /**
     * Retrieves a page of all subjects.
     *
     * This method retrieves a page of all subjects from the database based on
     * the provided pagination parameters.
     *
     * @param pageable The pagination parameters.
     * @return A page of SubjectDto objects.
     * @throws InvalidDataException if the requested page does not exist.
     */
    @Override
    public Page<SubjectDto> findAll(Pageable pageable) {
        Page<Subject> page = subjectRepository.findAll(pageable);
        if (pageable.getPageNumber() >= page.getTotalPages() && pageable.getPageNumber() != 0) {
            throw new InvalidDataException("ne postoji strana");
        }
        Page<SubjectDto> dtoPage = page.map(this::convertToDto);
        return dtoPage;
    }

    /**
     * Searches for subjects by name.
     *
     * This method searches for subjects in the database by name.
     *
     * @param searchName The name to search for.
     * @return A list of SubjectDto objects matching the search criteria.
     */
    @Override
    public List<SubjectDto> search(String searchName) {
        return subjectRepository.findAll(SubjectSpecification.search(searchName)).stream().map(
                dao -> subjectMapper.subjectToSubjectDto(dao)
        ).collect(Collectors.toList());
    }

    /**
     * Retrieves a subject by ID.
     *
     * This method retrieves a subject from the database by its ID.
     *
     * @param id The ID of the subject to retrieve.
     * @return The SubjectDto with the specified ID.
     * @throws InvalidDataException if no subject with the specified ID exists.
     */
    @Override
    public SubjectDto findById(Long id) {
        Optional<Subject> optional = subjectRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji sa tim id");
        } else {
            return subjectMapper.subjectToSubjectDto(optional.get());
        }
    }

    /**
     * Retrieves a subject by name.
     *
     * This method retrieves a subject from the database by its name.
     *
     * @param name The name of the subject to retrieve.
     * @return The SubjectDto with the specified name.
     * @throws InvalidDataException if no subject with the specified name
     * exists.
     */
    @Override
    public SubjectDto findByName(String name) {
        Optional<Subject> optional = subjectRepository.findByName(name);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji sa tim imenom");
        } else {
            return subjectMapper.subjectToSubjectDto(optional.get());
        }
    }

    /**
     * Deletes a subject by ID.
     *
     * This method deletes a subject from the database by its ID.
     *
     * @param id The ID of the subject to delete.
     * @throws InvalidDataException if no subject with the specified ID exists.
     */
    @Override
    public void delete(Long id) {
        Optional<Subject> optional = subjectRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji sa tim id");
        } else {
            subjectRepository.delete(optional.get());
        }
    }

}
