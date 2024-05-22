/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.webapp.converter.EducationTitleMapper;
import rs.ac.bg.fon.njt.webapp.domain.EducationTitle;
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.EducationTitleRepository;
import rs.ac.bg.fon.njt.webapp.service.EducationTitleService;

/**
 * Service implementation for managing education titles. Provides methods for
 * saving, editing, finding, and deleting education titles.
 *
 * This class interacts with the database through
 * {@link EducationTitleRepository} and utilizes {@link EducationTitleMapper}
 * for DTO-to-entity mapping.
 *
 * @see EducationTitleService
 * @see EducationTitleRepository
 * @see EducationTitleMapper
 * @see EducationTitleDto
 * @see EducationTitle
 * @see InvalidDataException
 *
 * This implementation ensures that:
 * <ul>
 * <li>An education title cannot be saved if another title with the same ID or
 * name already exists.
 * <li>An education title cannot be edited if it does not exist or if another
 * title with the same name already exists.
 * <li>An education title cannot be deleted if it does not exist.
 * </ul>
 * @author aleks
 */
@Service
public class EducationTitleServiceImpl implements EducationTitleService {

    @Autowired
    private EducationTitleRepository educationTitleRepository;

    @Autowired
    private EducationTitleMapper educationTitleMapper;

    /**
     * Saves a new education title.
     *
     * @param educationTitleDto The DTO of the education title to save.
     * @return The saved EducationTitleDto.
     * @throws InvalidDataException if an education title with the same ID or
     * name already exists.
     */
    @Override
    public EducationTitleDto save(EducationTitleDto educationTitleDto) {
        Optional<EducationTitle> optional;
        if (educationTitleDto.getId() != null) {
            optional = educationTitleRepository.findById(educationTitleDto.getId());
            if (optional.isPresent()) {
                throw new InvalidDataException("vec postoji titula sa ovim id!");
            }
        }

        optional = null;
        optional = educationTitleRepository.findByName(educationTitleDto.getName());
        if (optional.isPresent()) {
            throw new InvalidDataException("vec postoji EducationTitle sa tim nazivom");
        }
        EducationTitle educationTitle = educationTitleMapper.educationTitleDtoToEducationTitle(educationTitleDto);
//        educationTitle.setId(null);
        return educationTitleMapper.educationTitleToEducationTitleDto(
                educationTitleRepository.save(educationTitle)
        );
    }

    /**
     * Edits an existing education title.
     *
     * @param educationTitleDto The DTO of the education title to edit.
     * @return The edited EducationTitleDto.
     * @throws InvalidDataException if the education title does not exist or if
     * another title with the same name already exists.
     */
    @Override
    public EducationTitleDto edit(EducationTitleDto educationTitleDto) {
        Optional<EducationTitle> optional = educationTitleRepository.findById(educationTitleDto.getId());
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji educationTitle sa tim ID");
        }
        optional = null;
        optional = educationTitleRepository.findByName(educationTitleDto.getName());
        if (optional.isPresent()) {
            throw new InvalidDataException("vec postoji educationTitle sa tim nazivom");
        }
        EducationTitle educationTitle = educationTitleMapper.educationTitleDtoToEducationTitle(educationTitleDto);
        return educationTitleMapper.educationTitleToEducationTitleDto(
                educationTitleRepository.save(educationTitle)
        );
    }

    /**
     * Finds all education titles.
     *
     * @return A list of EducationTitleDto.
     */
    @Override
    public List<EducationTitleDto> findAll() {
        return educationTitleRepository.findAll().stream().map(
                dao -> educationTitleMapper.educationTitleToEducationTitleDto(dao)
        ).collect(Collectors.toList());
    }

    /**
     * Finds an education title by its ID.
     *
     * @param id The ID of the education title.
     * @return The EducationTitleDto with the given ID.
     * @throws InvalidDataException if no education title exists with the given
     * ID.
     */
    @Override
    public EducationTitleDto findById(Long id) {
        Optional<EducationTitle> optional = educationTitleRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji EducationTitle sa tim id");
        } else {
            return educationTitleMapper.educationTitleToEducationTitleDto(optional.get());
        }
    }

    /**
     * Finds an education title by its name.
     *
     * @param name The name of the education title.
     * @return The EducationTitleDto with the given name.
     * @throws InvalidDataException if no education title exists with the given
     * name.
     */
    @Override
    public EducationTitleDto findByName(String name) {
        Optional<EducationTitle> optional = educationTitleRepository.findByName(name);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji EducationTitle sa tim imenom");
        } else {
            return educationTitleMapper.educationTitleToEducationTitleDto(optional.get());
        }
    }

    /**
     * Deletes an education title by its ID.
     *
     * @param id The ID of the education title to delete.
     * @throws InvalidDataException if no education title exists with the given
     * ID.
     */
    @Override
    public void delete(Long id) {
        Optional<EducationTitle> optional = educationTitleRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji EducationTitle sa tim id");
        } else {
            educationTitleRepository.delete(optional.get());
        }
    }

}
