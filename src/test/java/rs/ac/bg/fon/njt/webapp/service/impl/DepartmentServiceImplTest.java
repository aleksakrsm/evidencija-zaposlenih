/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.fon.njt.webapp.converter.DepartmentMapper;
import rs.ac.bg.fon.njt.webapp.domain.Department;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.DepartmentRepository;

/**
 *
 * @author aleks
 */
@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    
    @Mock
    private DepartmentRepository departmentRepository;
    
    @Mock
    private DepartmentMapper departmentMapper;
    
    @InjectMocks//mora klasa a ne interfejs
    private DepartmentServiceImpl departmentService;
    
    private Department department1;
    private Department department2;
    private DepartmentDto departmentDto1;
    private DepartmentDto departmentDto2;
    
    @BeforeEach
    public void setUp() {
        department1 = new Department(1l, "Silab", "SILAB");
        department2 = new Department(2l, "Elab", "ELAB");
        
        departmentDto1 = new DepartmentDto(1l, "Silab", "SILAB");
        departmentDto2 = new DepartmentDto(2l, "Elab", "ELAB");
    }

//    @AfterEach
//    public void tearDown() {
//
//    }
    @Test
    void findAllTest() {
        Mockito.when(departmentRepository.findAll()).thenReturn(Arrays.asList(department1, department2));
        Mockito.when(departmentMapper.departmentToDepartmentDto(department1)).thenReturn(departmentDto1);
        Mockito.when(departmentMapper.departmentToDepartmentDto(department2)).thenReturn(departmentDto2);
        List<DepartmentDto> resultDepartments = departmentService.findAll();
        
        Assertions.assertEquals(2, resultDepartments.size());
        Assertions.assertEquals("Silab", resultDepartments.get(0).getName());
        Assertions.assertEquals("SILAB", resultDepartments.get(0).getShortName());
        Assertions.assertEquals(departmentDto1, resultDepartments.get(0));
        Assertions.assertEquals("Elab", resultDepartments.get(1).getName());
        Assertions.assertEquals("ELAB", resultDepartments.get(1).getShortName());
        Assertions.assertEquals(departmentDto2, resultDepartments.get(1));
    }
    
    @Test
    void saveTestSuccess() {
        DepartmentDto newDto = new DepartmentDto(3l, "Novi", "Novi");
        Department newDep = new Department(3l, "Novi", "Novi");
        Mockito.when(departmentRepository.findById(newDep.getId())).thenReturn(Optional.empty());
        Mockito.when(departmentRepository.findByName(newDep.getName())).thenReturn(Optional.empty());
        Mockito.when(departmentRepository.findByShortName(newDep.getShortName())).thenReturn(Optional.empty());
        Mockito.when(departmentRepository.save(newDep)).thenReturn(newDep);
        Mockito.when(departmentMapper.departmentToDepartmentDto(newDep)).thenReturn(newDto);
        Mockito.when(departmentMapper.departmentDtoToDepartment(newDto)).thenReturn(newDep);
        
        DepartmentDto savedDto = departmentService.save(newDto);
        
        Assertions.assertEquals(newDto, savedDto);
        
    }
    
    @Test
    void saveTestFailureId() {
        DepartmentDto newDto = new DepartmentDto(3l, "Novi", "Novi");
        Department newDep = new Department(3l, "Novi", "Novi");
        Mockito.when(departmentRepository.findById(newDep.getId())).thenReturn(Optional.of(newDep));
        Assertions.assertThrows(InvalidDataException.class, () -> departmentService.save(newDto));
    }
    
    @Test
    void saveTestFailureName() {
        DepartmentDto newDto = new DepartmentDto(3l, "Novi", "Novi");
        Department newDep = new Department(3l, "Novi", "Novi");
        Mockito.when(departmentRepository.findById(newDep.getId())).thenReturn(Optional.empty());
        Mockito.when(departmentRepository.findByName(newDep.getName())).thenReturn(Optional.of(newDep));
        Assertions.assertThrows(InvalidDataException.class, () -> departmentService.save(newDto));
    }
    
    @Test
    void saveTestFailureShortName() {
        DepartmentDto newDto = new DepartmentDto(3l, "Novi", "Novi");
        Department newDep = new Department(3l, "Novi", "Novi");
        Mockito.when(departmentRepository.findById(newDep.getId())).thenReturn(Optional.empty());
        Mockito.when(departmentRepository.findByName(newDep.getName())).thenReturn(Optional.empty());
        Mockito.when(departmentRepository.findByShortName(newDep.getShortName())).thenReturn(Optional.of(newDep));
        Assertions.assertThrows(InvalidDataException.class, () -> departmentService.save(newDto));
    }
    
    @Test
    void editTestSuccess() {
        department1.setName("Proba");
        department1.setShortName("Proba");
        
        departmentDto1.setName("Proba");
        departmentDto1.setShortName("Proba");
        Mockito.when(departmentRepository.findById(department1.getId())).thenReturn(Optional.of(department1));
        Mockito.when(departmentRepository.findByName(department1.getName())).thenReturn(Optional.empty());
        Mockito.when(departmentRepository.findByShortName(department1.getShortName())).thenReturn(Optional.empty());
        Mockito.when(departmentRepository.save(department1)).thenReturn(department1);
        Mockito.when(departmentMapper.departmentToDepartmentDto(department1)).thenReturn(departmentDto1);
        Mockito.when(departmentMapper.departmentDtoToDepartment(departmentDto1)).thenReturn(department1);
        
        DepartmentDto savedDto = departmentService.edit(departmentDto1);
        
        Assertions.assertEquals(departmentDto1, savedDto);
    }
    
    @Test
    void editTestFailureId() {
        department1.setName("Proba");
        department1.setShortName("Proba");
        
        departmentDto1.setName("Proba");
        departmentDto1.setShortName("Proba");
        Mockito.when(departmentRepository.findById(department1.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, () -> departmentService.edit(departmentDto1));
    }

    @Test
    void editTestFailureName() {
        Mockito.when(departmentRepository.findById(department1.getId())).thenReturn(Optional.of(department1));
        Mockito.when(departmentRepository.findByName(department1.getName())).thenReturn(Optional.of(department2));
        
        Assertions.assertThrows(InvalidDataException.class, () -> departmentService.edit(departmentDto1));
    }
    @Test
    void editTestFailureShortName() {
        Mockito.when(departmentRepository.findById(department1.getId())).thenReturn(Optional.of(department1));
        Mockito.when(departmentRepository.findByName(department1.getName())).thenReturn(Optional.empty());
        Mockito.when(departmentRepository.findByShortName(department1.getShortName())).thenReturn(Optional.of(department2));
        
        Assertions.assertThrows(InvalidDataException.class, () -> departmentService.edit(departmentDto1));
    }
    
    @Test
    void findByIdTestExsist() {
        Mockito.when(departmentMapper.departmentToDepartmentDto(department1)).thenReturn(departmentDto1);
        Mockito.when(departmentRepository.findById(department1.getId())).thenReturn(Optional.of(department1));
        Assertions.assertEquals(departmentDto1, departmentService.findById(department1.getId()));
    }
    
    @Test
    void findByIdTestDontExsist() {
        Mockito.when(departmentRepository.findById(3l)).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                departmentService.findById(3l);
            }
        });
    }
}
