/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.njt.webapp.domain.AcademicTitle;
import rs.ac.bg.fon.njt.webapp.domain.Department;
import rs.ac.bg.fon.njt.webapp.domain.EducationTitle;
import rs.ac.bg.fon.njt.webapp.domain.Employee;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;

/**
 *
 * @author aleks
 */
@DataJpaTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
//@Transactional
public class EmployeeRepositoryTest {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private AcademicTitleRepository academicTitleRepository;
    @Autowired
    private EducationTitleRepository educationTitleRepository;

    private Employee employee;
    private Department department;
    
    private AcademicTitle academicTitle;
    private EducationTitle educationTitle;
    private List<Employee> employees;

    @BeforeEach
    public void setUp() {
        department = new Department(1l, "Silab", "Silab");
        department = departmentRepository.save(department);
        academicTitle = new AcademicTitle(1l,"Proba");
        academicTitle = academicTitleRepository.save(academicTitle);
        educationTitle = new EducationTitle(1l,"Proba");
        educationTitle = educationTitleRepository.save(educationTitle);
        
        employee = new Employee("Prvi", "Prvi", LocalDate.of(1999, Month.MARCH, 4), department, academicTitle, educationTitle, Status.ACTIVE);

        employees = new ArrayList<>();
        employees.add(employee);
        employees.add(new Employee("Drugi", "Drugi", LocalDate.of(1999, Month.MARCH, 4), department, academicTitle, educationTitle, Status.ACTIVE));
        employees.add(new Employee("Treci", "Treci", LocalDate.of(1999, Month.MARCH, 4), department, academicTitle, educationTitle, Status.ACTIVE));
        employees.add(new Employee("Cetvrti", "Cetvrti", LocalDate.of(1999, Month.MARCH, 4), department, academicTitle, educationTitle, Status.ACTIVE));
        for (Employee emp : employees) {
            emp = employeeRepository.save(emp);
        }
    }

    @AfterEach
    public void tearDown() {
        employeeRepository.deleteAll();
        departmentRepository.deleteAll();
        educationTitleRepository.deleteAll();
        academicTitleRepository.deleteAll();
    }

    @Test
    void givenEmployee_whenSaved_thenCanBeFoundById() {
        Employee savedEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        
        Assertions.assertNotNull(savedEmployee);
        Assertions.assertEquals(employee.getFirstname(), savedEmployee.getFirstname());
        Assertions.assertEquals(employee.getLastname(), savedEmployee.getLastname());
        Assertions.assertEquals(employee.getId(), savedEmployee.getId());
        Assertions.assertEquals(employee.getAcademicTitle(), savedEmployee.getAcademicTitle());
        Assertions.assertEquals(employee.getEducationTitle(), savedEmployee.getEducationTitle());
        Assertions.assertEquals(employee.getDepartment(), savedEmployee.getDepartment());
        Assertions.assertEquals(employee.getBirthday(), savedEmployee.getBirthday());
        Assertions.assertEquals(employee.getStatus(), savedEmployee.getStatus());
        Assertions.assertEquals(4, employees.size());
    }

    @Test
    void editTest() {
        employee.setFirstname("Novoime");
        employee.setLastname("Novoime");
        employee.setBirthday(LocalDate.of(1997, Month.MARCH, 3));
        employee.setStatus(Status.INACTIVE);
        Employee returnedEmployee = employeeRepository.save(employee);
        Assertions.assertNotNull(returnedEmployee);
        Assertions.assertEquals(employee.getFirstname(), returnedEmployee.getFirstname());
        Assertions.assertEquals(employee.getLastname(), returnedEmployee.getLastname());
        Assertions.assertEquals(employee.getId(), returnedEmployee.getId());
        Assertions.assertEquals(employee.getAcademicTitle(), returnedEmployee.getAcademicTitle());
        Assertions.assertEquals(employee.getEducationTitle(), returnedEmployee.getEducationTitle());
        Assertions.assertEquals(employee.getDepartment(), returnedEmployee.getDepartment());
        Assertions.assertEquals(employee.getBirthday(), returnedEmployee.getBirthday());
        Assertions.assertEquals(employee.getStatus(), returnedEmployee.getStatus());
    }

    @Test
    void findAllTest() {
        List<Employee> returnedEmployees = employeeRepository.findAll();
        Assertions.assertEquals(employees, returnedEmployees);
        returnedEmployees.add(new Employee("Peti", "Peti", LocalDate.of(1999, Month.MARCH, 4), department, academicTitle, educationTitle, Status.ACTIVE));
        Assertions.assertNotEquals(employees, returnedEmployees);
    }
    
}
