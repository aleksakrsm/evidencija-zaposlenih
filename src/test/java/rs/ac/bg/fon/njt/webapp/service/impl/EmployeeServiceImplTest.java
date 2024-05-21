/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service.impl;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
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
import rs.ac.bg.fon.njt.webapp.converter.EmployeeMapper;
import rs.ac.bg.fon.njt.webapp.domain.AcademicTitle;
import rs.ac.bg.fon.njt.webapp.domain.Department;
import rs.ac.bg.fon.njt.webapp.domain.EducationTitle;
import rs.ac.bg.fon.njt.webapp.domain.Employee;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeFilterDto;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.AcademicTitleRepository;
import rs.ac.bg.fon.njt.webapp.repository.DepartmentRepository;
import rs.ac.bg.fon.njt.webapp.repository.EducationTitleRepository;
import rs.ac.bg.fon.njt.webapp.repository.EmployeeRepository;
import rs.ac.bg.fon.njt.webapp.repository.specifications.EmployeeSpecification;

/**
 *
 * @author aleks
 */
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private AcademicTitleRepository academicTitleRepository;
    @Mock
    private EducationTitleRepository educationTitleRepository;
    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks//mora klasa a ne interfejs
    private EmployeeServiceImpl employeeService;

    private Employee employee1;
    private Employee employee2;
    private EmployeeDto employeeDto1;
    private EmployeeDto employeeDto2;
    private AcademicTitle academicTitle;
    private AcademicTitleDto academicTitleDto;
    private EducationTitle educationTitle;
    private EducationTitleDto educationTitleDto;
    private Department department;
    private DepartmentDto departmentDto;

    @BeforeEach
    public void setUp() {
        academicTitle = new AcademicTitle(1l, "redovni profesor");
        academicTitleDto = new AcademicTitleDto(1l, "redovni profesor");
        educationTitle = new EducationTitle(1l, "PhD");
        educationTitleDto = new EducationTitleDto(1l, "PhD");
        department = new Department(1l, "Silab","SILAB");
        departmentDto = new DepartmentDto(1l, "Silab","SILAB");
        
        employeeDto1 = new EmployeeDto(1l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        employee1 = new Employee(1l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        employeeDto2 = new EmployeeDto(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        employee2 = new Employee(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
    }
    
//    Long countByAcademicTitleAndDepartment(Long academicTitleId,Long departmentId)
//    List<EmployeeDto> search(String term)
    
    @Test
    void saveTestSuccess() {
        EmployeeDto newDto = new EmployeeDto(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.empty());
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        Mockito.when(academicTitleRepository.findById(academicTitle.getId())).thenReturn(Optional.of(academicTitle));
        Mockito.when(educationTitleRepository.findById(educationTitle.getId())).thenReturn(Optional.of(educationTitle));
        Mockito.when(employeeRepository.save(newEmp)).thenReturn(newEmp);
        
        Mockito.when(employeeMapper.employeeDtoToEmployee(newDto)).thenReturn(newEmp);
        Mockito.when(employeeMapper.employeeToEmployeeDto(newEmp)).thenReturn(newDto);
        
        EmployeeDto savedDto = employeeService.save(newDto);
        
        Assertions.assertEquals(newDto, savedDto);
    }
    
    @Test
    void saveTestFailureId() {
        EmployeeDto newDto = new EmployeeDto(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.of(employee2));
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.save(newDto));
    }
    
    @Test
    void saveTestFailureAcademicTitleNonExistent() {
        EmployeeDto newDto = new EmployeeDto(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.empty());
        Mockito.when(academicTitleRepository.findById(newEmp.getAcademicTitle().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.save(newDto));
    }
    @Test
    void saveTestFailureAcademicTitleNull() {
        EmployeeDto newDto = new EmployeeDto(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, null, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, null, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.save(newDto));
    }
    @Test
    void saveTestFailureAcademicTitleIDNull() {
        academicTitle.setId(null);
        academicTitleDto.setId(null);
        EmployeeDto newDto = new EmployeeDto(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.save(newDto));
    }
    @Test
    void saveTestFailureEducationTitleNonExistent() {
        EmployeeDto newDto = new EmployeeDto(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.empty());
        Mockito.when(academicTitleRepository.findById(newEmp.getAcademicTitle().getId())).thenReturn(Optional.of(academicTitle));
        Mockito.when(educationTitleRepository.findById(newEmp.getEducationTitle().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.save(newDto));
    }
    @Test
    void saveTestFailureEducationTitleNull() {
        EmployeeDto newDto = new EmployeeDto(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, null, Status.ACTIVE);
        Employee newEmp = new Employee(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, null, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.empty());
        Mockito.when(academicTitleRepository.findById(newEmp.getAcademicTitle().getId())).thenReturn(Optional.of(academicTitle));
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.save(newDto));
    }
    @Test
    void saveTestFailureEducationTitleIDNull() {
        educationTitleDto.setId(null);
        
        EmployeeDto newDto = new EmployeeDto(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.empty());
        Mockito.when(academicTitleRepository.findById(newEmp.getAcademicTitle().getId())).thenReturn(Optional.of(academicTitle));
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.save(newDto));
    }
    @Test
    void saveTestFailureDepartmentNonExistent() {
        EmployeeDto newDto = new EmployeeDto(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.empty());
        Mockito.when(academicTitleRepository.findById(newEmp.getAcademicTitle().getId())).thenReturn(Optional.of(academicTitle));
        Mockito.when(educationTitleRepository.findById(newEmp.getEducationTitle().getId())).thenReturn(Optional.of(educationTitle));
        Mockito.when(departmentRepository.findById(newEmp.getDepartment().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.save(newDto));
    }
    @Test
    void saveTestFailureDepartmentNull() {
        EmployeeDto newDto = new EmployeeDto(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), null, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.empty());
        Mockito.when(academicTitleRepository.findById(newEmp.getAcademicTitle().getId())).thenReturn(Optional.of(academicTitle));
        Mockito.when(educationTitleRepository.findById(newEmp.getEducationTitle().getId())).thenReturn(Optional.of(educationTitle));
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.save(newDto));
    }
    @Test
    void saveTestFailureDepartmentIDNull() {
        departmentDto.setId(null);
        EmployeeDto newDto = new EmployeeDto(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(3l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.empty());
        Mockito.when(academicTitleRepository.findById(newEmp.getAcademicTitle().getId())).thenReturn(Optional.of(academicTitle));
        Mockito.when(educationTitleRepository.findById(newEmp.getEducationTitle().getId())).thenReturn(Optional.of(educationTitle));
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.save(newDto));
    }

    @Test
    void editTestSuccess() {
        EmployeeDto newDto = new EmployeeDto(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.of(employee2));
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        Mockito.when(academicTitleRepository.findById(academicTitle.getId())).thenReturn(Optional.of(academicTitle));
        Mockito.when(educationTitleRepository.findById(educationTitle.getId())).thenReturn(Optional.of(educationTitle));
        Mockito.when(employeeRepository.save(newEmp)).thenReturn(newEmp);
        
        Mockito.when(employeeMapper.employeeDtoToEmployee(newDto)).thenReturn(newEmp);
        Mockito.when(employeeMapper.employeeToEmployeeDto(newEmp)).thenReturn(newDto);
        
        EmployeeDto savedDto = employeeService.edit(newDto);
        
        Assertions.assertEquals(newDto, savedDto);
    }
    
    @Test
    void editTestFailureId() {
        EmployeeDto newDto = new EmployeeDto(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.edit(newDto));
    }
    
    @Test
    void editTestFailureAcademicTitleNonExistent() {
        EmployeeDto newDto = new EmployeeDto(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.of(employee2));
        Mockito.when(academicTitleRepository.findById(newEmp.getAcademicTitle().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.edit(newDto));
    }
    @Test
    void editTestFailureAcademicTitleNull() {
        EmployeeDto newDto = new EmployeeDto(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, null, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, null, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.of(employee2));
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.edit(newDto));
    }
    @Test
    void editTestFailureAcademicTitleIDNull() {
        academicTitle.setId(null);
        academicTitleDto.setId(null);
        EmployeeDto newDto = new EmployeeDto(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.of(employee2));
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.edit(newDto));
    }
    @Test
    void editTestFailureEducationTitleNonExistent() {
        EmployeeDto newDto = new EmployeeDto(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.of(employee2));
        Mockito.when(academicTitleRepository.findById(newEmp.getAcademicTitle().getId())).thenReturn(Optional.of(academicTitle));
        Mockito.when(educationTitleRepository.findById(newEmp.getEducationTitle().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.edit(newDto));
    }
    @Test
    void editTestFailureEducationTitleNull() {
        EmployeeDto newDto = new EmployeeDto(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, null, Status.ACTIVE);
        Employee newEmp = new Employee(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, null, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.of(employee2));
        Mockito.when(academicTitleRepository.findById(newEmp.getAcademicTitle().getId())).thenReturn(Optional.of(academicTitle));
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.edit(newDto));
    }
    @Test
    void editTestFailureEducationTitleIDNull() {
        educationTitleDto.setId(null);
        
        EmployeeDto newDto = new EmployeeDto(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.of(employee2));
        Mockito.when(academicTitleRepository.findById(newEmp.getAcademicTitle().getId())).thenReturn(Optional.of(academicTitle));
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.edit(newDto));
    }
    @Test
    void editTestFailureDepartmentNonExistent() {
        EmployeeDto newDto = new EmployeeDto(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.of(employee2));
        Mockito.when(academicTitleRepository.findById(newEmp.getAcademicTitle().getId())).thenReturn(Optional.of(academicTitle));
        Mockito.when(educationTitleRepository.findById(newEmp.getEducationTitle().getId())).thenReturn(Optional.of(educationTitle));
        Mockito.when(departmentRepository.findById(newEmp.getDepartment().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.edit(newDto));
    }
    @Test
    void editTestFailureDepartmentNull() {
        EmployeeDto newDto = new EmployeeDto(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), null, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.of(employee2));
        Mockito.when(academicTitleRepository.findById(newEmp.getAcademicTitle().getId())).thenReturn(Optional.of(academicTitle));
        Mockito.when(educationTitleRepository.findById(newEmp.getEducationTitle().getId())).thenReturn(Optional.of(educationTitle));
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.edit(newDto));
    }
    @Test
    void editTestFailureDepartmentIDNull() {
        departmentDto.setId(null);
        EmployeeDto newDto = new EmployeeDto(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), departmentDto, academicTitleDto, educationTitleDto, Status.ACTIVE);
        Employee newEmp = new Employee(2l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.MARCH, 2), department, academicTitle, educationTitle, Status.ACTIVE);
        
        Mockito.when(employeeRepository.findById(newEmp.getId())).thenReturn(Optional.of(employee2));
        Mockito.when(academicTitleRepository.findById(newEmp.getAcademicTitle().getId())).thenReturn(Optional.of(academicTitle));
        Mockito.when(educationTitleRepository.findById(newEmp.getEducationTitle().getId())).thenReturn(Optional.of(educationTitle));
        Assertions.assertThrows(InvalidDataException.class, () -> employeeService.edit(newDto));
    }
    
    @Test
    void findByIdTestExsist() {
        Mockito.when(employeeMapper.employeeToEmployeeDto(employee1)).thenReturn(employeeDto1);
        Mockito.when(employeeRepository.findById(employee1.getId())).thenReturn(Optional.of(employee1));
        Assertions.assertEquals(employeeDto1, employeeService.findById(employee1.getId()));
    }
    
    @Test
    void findByIdTestDontExsist() {
        Mockito.when(employeeRepository.findById(3l)).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidDataException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                employeeService.findById(3l);
            }
        });
    }
    
    @Test
    void pageFilterPaginateTestPass() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").ascending());
        
        
        List<Employee> listOfEmployees = new ArrayList<Employee>();
        listOfEmployees.add(employee1);
        listOfEmployees.add(employee2);
        EmployeeFilterDto filterDto = new EmployeeFilterDto(-1l, 1l, 1l, 1l, Status.ACTIVE);
        Specification<Employee> specification = EmployeeSpecification.filterEmployees(filterDto);
        try ( MockedStatic<EmployeeSpecification> mockedStatic = Mockito.mockStatic(EmployeeSpecification.class)) {
            mockedStatic.when(() -> EmployeeSpecification.filterEmployees(filterDto))
                    .thenReturn(specification);
            
            Mockito.when(employeeRepository.findAll(EmployeeSpecification.filterEmployees(filterDto), pageable)).thenReturn(new PageImpl<Employee>(listOfEmployees, pageable, 2));
            Mockito.when(employeeMapper.employeeToEmployeeDto(employee1)).thenReturn(employeeDto1);
            Mockito.when(employeeMapper.employeeToEmployeeDto(employee2)).thenReturn(employeeDto2);
            Page<EmployeeDto> pageResult = employeeService.pageFilterPaginate(filterDto, pageable);
            Assertions.assertEquals(2, pageResult.getTotalElements());
            Assertions.assertEquals(employeeDto1, pageResult.getContent().get(0));
            Assertions.assertEquals(employeeDto2, pageResult.getContent().get(1));
        }
    }

}
