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
import rs.ac.bg.fon.njt.webapp.repository.AcademicTitleRepository;
import rs.ac.bg.fon.njt.webapp.service.AcademicTitleService;
import rs.ac.bg.fon.njt.webapp.converter.AcademicTitleMapper;
import rs.ac.bg.fon.njt.webapp.domain.AcademicTitle;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;

/**
 * Implementation of the {@link AcademicTitleService} interface. Provides
 * methods for managing academic titles, including saving, editing, finding, and
 * deleting academic titles.
 *
 * This class uses {@link AcademicTitleRepository} to interact with the database
 * and {@link AcademicTitleMapper} to map between DTOs and entities.
 *
 * @see AcademicTitleService
 * @see AcademicTitleRepository
 * @see AcademicTitleMapper
 * @see AcademicTitleDto
 * @see AcademicTitle
 * @see InvalidDataException 
 * 
 * This implementation ensures that:
 * <ul>
 * <li>An academic title cannot be saved if another title with the same ID or
 * name already exists.
 * <li>An academic title cannot be edited if it does not exist or if another
 * title with the same name already exists.
 * <li>An academic title cannot be deleted if it does not exist.
 * </ul>
 *
 * @author aleks
 */
@Service
public class AcademicTitleServiceImpl implements AcademicTitleService {

    @Autowired
    private AcademicTitleRepository academicTitleRepository;

    @Autowired
    private AcademicTitleMapper academicTitleMapper;

    /**
     * Saves a new academic title.
     *
     * @param academicTitleDto The DTO of the academic title to save.
     * @return The saved AcademicTitleDto.
     * @throws InvalidDataException if an academic title with the same ID or
     * name already exists.
     */
    @Override
    public AcademicTitleDto save(AcademicTitleDto academicTitleDto) {
        Optional<AcademicTitle> optional;
        if (academicTitleDto.getId() != null) {
            optional = academicTitleRepository.findById(academicTitleDto.getId());
            if (optional.isPresent()) {
                throw new InvalidDataException("vec postoji titula sa ovim id!");
            }
        }

        optional = null;
        optional = academicTitleRepository.findByName(academicTitleDto.getName());
        if (optional.isPresent()) {
            throw new InvalidDataException("vec postoji sa tim nazivom");
        }
        AcademicTitle academicTitle = academicTitleMapper.academicTitleDtoToAcademicTitle(academicTitleDto);
//        academicTitle.setId(null);
        return academicTitleMapper.academicTitleToAcademicTitleDto(
                academicTitleRepository.save(academicTitle)
        );
    }

    /**
     * Edits an existing academic title.
     *
     * @param academicTitleDto The DTO of the academic title to edit.
     * @return The edited AcademicTitleDto.
     * @throws InvalidDataException if the academic title does not exist or if
     * an academic title with the same name already exists.
     */
    @Override
    public AcademicTitleDto edit(AcademicTitleDto academicTitleDto) {
        Optional<AcademicTitle> optional = academicTitleRepository.findById(academicTitleDto.getId());
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji sa tim ID");
        }
        optional = null;
        optional = academicTitleRepository.findByName(academicTitleDto.getName());
        if (optional.isPresent()) {
            throw new InvalidDataException("vec postoji sa tim nazivom");
        }
        AcademicTitle academicTitle = academicTitleMapper.academicTitleDtoToAcademicTitle(academicTitleDto);
        return academicTitleMapper.academicTitleToAcademicTitleDto(
                academicTitleRepository.save(academicTitle)
        );
    }

    /**
     * Finds all academic titles.
     *
     * @return A list of AcademicTitleDto.
     */
    @Override
    public List<AcademicTitleDto> findAll() {
        return academicTitleRepository.findAll().stream().map(
                dao -> academicTitleMapper.academicTitleToAcademicTitleDto(dao)
        ).collect(Collectors.toList());
    }

    /**
     * Finds an academic title by its ID.
     *
     * @param id The ID of the academic title.
     * @return The AcademicTitleDto with the given ID.
     * @throws InvalidDataException if no academic title exists with the given
     * ID.
     */
    @Override
    public AcademicTitleDto findById(Long id) {
        Optional<AcademicTitle> optional = academicTitleRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji sa tim id");
        } else {
            return academicTitleMapper.academicTitleToAcademicTitleDto(optional.get());
        }
    }

    /**
     * Finds an academic title by its name.
     *
     * @param name The name of the academic title.
     * @return The AcademicTitleDto with the given name.
     * @throws InvalidDataException if no academic title exists with the given
     * name.
     */
    @Override
    public AcademicTitleDto findByName(String name) {
        Optional<AcademicTitle> optional = academicTitleRepository.findByName(name);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji sa tim imenom");
        } else {
            return academicTitleMapper.academicTitleToAcademicTitleDto(optional.get());
        }
    }

    /**
     * Deletes an academic title by its ID.
     *
     * @param id The ID of the academic title to delete.
     * @throws InvalidDataException if no academic title exists with the given
     * ID.
     */
    @Override
    public void delete(Long id) {
        Optional<AcademicTitle> optional = academicTitleRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji sa tim id");
        } else {
            academicTitleRepository.delete(optional.get());
        }
    }

}
