/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.njt.webapp.domain.Department;
import rs.ac.bg.fon.njt.webapp.domain.EducationTitle;

/**
 *
 * @author aleks
 */
@DataJpaTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
@Transactional
public class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    private Department department;
    private List<Department> departments;

    @BeforeEach
    public void setUp() {
        department = new Department();
        department.setName("Proba1");
        department.setShortName("Proba1");

        departments = new ArrayList<>();
        departments.add(department);
        departments.add(new Department("Proba2","Proba2"));
        departments.add(new Department("Proba3","Proba3"));
        departments.add(new Department("Proba4","Proba4"));
        departments.add(new Department("Proba5","Proba5"));
        for (Department dep : departments) {
            dep = departmentRepository.save(dep);
        }
    }

    @AfterEach
    public void tearDown() {
        departmentRepository.deleteAll();
    }

    @Test
    void givenDepartment_whenSaved_thenCanBeFoundByName() {
        Department savedDepartment = departmentRepository.findByName(department.getName()).orElse(null);
        Assertions.assertNotNull(savedDepartment);
        Assertions.assertEquals(department.getName(), savedDepartment.getName());
        Assertions.assertEquals(department.getShortName(), savedDepartment.getShortName());
        Assertions.assertEquals(department.getId(), savedDepartment.getId());
        Assertions.assertEquals(5, departments.size());
    }

    @Test
    void editJustNamesTest() {
        department.setName("Novoime");
        department.setShortName("Novoime");
        Department returnedDepartment = departmentRepository.save(department);
        Assertions.assertNotNull(returnedDepartment);
        Assertions.assertEquals(department.getName(), returnedDepartment.getName());
        Assertions.assertEquals(department.getShortName(), returnedDepartment.getShortName());
        Assertions.assertEquals(department.getId(), returnedDepartment.getId());
    }

    @Test
    void findAllTest() {
        List<Department> returnedDepartments = departmentRepository.findAll();
        Assertions.assertEquals(departments, returnedDepartments);
        returnedDepartments.add(new Department(10l, "PROBA10","PROBA10"));
        Assertions.assertNotEquals(departments, returnedDepartments);
    }

    @Test
    void findByIdTest() {
        Long id = department.getId();
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        Assertions.assertTrue(optionalDepartment.isPresent());
        Department retrievedDepartment = optionalDepartment.get();
        Assertions.assertEquals(department.getName(), retrievedDepartment.getName());
        Assertions.assertEquals(department.getShortName(), retrievedDepartment.getShortName());
        Assertions.assertEquals(department.getId(), retrievedDepartment.getId());
    }
    @Test
    void findByShortNameTest() {
        Optional<Department> optionalDepartment = departmentRepository.findByShortName(department.getShortName());
        Assertions.assertTrue(optionalDepartment.isPresent());
        Department retrievedDepartment = optionalDepartment.get();
        Assertions.assertEquals(department.getName(), retrievedDepartment.getName());
        Assertions.assertEquals(department.getShortName(), retrievedDepartment.getShortName());
        Assertions.assertEquals(department.getId(), retrievedDepartment.getId());
    }

    @Test
    void deleteTest() {
        Department forDeleting = departmentRepository.findById(department.getId()).orElse(null);
        Assertions.assertNotNull(forDeleting);
        
        departmentRepository.delete(forDeleting);
        
        forDeleting = departmentRepository.findById(department.getId()).orElse(null);
        Assertions.assertNull(forDeleting);
    }
}
