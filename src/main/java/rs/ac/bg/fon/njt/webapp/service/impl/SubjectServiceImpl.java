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
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;
import rs.ac.bg.fon.njt.webapp.dto.SubjectDto;
import rs.ac.bg.fon.njt.webapp.dto.SubjectFilterDto;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.SubjectRepository;
import rs.ac.bg.fon.njt.webapp.repository.specifications.SubjectSpecification;
import rs.ac.bg.fon.njt.webapp.service.SubjectService;

/**
 *
 * @author aleks
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectMapper subjectMapper;

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

    @Override
    public SubjectDto edit(SubjectDto subjectDto) {

        Optional<Subject> optional = subjectRepository.findById(subjectDto.getId());
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji sa tim ID");
        }
        optional = null;
        optional = subjectRepository.findByName(subjectDto.getName());
        if (optional.isPresent()&&optional.get().getId()!=subjectDto.getId()) {
            throw new InvalidDataException("vec postoji sa tim nazivom");
        }
        Subject subject = subjectMapper.subjectDtoToSubject(subjectDto);
        return subjectMapper.subjectToSubjectDto(
                subjectRepository.save(subject)
        );
    }

    @Override
    public Page<SubjectDto> pageFilterPaginate(SubjectFilterDto filterDto,Pageable pageable){
        Page<Subject> pageSub = subjectRepository.findAll(SubjectSpecification.filter(filterDto),pageable);
        if(pageSub.getTotalPages()<=pageable.getPageNumber() && pageable.getPageNumber()!=0){
            throw new InvalidDataException("ne postoji strana");
        }
        Page<SubjectDto> dtoPage = pageSub.map(this::convertToDto);
        return dtoPage;
    }
    private SubjectDto convertToDto(Subject subject) {
        return subjectMapper.subjectToSubjectDto(subject);
    }
    
    @Override
    public List<SubjectDto> findAll() {
        return subjectRepository.findAll().stream().map(
                dao -> subjectMapper.subjectToSubjectDto(dao)
        ).collect(Collectors.toList());
    }
    
    @Override
    public Page<SubjectDto> findAll(Pageable pageable){
        Page<Subject> page = subjectRepository.findAll(pageable);
        if(pageable.getPageNumber()>=page.getTotalPages() && pageable.getPageNumber()!=0){
            throw new InvalidDataException("ne postoji strana");
        }
        Page<SubjectDto> dtoPage = page.map(this::convertToDto);
        return dtoPage;
    }
//    @Override
//    public List<SubjectDto> findAll(Pageable pageable){
//        Page<Subject> page = subjectRepository.findAll(pageable);
//        
//        if(pageable.getPageNumber()>=page.getTotalPages()){
//            throw new InvalidDataException("ne postoji strana");
//        }
//        
//        return page.getContent().stream().map(
//                dao -> subjectMapper.subjectToSubjectDto(dao)
//        ).collect(Collectors.toList());
//    }
    
//    @Override
//    public List<SubjectDto> filter(StudiesType studiesType) {
//        return subjectRepository.findAll(SubjectSpecification.filter(studiesType)).stream().map(
//                dao -> subjectMapper.subjectToSubjectDto(dao)
//        ).collect(Collectors.toList());
//    }
    @Override
    public List<SubjectDto> search(String searchName) {
        return subjectRepository.findAll(SubjectSpecification.search(searchName)).stream().map(
                dao -> subjectMapper.subjectToSubjectDto(dao)
        ).collect(Collectors.toList());
    }

    @Override
    public SubjectDto findById(Long id) {
        Optional<Subject> optional = subjectRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji sa tim id");
        } else {
            return subjectMapper.subjectToSubjectDto(optional.get());
        }
    }

    @Override
    public SubjectDto findByName(String name) {
        Optional<Subject> optional = subjectRepository.findByName(name);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji sa tim imenom");
        } else {
            return subjectMapper.subjectToSubjectDto(optional.get());
        }
    }

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
