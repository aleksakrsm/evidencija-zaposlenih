/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service.impl;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.fon.njt.webapp.converter.EmployeeAcademicTitleMapper;
import rs.ac.bg.fon.njt.webapp.converter.HistoryItemIdMapper;
import rs.ac.bg.fon.njt.webapp.domain.AcademicTitle;
import rs.ac.bg.fon.njt.webapp.domain.Department;
import rs.ac.bg.fon.njt.webapp.domain.EducationTitle;
import rs.ac.bg.fon.njt.webapp.domain.Employee;
import rs.ac.bg.fon.njt.webapp.domain.EmployeeAcademicTitle;
import rs.ac.bg.fon.njt.webapp.domain.HistoryItemID;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeAcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.HistoryItemIdDto;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.AcademicTitleRepository;
import rs.ac.bg.fon.njt.webapp.repository.EmployeeAcademicTitleRepository;
import rs.ac.bg.fon.njt.webapp.repository.EmployeeRepository;

/**
 *
 * @author aleks
 */
@ExtendWith(MockitoExtension.class)
public class EmployeeAcademicTitleServiceImplTest {
 
//    historyItemService.findByEmployee(id)  gotovo
//    historyItemService.saveChanges(toSave,toDelete)
    
    @Mock
    private EmployeeAcademicTitleRepository historyItemRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private AcademicTitleRepository academicTitleRepository;

    @Mock
    private EmployeeAcademicTitleMapper historyItemMapper;

    @Mock
    private HistoryItemIdMapper historyItemIdMapper;
    
    @InjectMocks
    private EmployeeAcademicTitleServiceImpl service;
    
    private EmployeeAcademicTitle eat1;
    private EmployeeAcademicTitle eat2;
    private EmployeeAcademicTitle eat3;
    private EmployeeAcademicTitle eat4;
    private List<EmployeeAcademicTitle> eats;
    private Employee employee1;
    private Employee employee2;
    private AcademicTitle at1;
    private AcademicTitle at2;
    private EducationTitle et;
    private Department d;
    
    
    private EmployeeAcademicTitleDto eatDto1;
    private EmployeeAcademicTitleDto eatDto2;
    private EmployeeAcademicTitleDto eatDto3;
    private EmployeeAcademicTitleDto eatDto4;
    private List<EmployeeAcademicTitleDto> eatsDto;
    private EmployeeDto employeeDto1;
    private EmployeeDto employeeDto2;
    private AcademicTitleDto atDto1;
    private AcademicTitleDto atDto2;
    private EducationTitleDto etDto;
    private DepartmentDto dDto;
    
    @BeforeEach
    public void setUp() {
        d = new Department(1l, "Silab", "Silab");
        dDto = new DepartmentDto(1l, "Silab", "Silab");
        at1 = new AcademicTitle(1l, "Proba1");
        atDto1 = new AcademicTitleDto(1l, "Proba1");
        at2 = new AcademicTitle(1l, "Proba2");
        atDto2 = new AcademicTitleDto(1l, "Proba2");
        et = new EducationTitle(1l, "Proba");
        etDto = new EducationTitleDto(1l, "Proba");
        employee1 = new Employee(1l,"Prvi", "Prvi", LocalDate.of(1999, Month.MARCH, 4), d, at1, et, Status.ACTIVE);
        employeeDto1 = new EmployeeDto(1l,"Prvi", "Prvi", LocalDate.of(1999, Month.MARCH, 4), dDto, atDto1, etDto, Status.ACTIVE);
        employee2 = new Employee(2l,"Drugi", "Drugi", LocalDate.of(1999, Month.MARCH, 4), d, at2, et, Status.ACTIVE);
        employeeDto2 = new EmployeeDto(2l,"Drugi", "Drugi", LocalDate.of(1999, Month.MARCH, 4), dDto, atDto2, etDto, Status.ACTIVE);
        
        
        eat1 = new EmployeeAcademicTitle(new HistoryItemID(employee1, at1, LocalDate.of(2024, Month.MARCH, 1)), LocalDate.now());
        eatDto1 = new EmployeeAcademicTitleDto(new HistoryItemIdDto(employeeDto1, atDto1, LocalDate.of(2024, Month.MARCH, 1)), LocalDate.now());
        eat2 = new EmployeeAcademicTitle(new HistoryItemID(employee1, at2, LocalDate.of(2023, Month.MARCH, 1)), LocalDate.of(2024, Month.MARCH, 1));
        eatDto2 = new EmployeeAcademicTitleDto(new HistoryItemIdDto(employeeDto1, atDto2, LocalDate.of(2023, Month.MARCH, 1)), LocalDate.of(2024, Month.MARCH, 1));
        eat3 = new EmployeeAcademicTitle(new HistoryItemID(employee2, at1, LocalDate.of(2024, Month.MARCH, 1)), LocalDate.now());
        eatDto3 = new EmployeeAcademicTitleDto(new HistoryItemIdDto(employeeDto2, atDto1, LocalDate.of(2024, Month.MARCH, 1)), LocalDate.now());
        eat4 = new EmployeeAcademicTitle(new HistoryItemID(employee2, at2, LocalDate.of(2023, Month.MARCH, 1)), LocalDate.of(2024, Month.MARCH, 1));
        eatDto4 = new EmployeeAcademicTitleDto(new HistoryItemIdDto(employeeDto2, atDto2, LocalDate.of(2023, Month.MARCH, 1)), LocalDate.of(2024, Month.MARCH, 1));
        eats = new ArrayList<>();
        eats.add(eat1);
        eats.add(eat2);
        eats.add(eat3);
        eats.add(eat4);
        
        eatsDto = new ArrayList<>();
        eatsDto.add(eatDto1);
        eatsDto.add(eatDto2);
        eatsDto.add(eatDto3);
        eatsDto.add(eatDto4);
    }
     
    @Test
    void findByEmployeeTestPass(){
        List<EmployeeAcademicTitleDto> expectedList = new ArrayList<>();
        expectedList.add(eatDto1);
        expectedList.add(eatDto2);
        
        Mockito.when(employeeRepository.findById(employee1.getId())).thenReturn(Optional.of(employee1));
        Mockito.when(historyItemRepository.findByEmployeeIdOrderByBeginDateAsc(employee1.getId())).thenReturn(Arrays.asList(eat1,eat2));
        Mockito.when(historyItemMapper.historyItemToHistoryItemDto(eat1)).thenReturn(eatDto1);
        Mockito.when(historyItemMapper.historyItemToHistoryItemDto(eat2)).thenReturn(eatDto2);
        
        Assertions.assertEquals(expectedList, service.findByEmployee(employee1.getId()));
    }
    @Test
    void findByEmployeeTestFailureEmployeeIdNull(){
        employeeDto1.setId(null);
        Assertions.assertThrows(InvalidDataException.class, () -> service.findByEmployee(employee1.getId()));
    }
    @Test
    void findByEmployeeTestFailureEmployeeNonExistent(){
        Mockito.when(employeeRepository.findById(employee1.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, () -> service.findByEmployee(employee1.getId()));
    }
     
}    
