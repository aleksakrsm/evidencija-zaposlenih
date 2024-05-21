/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service.impl;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import rs.ac.bg.fon.njt.webapp.converter.SubjectMapper;
import rs.ac.bg.fon.njt.webapp.domain.Subject;
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;
import rs.ac.bg.fon.njt.webapp.dto.SubjectDto;
import rs.ac.bg.fon.njt.webapp.dto.SubjectFilterDto;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.SubjectRepository;
import rs.ac.bg.fon.njt.webapp.repository.specifications.SubjectSpecification;

/**
 *
 * @author aleks
 */
@ExtendWith(MockitoExtension.class)
public class SubjectServiceImplTest {

    @Mock
    private SubjectRepository subjectRepository;
    
    @Mock
    private SubjectMapper subjectMapper;
    
    @InjectMocks//mora klasa a ne interfejs
    private SubjectServiceImpl subjectService;
    
    private Subject subject1;
    private Subject subject2;
    private SubjectDto subjectDto1;
    private SubjectDto subjectDto2;
    
    @BeforeEach
    public void setUp() {
        subject1 = new Subject(1l, "NJT", 5, StudiesType.UNDERGRADUATE);
        subject2 = new Subject(2l, "ITEH", 5, StudiesType.UNDERGRADUATE);
        subjectDto1 = new SubjectDto(1l, "NJT", 5, StudiesType.UNDERGRADUATE);
        subjectDto2 = new SubjectDto(2l, "ITEH", 5, StudiesType.UNDERGRADUATE);
    }
    
    @Test
    void saveTestSuccess() {
        SubjectDto newDto = new SubjectDto(3l, "MLJR", 5, StudiesType.UNDERGRADUATE);
        Subject newSub = new Subject(3l, "MLJR", 5, StudiesType.UNDERGRADUATE);
        
        Mockito.when(subjectRepository.findById(newSub.getId())).thenReturn(Optional.empty());
        Mockito.when(subjectRepository.findByName(newSub.getName())).thenReturn(Optional.empty());
        Mockito.when(subjectRepository.save(newSub)).thenReturn(newSub);
        
        Mockito.when(subjectMapper.subjectDtoToSubject(newDto)).thenReturn(newSub);
        Mockito.when(subjectMapper.subjectToSubjectDto(newSub)).thenReturn(newDto);
        
        SubjectDto savedDto = subjectService.save(newDto);
        
        Assertions.assertEquals(newDto, savedDto);
    }
    
    @Test
    void saveTestFailureId() {
        SubjectDto newDto = new SubjectDto(2l, "MLJR", 5, StudiesType.UNDERGRADUATE);
        Subject newSub = new Subject(2l, "MLJR", 5, StudiesType.UNDERGRADUATE);
        Mockito.when(subjectRepository.findById(newSub.getId())).thenReturn(Optional.of(subject2));
        Assertions.assertThrows(InvalidDataException.class, () -> subjectService.save(newDto));
    }
    
    @Test
    void saveTestFailureName() {
        SubjectDto newDto = new SubjectDto(3l, "NJT", 5, StudiesType.UNDERGRADUATE);
        Subject newSub = new Subject(3l, "NJT", 5, StudiesType.UNDERGRADUATE);
        Mockito.when(subjectRepository.findById(newSub.getId())).thenReturn(Optional.empty());
        Mockito.when(subjectRepository.findByName(newSub.getName())).thenReturn(Optional.of(subject1));
        Assertions.assertThrows(InvalidDataException.class, () -> subjectService.save(newDto));
    }
    
    @Test
    void editTestSuccess() {
        subject1.setEcts(6);
        subject1.setStudiestype(StudiesType.MASTER);
        subject1.setName("NP");
        subjectDto1.setEcts(6);
        subjectDto1.setStudiesType(StudiesType.MASTER);
        subjectDto1.setName("NP");
        
        Mockito.when(subjectRepository.findById(subject1.getId())).thenReturn(Optional.of(subject1));
        Mockito.when(subjectRepository.findByName(subject1.getName())).thenReturn(Optional.empty());
        Mockito.when(subjectRepository.save(subject1)).thenReturn(subject1);
        
        Mockito.when(subjectMapper.subjectDtoToSubject(subjectDto1)).thenReturn(subject1);
        Mockito.when(subjectMapper.subjectToSubjectDto(subject1)).thenReturn(subjectDto1);
        
        SubjectDto savedDto = subjectService.edit(subjectDto1);
        
        Assertions.assertEquals(subjectDto1, savedDto);
    }
    
    @Test
    void editTestFailureId() {
        SubjectDto editDto = new SubjectDto(3l, "MLJR", 5, StudiesType.UNDERGRADUATE);
        Subject editSub = new Subject(3l, "MLJR", 5, StudiesType.UNDERGRADUATE);
        Mockito.when(subjectRepository.findById(editSub.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, () -> subjectService.edit(editDto));
    }
    
    @Test
    void editTestFailureName() {
        subject1.setName("ITEH");
        subjectDto1.setName("ITEH");
        Mockito.when(subjectRepository.findById(subject1.getId())).thenReturn(Optional.of(subject1));
        Mockito.when(subjectRepository.findByName(subject1.getName())).thenReturn(Optional.of(subject2));
        Assertions.assertThrows(InvalidDataException.class, () -> subjectService.edit(subjectDto1));
    }
    
    @Test
    void findByIdTestExsist() {
        Mockito.when(subjectMapper.subjectToSubjectDto(subject1)).thenReturn(subjectDto1);
        Mockito.when(subjectRepository.findById(subject1.getId())).thenReturn(Optional.of(subject1));
        Assertions.assertEquals(subjectDto1, subjectService.findById(subject1.getId()));
    }
    
    @Test
    void findByIdTestDontExsist() {
        Mockito.when(subjectRepository.findById(3l)).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                subjectService.findById(3l);
            }
        });
    }

    //subjectService.findAll(pageable)
    @Test
    void findAllTestPass() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").ascending());
        List<Subject> listOfSubjects = new ArrayList<Subject>();
        listOfSubjects.add(subject1);
        listOfSubjects.add(subject2);
        Mockito.when(subjectRepository.findAll(pageable)).thenReturn(new PageImpl<Subject>(listOfSubjects, pageable, 2));
        
        Mockito.when(subjectMapper.subjectToSubjectDto(subject1)).thenReturn(subjectDto1);
        Mockito.when(subjectMapper.subjectToSubjectDto(subject2)).thenReturn(subjectDto2);
        
        Page<SubjectDto> pageResult = subjectService.findAll(pageable);
        
        Assertions.assertEquals(2, pageResult.getTotalElements());
        
        Assertions.assertEquals(subjectDto1, pageResult.getContent().get(0));
        Assertions.assertEquals(subjectDto2, pageResult.getContent().get(1));
    }

    //subjectService.pageFilterPaginate(subjectFilter, pageable)
    @Test
    void pageFilterPaginateTestPass() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").ascending());
        subject1.setStudiestype(StudiesType.MASTER);
        SubjectFilterDto filterDto = new SubjectFilterDto(StudiesType.MASTER);
        
        List<Subject> listOfSubjects = new ArrayList<Subject>();
        listOfSubjects.add(subject1);
        Specification<Subject> specification = SubjectSpecification.filter(filterDto);
        try ( MockedStatic<SubjectSpecification> mockedStatic = Mockito.mockStatic(SubjectSpecification.class)) {
            mockedStatic.when(() -> SubjectSpecification.filter(filterDto))
                    .thenReturn(specification);
            
            Mockito.when(subjectRepository.findAll(SubjectSpecification.filter(filterDto), pageable)).thenReturn(new PageImpl<Subject>(listOfSubjects, pageable, 1));
            Mockito.when(subjectMapper.subjectToSubjectDto(subject1)).thenReturn(subjectDto1);
            Page<SubjectDto> pageResult = subjectService.pageFilterPaginate(filterDto, pageable);
            Assertions.assertEquals(1, pageResult.getTotalElements());
            Assertions.assertEquals(subjectDto1, pageResult.getContent().get(0));
        }
    }
}
