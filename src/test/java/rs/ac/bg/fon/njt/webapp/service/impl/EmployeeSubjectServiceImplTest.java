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
import rs.ac.bg.fon.njt.webapp.converter.EmployeeSubjectIdMapper;
import rs.ac.bg.fon.njt.webapp.converter.EmployeeSubjectMapper;
import rs.ac.bg.fon.njt.webapp.domain.AcademicTitle;
import rs.ac.bg.fon.njt.webapp.domain.Department;
import rs.ac.bg.fon.njt.webapp.domain.EducationTitle;
import rs.ac.bg.fon.njt.webapp.domain.Employee;
import rs.ac.bg.fon.njt.webapp.domain.EmployeeSubject;
import rs.ac.bg.fon.njt.webapp.domain.EmployeeSubjectID;
import rs.ac.bg.fon.njt.webapp.domain.Subject;
import rs.ac.bg.fon.njt.webapp.domain.enums.ClassType;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeSubjectDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeSubjectIdDto;
import rs.ac.bg.fon.njt.webapp.dto.SubjectDto;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.EmployeeRepository;
import rs.ac.bg.fon.njt.webapp.repository.EmployeeSubjectRepository;
import rs.ac.bg.fon.njt.webapp.repository.SubjectRepository;

/**
 *
 * @author aleks
 */
@ExtendWith(MockitoExtension.class)
public class EmployeeSubjectServiceImplTest {

//    employeeSubjectService.findBySubject(id) gotovo
//    employeeSubjectService.saveChanges(toSave, toDelete)

    @Mock
    private EmployeeSubjectRepository employeeSubjectRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private EmployeeSubjectMapper employeeSubjectMapper;

    @InjectMocks
    private EmployeeSubjectServiceImpl service;

    private EmployeeSubject es1;
    private EmployeeSubject es2;
    private EmployeeSubject es3;
    private EmployeeSubject es4;
    private List<EmployeeSubject> ess;
    private Employee employee1;
    private Employee employee2;
    private Subject s1;
    private Subject s2;
    private AcademicTitle at;
    private EducationTitle et;
    private Department d;

    private EmployeeSubjectDto esDto1;
    private EmployeeSubjectDto esDto2;
    private EmployeeSubjectDto esDto3;
    private EmployeeSubjectDto esDto4;
    private List<EmployeeSubjectDto> essDto;
    private EmployeeDto employeeDto1;
    private EmployeeDto employeeDto2;
    private SubjectDto sDto1;
    private SubjectDto sDto2;
    private AcademicTitleDto atDto;
    private EducationTitleDto etDto;
    private DepartmentDto dDto;

    @BeforeEach
    public void setUp() {
        s1 = new Subject(1l, "Proba", 3, StudiesType.UNDERGRADUATE);
        sDto1 = new SubjectDto(1l, "Proba", 3, StudiesType.UNDERGRADUATE);
        s1 = new Subject(2l, "Proba2", 3, StudiesType.UNDERGRADUATE);
        sDto2 = new SubjectDto(2l, "Proba2", 3, StudiesType.UNDERGRADUATE);
        d = new Department(1l, "Silab", "Silab");
        dDto = new DepartmentDto(1l, "Silab", "Silab");
        at = new AcademicTitle(1l, "Proba1");
        atDto = new AcademicTitleDto(1l, "Proba1");
        et = new EducationTitle(1l, "Proba");
        etDto = new EducationTitleDto(1l, "Proba");
        employee1 = new Employee(1l, "Prvi", "Prvi", LocalDate.of(1999, Month.MARCH, 4), d, at, et, Status.ACTIVE);
        employeeDto1 = new EmployeeDto(1l, "Prvi", "Prvi", LocalDate.of(1999, Month.MARCH, 4), dDto, atDto, etDto, Status.ACTIVE);
        employee2 = new Employee(2l, "Drugi", "Drugi", LocalDate.of(1999, Month.MARCH, 4), d, at, et, Status.ACTIVE);
        employeeDto2 = new EmployeeDto(2l, "Drugi", "Drugi", LocalDate.of(1999, Month.MARCH, 4), dDto, atDto, etDto, Status.ACTIVE);

        es1 = new EmployeeSubject(new EmployeeSubjectID(employee1, s1), ClassType.LECTURES);
        es2 = new EmployeeSubject(new EmployeeSubjectID(employee1, s2), ClassType.LECTURES);
        es3 = new EmployeeSubject(new EmployeeSubjectID(employee2, s1), ClassType.LECTURES);
        es4 = new EmployeeSubject(new EmployeeSubjectID(employee2, s2), ClassType.LECTURES);
        esDto1 = new EmployeeSubjectDto(new EmployeeSubjectIdDto(employeeDto1, sDto1), ClassType.LECTURES);
        esDto2 = new EmployeeSubjectDto(new EmployeeSubjectIdDto(employeeDto1, sDto2), ClassType.LECTURES);
        esDto3 = new EmployeeSubjectDto(new EmployeeSubjectIdDto(employeeDto2, sDto1), ClassType.LECTURES);
        esDto4 = new EmployeeSubjectDto(new EmployeeSubjectIdDto(employeeDto2, sDto2), ClassType.LECTURES);
        
        ess = new ArrayList<>();
        ess.add(es1);
        ess.add(es2);
        ess.add(es3);
        ess.add(es4);

        essDto = new ArrayList<>();
        essDto.add(esDto1);
        essDto.add(esDto2);
        essDto.add(esDto3);
        essDto.add(esDto4);
    }

    @Test
    void findBySubjectTestPass() {
        List<EmployeeSubjectDto> expectedList = new ArrayList<>();
        expectedList.add(esDto1);
        expectedList.add(esDto3);

        Mockito.when(subjectRepository.findById(s1.getId())).thenReturn(Optional.of(s1));
        
        Mockito.when(employeeSubjectRepository.findByIdSubjectId(s1.getId())).thenReturn(Arrays.asList(es1, es3));
        Mockito.when(employeeSubjectMapper.employeeSubjectToEmployeeSubjectDto(es1)).thenReturn(esDto1);
        Mockito.when(employeeSubjectMapper.employeeSubjectToEmployeeSubjectDto(es3)).thenReturn(esDto3);

        Assertions.assertEquals(expectedList, service.findBySubject(s1.getId()));
    }

    @Test
    void findBySubjectTestFailureSubjectIdNull() {
        sDto1.setId(null);
        Assertions.assertThrows(InvalidDataException.class, () -> service.findBySubject(sDto1.getId()));
    }

    @Test
    void findBySubjectTestFailureSubjectNonExistent() {
        Mockito.when(subjectRepository.findById(s1.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, () -> service.findBySubject(s1.getId()));
    }

//    @Transactional
//    public List<EmployeeSubjectDto> saveChanges(List<EmployeeSubjectDto> toSave, List<EmployeeSubjectDto> toDelete) {
//        for (EmployeeSubjectDto deleteItem : toDelete) {
//            empSubRep.deleteById(empSubIDMapper.employeeSubjectIdDtoToEmployeeSubjectId(deleteItem.getId()));
//        }
//        for (EmployeeSubjectDto itemDto : toSave) {
//            empSubRep.save(empSubMapper.employeeSubjectDtoToEmployeeSubject(itemDto));
//        }
//        return null;
//    }
}
