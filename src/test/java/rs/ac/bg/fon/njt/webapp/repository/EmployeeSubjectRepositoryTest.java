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
import java.util.stream.Collectors;
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
import rs.ac.bg.fon.njt.webapp.domain.EmployeeSubject;
import rs.ac.bg.fon.njt.webapp.domain.EmployeeSubjectID;
import rs.ac.bg.fon.njt.webapp.domain.HistoryItemID;
import rs.ac.bg.fon.njt.webapp.domain.Subject;
import rs.ac.bg.fon.njt.webapp.domain.enums.ClassType;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;

/**
 *
 * @author aleks
 */
@DataJpaTest
public class EmployeeSubjectRepositoryTest {

    // findByIdSubjectId OVO, findById, save, deleteById, delete
    @Autowired
    private EmployeeSubjectRepository repository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private AcademicTitleRepository academicTitleRepository;
    @Autowired
    private EducationTitleRepository educationTitleRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;

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

    @BeforeEach
    public void setUp() {
        d = new Department(1l, "Silab", "Silab");
        d = departmentRepository.save(d);

        at = new AcademicTitle(1l, "Proba1");
        at = academicTitleRepository.save(at);

        et = new EducationTitle(1l, "Proba");
        et = educationTitleRepository.save(et);

        s1 = new Subject(1l, "Proba1", 3, StudiesType.UNDERGRADUATE);
        s1 = subjectRepository.save(s1);
        s2 = new Subject(2l, "Proba2", 3, StudiesType.UNDERGRADUATE);
        s2 = subjectRepository.save(s2);

        employee1 = new Employee("Prvi", "Prvi", LocalDate.of(1999, Month.MARCH, 4), d, at, et, Status.ACTIVE);
        employee1 = employeeRepository.save(employee1);
        employee2 = new Employee("Drugi", "Drugi", LocalDate.of(1999, Month.MARCH, 4), d, at, et, Status.ACTIVE);
        employee2 = employeeRepository.save(employee2);

        es1 = new EmployeeSubject(new EmployeeSubjectID(employee1, s1), ClassType.LECTURES);
        repository.save(es1);
        es2 = new EmployeeSubject(new EmployeeSubjectID(employee1, s2), ClassType.LECTURES);
        repository.save(es2);
        es3 = new EmployeeSubject(new EmployeeSubjectID(employee2, s1), ClassType.LECTURES);
        repository.save(es3);
        es4 = new EmployeeSubject(new EmployeeSubjectID(employee2, s2), ClassType.LECTURES);
        repository.save(es4);

        ess = new ArrayList<>();
        ess.add(es1);
        ess.add(es2);
        ess.add(es3);
        ess.add(es4);
    }

    @AfterEach
    public void teardown() {
        repository.deleteAll();
        academicTitleRepository.deleteAll();
        educationTitleRepository.deleteAll();
        departmentRepository.deleteAll();
        employeeRepository.deleteAll();
        subjectRepository.deleteAll();
    }

    @Test
    void givenEmployeeSubject_whenSaved_thenCanBeFoundByID() {
        EmployeeSubjectID itemID = new EmployeeSubjectID(employee1, s1);

        Optional<EmployeeSubject> optionalEmployeeSubject = repository.findById(itemID);
        Assertions.assertTrue(optionalEmployeeSubject.isPresent());

        EmployeeSubject employeeSubject = optionalEmployeeSubject.get();

        Assertions.assertEquals(es1, employeeSubject);
        Assertions.assertEquals(4, ess.size());
    }

    @Test
    void deleteByIDTest() {
        EmployeeSubjectID itemID = new EmployeeSubjectID(employee1, s1);
        
        Optional<EmployeeSubject> optionalEmployeeSubject = repository.findById(itemID);
        Assertions.assertTrue(optionalEmployeeSubject.isPresent());

        repository.deleteById(itemID);

        optionalEmployeeSubject = repository.findById(itemID);
        Assertions.assertTrue(optionalEmployeeSubject.isEmpty());
    }
    @Test
    void deleteTest() {
        EmployeeSubjectID itemID = new EmployeeSubjectID(employee1, s1);
        
        Optional<EmployeeSubject> optionalEmployeeSubject = repository.findById(itemID);
        Assertions.assertTrue(optionalEmployeeSubject.isPresent());

        repository.delete(optionalEmployeeSubject.get());

        optionalEmployeeSubject = repository.findById(itemID);
        Assertions.assertTrue(optionalEmployeeSubject.isEmpty());
    }

    @Test
    void findByIdSubjectId() {
        List<EmployeeSubject> resultList = repository.findByIdSubjectId(s1.getId());
        Assertions.assertEquals(2, resultList.size());
        Assertions.assertEquals(resultList, resultList.stream().filter(x->x.getId().getSubject().getId() == s1.getId()).collect(Collectors.toList()));
    }
}
