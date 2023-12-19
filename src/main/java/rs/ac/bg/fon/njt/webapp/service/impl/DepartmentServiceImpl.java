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
 *
 * @author aleks
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

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

    @Override
    public DepartmentDto edit(DepartmentDto departmentDto) {
        Optional<Department> optional = departmentRepository.findById(departmentDto.getId());
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji sa tim ID");
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
        return departmentMapper.departmentToDepartmentDto(
                departmentRepository.save(department)
        );
    }

    @Override
    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream().map(
                dao -> departmentMapper.departmentToDepartmentDto(dao)
        ).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto findById(Long id) {
        Optional<Department> optional = departmentRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji Department sa tim id");
        } else {
            return departmentMapper.departmentToDepartmentDto(optional.get());
        }
    }

    @Override
    public DepartmentDto findByName(String name) {
        Optional<Department> optional = departmentRepository.findByName(name);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji Department sa tim imenom");
        } else {
            return departmentMapper.departmentToDepartmentDto(optional.get());
        }
    }

    @Override
    public DepartmentDto findByShortName(String shortName) {
        Optional<Department> optional = departmentRepository.findByShortName(shortName);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji Department sa tim skracenim nazivom");
        } else {
            return departmentMapper.departmentToDepartmentDto(optional.get());
        }
    }

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
