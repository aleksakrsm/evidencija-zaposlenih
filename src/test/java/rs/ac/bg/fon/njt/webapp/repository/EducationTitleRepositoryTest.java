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
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
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
public class EducationTitleRepositoryTest {
    
    @Autowired
    private EducationTitleRepository educationTitleRepository;

    private EducationTitle educationTitle;
    private List<EducationTitle> educationTitles;

    @BeforeEach
    public void setUp() {
        educationTitle = new EducationTitle();
        educationTitle.setName("Proba1");

        educationTitles = new ArrayList<>();
        educationTitles.add(educationTitle);
        educationTitles.add(new EducationTitle("Proba2"));
        educationTitles.add(new EducationTitle("Proba3"));
        educationTitles.add(new EducationTitle("Proba4"));
        educationTitles.add(new EducationTitle("Proba5"));
        for (EducationTitle et : educationTitles) {
            et = educationTitleRepository.save(et);
        }
    }

    @AfterEach
    public void tearDown() {
        educationTitleRepository.deleteAll();
    }

    @Test
    void givenEducationTitle_whenSaved_thenCanBeFoundByName() {
        EducationTitle savedTitle = educationTitleRepository.findByName(educationTitle.getName()).orElse(null);
        Assertions.assertNotNull(savedTitle);
        Assertions.assertEquals(educationTitle.getName(), savedTitle.getName());
        Assertions.assertEquals(educationTitle.getId(), savedTitle.getId());
        Assertions.assertEquals(5, educationTitles.size());
    }

    @Test
    void editJustNameTest() {
        educationTitle.setName("Novoime");
        EducationTitle returnedTitle = educationTitleRepository.save(educationTitle);
        Assertions.assertNotNull(returnedTitle);
        Assertions.assertEquals(educationTitle.getName(), returnedTitle.getName());
        Assertions.assertEquals(educationTitle.getId(), returnedTitle.getId());
    }

    @Test
    void findAllTest() {
        List<EducationTitle> returnedTitles = educationTitleRepository.findAll();
        Assertions.assertEquals(educationTitles, returnedTitles);
        returnedTitles.add(new EducationTitle(10l, "PROBA10"));
        Assertions.assertNotEquals(educationTitles, returnedTitles);
    }

    @Test
    void findByIdTest() {
        Long id = educationTitle.getId();
        Optional<EducationTitle> optionalTitle = educationTitleRepository.findById(id);
        Assertions.assertTrue(optionalTitle.isPresent());
        EducationTitle retrievedTitle = optionalTitle.get();
        Assertions.assertEquals(educationTitle.getName(), retrievedTitle.getName());
        Assertions.assertEquals(educationTitle.getId(), retrievedTitle.getId());
    }

    @Test
    void deleteTest() {
        EducationTitle forDeleting = educationTitleRepository.findById(educationTitle.getId()).orElse(null);
        Assertions.assertNotNull(forDeleting);
        
        educationTitleRepository.delete(forDeleting);
        
        forDeleting = educationTitleRepository.findById(educationTitle.getId()).orElse(null);
        Assertions.assertNull(forDeleting);
    }
}
