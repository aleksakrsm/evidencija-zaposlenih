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
 *
 * @author aleks
 */
@Service
public class AcademicTitleServiceImpl implements AcademicTitleService {

    @Autowired
    private AcademicTitleRepository academicTitleRepository;

    @Autowired
    private AcademicTitleMapper academicTitleMapper;

    @Override
    public AcademicTitleDto save(AcademicTitleDto academicTitleDto) {
        Optional<AcademicTitle> optional;
        if (academicTitleDto.getId() != null) {
            optional = academicTitleRepository.findById(academicTitleDto.getId());
            if(optional.isPresent())
                throw new InvalidDataException("vec postoji titula sa ovim id!");
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

    @Override
    public List<AcademicTitleDto> findAll() {
        return academicTitleRepository.findAll().stream().map(
                dao -> academicTitleMapper.academicTitleToAcademicTitleDto(dao)
        ).collect(Collectors.toList());
    }

    @Override
    public AcademicTitleDto findById(Long id) {
        Optional<AcademicTitle> optional = academicTitleRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji sa tim id");
        } else {
            return academicTitleMapper.academicTitleToAcademicTitleDto(optional.get());
        }
    }

    @Override
    public AcademicTitleDto findByName(String name) {
        Optional<AcademicTitle> optional = academicTitleRepository.findByName(name);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji sa tim imenom");
        } else {
            return academicTitleMapper.academicTitleToAcademicTitleDto(optional.get());
        }
    }

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
