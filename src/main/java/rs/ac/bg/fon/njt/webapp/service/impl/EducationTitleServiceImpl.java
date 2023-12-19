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
 *
 * @author aleks
 */
@Service
public class EducationTitleServiceImpl implements EducationTitleService {

    @Autowired
    private EducationTitleRepository educationTitleRepository;

    @Autowired
    private EducationTitleMapper educationTitleMapper;

    @Override
    public EducationTitleDto save(EducationTitleDto educationTitleDto) {
        Optional<EducationTitle> optional;
        if (educationTitleDto.getId() != null) {
            optional = educationTitleRepository.findById(educationTitleDto.getId());
            if(optional.isPresent())
                throw new InvalidDataException("vec postoji titula sa ovim id!");
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

    @Override
    public List<EducationTitleDto> findAll() {
        return educationTitleRepository.findAll().stream().map(
                dao -> educationTitleMapper.educationTitleToEducationTitleDto(dao)
        ).collect(Collectors.toList());
    }

    @Override
    public EducationTitleDto findById(Long id) {
        Optional<EducationTitle> optional = educationTitleRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji EducationTitle sa tim id");
        } else {
            return educationTitleMapper.educationTitleToEducationTitleDto(optional.get());
        }
    }

    @Override
    public EducationTitleDto findByName(String name) {
        Optional<EducationTitle> optional = educationTitleRepository.findByName(name);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji EducationTitle sa tim imenom");
        } else {
            return educationTitleMapper.educationTitleToEducationTitleDto(optional.get());
        }
    }

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
