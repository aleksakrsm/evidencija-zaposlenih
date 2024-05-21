/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import rs.ac.bg.fon.njt.webapp.domain.AcademicTitle;
import rs.ac.bg.fon.njt.webapp.domain.Department;
import rs.ac.bg.fon.njt.webapp.domain.EducationTitle;
import rs.ac.bg.fon.njt.webapp.domain.Employee;
import rs.ac.bg.fon.njt.webapp.domain.EmployeeAcademicTitle;
import rs.ac.bg.fon.njt.webapp.domain.HistoryItemID;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;

/**
 *
 * @author aleks
 */
@DataJpaTest
public class EmployeeAcademicTitleRepositoryTest {

    // save, findById, findByEmployeeIdOrderByBeginDateAsc, deleteById
    @Autowired
    private EmployeeAcademicTitleRepository repository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AcademicTitleRepository academicTitleRepository;
    @Autowired
    private EducationTitleRepository educationTitleRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

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

    @BeforeEach
    public void setUp() {
        d = new Department(1l, "Silab", "Silab");
        d = departmentRepository.save(d);

        at1 = new AcademicTitle(1l, "Proba1");
        at1 = academicTitleRepository.save(at1);
        at2 = new AcademicTitle(1l, "Proba2");
        at2 = academicTitleRepository.save(at2);

        et = new EducationTitle(1l, "Proba");
        et = educationTitleRepository.save(et);

        employee1 = new Employee("Prvi", "Prvi", LocalDate.of(1999, Month.MARCH, 4), d, at1, et, Status.ACTIVE);
        employee1 = employeeRepository.save(employee1);
        employee2 = new Employee("Drugi", "Drugi", LocalDate.of(1999, Month.MARCH, 4), d, at2, et, Status.ACTIVE);
        employee2 = employeeRepository.save(employee2);

        eat1 = new EmployeeAcademicTitle(new HistoryItemID(employee1, at1, LocalDate.of(2024, Month.MARCH, 1)), LocalDate.now());
        repository.save(eat1);
        eat2 = new EmployeeAcademicTitle(new HistoryItemID(employee1, at2, LocalDate.of(2023, Month.MARCH, 1)), LocalDate.of(2024, Month.MARCH, 1));
        eat2 = repository.save(eat2);

        eat3 = new EmployeeAcademicTitle(new HistoryItemID(employee2, at1, LocalDate.of(2024, Month.MARCH, 1)), LocalDate.now());
        eat3 = repository.save(eat3);
        eat4 = new EmployeeAcademicTitle(new HistoryItemID(employee2, at2, LocalDate.of(2023, Month.MARCH, 1)), LocalDate.of(2024, Month.MARCH, 1));
        eat4 = repository.save(eat4);

        eats = new ArrayList<>();
        eats.add(eat1);
        eats.add(eat2);
        eats.add(eat3);
        eats.add(eat4);
    }

    @AfterEach
    public void teardown() {
        repository.deleteAll();
        academicTitleRepository.deleteAll();
        educationTitleRepository.deleteAll();
        departmentRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    void givenHistoryItem_whenSaved_thenCanBeFoundByID() {
        HistoryItemID itemID = new HistoryItemID(employee1, at1, LocalDate.of(2024, Month.MARCH, 1));

        Optional<EmployeeAcademicTitle> optionalHistoryItem = repository.findById(itemID);
        Assertions.assertTrue(optionalHistoryItem.isPresent());

        EmployeeAcademicTitle historyItem = optionalHistoryItem.get();

        Assertions.assertEquals(eat1, historyItem);
        Assertions.assertEquals(4, eats.size());
    }

    @Test
    void deleteByIDTest() {
        HistoryItemID itemID = new HistoryItemID(employee1, at1, LocalDate.of(2024, Month.MARCH, 1));

        Optional<EmployeeAcademicTitle> optionalHistoryItem = repository.findById(itemID);
        Assertions.assertTrue(optionalHistoryItem.isPresent());

        repository.deleteById(itemID);

        optionalHistoryItem = repository.findById(itemID);
        Assertions.assertTrue(optionalHistoryItem.isEmpty());
    }
    @Test
    void findByEmployeeIdOrderByBeginDateAsc() {
        List<EmployeeAcademicTitle> resultList = repository.findByEmployeeIdOrderByBeginDateAsc(employee1.getId());
        Assertions.assertEquals(2, resultList.size());
        Assertions.assertEquals(resultList.get(0), eat2);
        Assertions.assertNotEquals(resultList.get(0), eat1);
        Assertions.assertEquals(resultList.get(1), eat1);
    }
    
}
