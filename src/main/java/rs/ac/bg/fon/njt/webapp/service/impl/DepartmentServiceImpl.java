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
import rs.ac.bg.fon.njt.webapp.converter.DepartmentMapper;
import rs.ac.bg.fon.njt.webapp.domain.Department;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.DepartmentRepository;
import rs.ac.bg.fon.njt.webapp.service.DepartmentService;

/**
 * Implementation of the {@link DepartmentService} interface. Provides methods
 * for managing departments, including saving, editing, finding, and deleting
 * departments.
 *
 * This class uses {@link DepartmentRepository} to interact with the database
 * and {@link DepartmentMapper} to map between DTOs and entities.
 *
 * @see DepartmentService
 * @see DepartmentRepository
 * @see DepartmentMapper
 * @see DepartmentDto
 * @see Department
 * @see InvalidDataException
 *
 * This implementation ensures that:
 * <ul>
 * <li>A department cannot be saved if another department with the same ID,
 * name, or short name already exists.
 * <li>A department cannot be edited if it does not exist or if another
 * department with the same name or short name already exists.
 * <li>A department cannot be deleted if it does not exist.
 * </ul>
 * @author aleks
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * Saves a new department.
     *
     * @param departmentDto The DTO of the department to save.
     * @return The saved DepartmentDto.
     * @throws InvalidDataException if a department with the same ID, name, or
     * short name already exists.
     */
    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        Optional<Department> optional;
        if (departmentDto.getId() != null) {
            optional = departmentRepository.findById(departmentDto.getId());
            if (optional.isPresent()) {
                throw new InvalidDataException("vec postoji katedra sa ovim id!");
            }
        }

        optional = null;
        optional = departmentRepository.findByName(departmentDto.getName());
        if (optional.isPresent()) {
            throw new InvalidDataException("vec postoji katedra sa tim nazivom");
        }
        optional = null;
        optional = departmentRepository.findByShortName(departmentDto.getShortName());
        if (optional.isPresent()) {
            throw new InvalidDataException("vec postoji katedra sa tim skracenim nazivom");
        }
        Department department = departmentMapper.departmentDtoToDepartment(departmentDto);
//        department.setId(null);
        return departmentMapper.departmentToDepartmentDto(
                departmentRepository.save(department)
        );
    }

    /**
     * Edits an existing department.
     *
     * @param departmentDto The DTO of the department to edit.
     * @return The edited DepartmentDto.
     * @throws InvalidDataException if the department does not exist or if
     * another department with the same name or short name already exists.
     */
    @Override
    public DepartmentDto edit(DepartmentDto departmentDto) {
        Optional<Department> optional = departmentRepository.findById(departmentDto.getId());
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji sa tim ID");
        }
        optional = null;
        optional = departmentRepository.findByName(departmentDto.getName());
        if (optional.isPresent() && optional.get().getId() != departmentDto.getId()) {
            throw new InvalidDataException("vec postoji katedra sa tim nazivom");
        }
        optional = null;
        optional = departmentRepository.findByShortName(departmentDto.getShortName());
        if (optional.isPresent() && optional.get().getId() != departmentDto.getId()) {
            throw new InvalidDataException("vec postoji katedra sa tim skracenim nazivom");
        }
        Department department = departmentMapper.departmentDtoToDepartment(departmentDto);
        return departmentMapper.departmentToDepartmentDto(
                departmentRepository.save(department)
        );
    }

    /**
     * Finds all departments.
     *
     * @return A list of DepartmentDto.
     */
    @Override
    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream().map(
                dao -> departmentMapper.departmentToDepartmentDto(dao)
        ).collect(Collectors.toList());
    }

    /**
     * Finds a department by its ID.
     *
     * @param id The ID of the department.
     * @return The DepartmentDto with the given ID.
     * @throws InvalidDataException if no department exists with the given ID.
     */
    @Override
    public DepartmentDto findById(Long id) {
        Optional<Department> optional = departmentRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji Department sa tim id");
        } else {
            return departmentMapper.departmentToDepartmentDto(optional.get());
        }
    }

    /**
     * Finds a department by its name.
     *
     * @param name The name of the department.
     * @return The DepartmentDto with the given name.
     * @throws InvalidDataException if no department exists with the given name.
     */
    @Override
    public DepartmentDto findByName(String name) {
        Optional<Department> optional = departmentRepository.findByName(name);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji Department sa tim imenom");
        } else {
            return departmentMapper.departmentToDepartmentDto(optional.get());
        }
    }

    /**
     * Finds a department by its short name.
     *
     * @param shortName The short name of the department.
     * @return The DepartmentDto with the given short name.
     * @throws InvalidDataException if no department exists with the given short
     * name.
     */
    @Override
    public DepartmentDto findByShortName(String shortName) {
        Optional<Department> optional = departmentRepository.findByShortName(shortName);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji Department sa tim skracenim nazivom");
        } else {
            return departmentMapper.departmentToDepartmentDto(optional.get());
        }
    }

    /**
     * Deletes a department by its ID.
     *
     * @param id The ID of the department to delete.
     * @throws InvalidDataException if no department exists with the given ID.
     */
    @Override
    public void delete(Long id) {
        Optional<Department> optional = departmentRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji Department sa tim id");
        } else {
            departmentRepository.delete(optional.get());
        }
    }

}
