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
import rs.ac.bg.fon.njt.webapp.domain.Subject;
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;

/**
 *
 * @author aleks
 */
@DataJpaTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
@Transactional
public class SubjectRepositoryTest {

    @Autowired
    private SubjectRepository subjectRepository;

    private Subject subject;
    private List<Subject> subjects;

    @BeforeEach
    public void setUp() {
        subject = new Subject();
        subject.setName("Proba1");
        subject.setEcts(3);
        subject.setStudiestype(StudiesType.UNDERGRADUATE);

        subjects = new ArrayList<>();
        subjects.add(subject);
        subjects.add(new Subject("Proba2", 3, StudiesType.UNDERGRADUATE));
        subjects.add(new Subject("Proba3", 3, StudiesType.UNDERGRADUATE));
        subjects.add(new Subject("Proba4", 3, StudiesType.UNDERGRADUATE));
        subjects.add(new Subject("Proba5", 3, StudiesType.UNDERGRADUATE));
        for (Subject sub : subjects) {
            sub = subjectRepository.save(sub);
        }
    }

    @AfterEach
    public void tearDown() {
        subjectRepository.deleteAll();
    }

    @Test
    void givenSubject_whenSaved_thenCanBeFoundByName() {
        Subject savedSubject = subjectRepository.findByName(subject.getName()).orElse(null);
        Assertions.assertNotNull(savedSubject);
        Assertions.assertEquals(subject.getName(), savedSubject.getName());
        Assertions.assertEquals(subject.getEcts(), savedSubject.getEcts());
        Assertions.assertEquals(subject.getId(), savedSubject.getId());
        Assertions.assertEquals(subject.getStudiestype(), savedSubject.getStudiestype());
        Assertions.assertEquals(5, subjects.size());
    }

    @Test
    void editTest() {
        subject.setName("Novoime");
        Subject returnedSubject = subjectRepository.save(subject);
        Assertions.assertNotNull(returnedSubject);
        Assertions.assertEquals(subject.getName(), returnedSubject.getName());
        Assertions.assertEquals(subject.getEcts(), returnedSubject.getEcts());
        Assertions.assertEquals(subject.getStudiestype(), returnedSubject.getStudiestype());
        Assertions.assertEquals(subject.getId(), returnedSubject.getId());
    }

    @Test
    void findAllTest() {
        List<Subject> returnedSubjects = subjectRepository.findAll();
        Assertions.assertEquals(subjects, returnedSubjects);
        returnedSubjects.add(new Subject(10l, "PROBA10", 3,StudiesType.UNDERGRADUATE));
        Assertions.assertNotEquals(subjects, returnedSubjects);
    }

    @Test
    void findByIdTest() {
        Long id = subject.getId();
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        Assertions.assertTrue(optionalSubject.isPresent());
        Subject retrievedDepartment = optionalSubject.get();
        Assertions.assertEquals(subject.getName(), retrievedDepartment.getName());
        Assertions.assertEquals(subject.getEcts(), retrievedDepartment.getEcts());
        Assertions.assertEquals(subject.getStudiestype(), retrievedDepartment.getStudiestype());
        Assertions.assertEquals(subject.getId(), retrievedDepartment.getId());
    }


    @Test
    void deleteTest() {
        Subject forDeleting = subjectRepository.findById(subject.getId()).orElse(null);
        Assertions.assertNotNull(forDeleting);

        subjectRepository.delete(forDeleting);

        forDeleting = subjectRepository.findById(subject.getId()).orElse(null);
        Assertions.assertNull(forDeleting);
    }
}
