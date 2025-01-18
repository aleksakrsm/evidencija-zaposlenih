/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.bg.fon.njt.webapp.domain.AcademicTitle;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;

/**
 *
 * @author aleks
 */
@DataJpaTest
public class AcademicTitleRepositoryTest {

    @Autowired
    private AcademicTitleRepository academicTitleRepository;

    private AcademicTitle academicTitle;
    private List<AcademicTitle> academicTitles;

    @BeforeEach
    public void setUp() {
        academicTitle = new AcademicTitle();
        academicTitle.setName("Proba1");

        academicTitles = new ArrayList<>();
        academicTitles.add(academicTitle);
        academicTitles.add(new AcademicTitle("Proba2"));
        academicTitles.add(new AcademicTitle("Proba3"));
        academicTitles.add(new AcademicTitle("Proba4"));
        academicTitles.add(new AcademicTitle("Proba5"));
        for (AcademicTitle at : academicTitles) {
            at = academicTitleRepository.save(at);
        }
    }

    @AfterEach
    public void tearDown() {
        academicTitleRepository.deleteAll();
    }

    @Test
    void givenAcademicTitle_whenSaved_thenCanBeFoundByName() {
        AcademicTitle savedTitle = academicTitleRepository.findByName(academicTitle.getName()).orElse(null);
        Assertions.assertNotNull(savedTitle);
        Assertions.assertEquals(academicTitle.getName(), savedTitle.getName());
        Assertions.assertEquals(academicTitle.getId(), savedTitle.getId());
        Assertions.assertEquals(5, academicTitles.size());
    }

    @Test
    void editJustNameTest() {
        academicTitle.setName("Novoime");
        AcademicTitle returnedTitle = academicTitleRepository.save(academicTitle);
        Assertions.assertNotNull(returnedTitle);
        Assertions.assertEquals(academicTitle.getName(), returnedTitle.getName());
        Assertions.assertEquals(academicTitle.getId(), returnedTitle.getId());
    }

    @Test
    void findAllTest() {
        List<AcademicTitle> returnedTitles = academicTitleRepository.findAll();
        Assertions.assertEquals(academicTitles, returnedTitles);
        returnedTitles.add(new AcademicTitle(10l, "PROBA10"));
        Assertions.assertNotEquals(academicTitles, returnedTitles);
    }

    @Test
    void findByIdTest() {
        Long id = academicTitle.getId();
        Optional<AcademicTitle> optionalTitle = academicTitleRepository.findById(id);
        Assertions.assertTrue(optionalTitle.isPresent());
        AcademicTitle retrievedTitle = optionalTitle.get();
        Assertions.assertEquals(academicTitle.getName(), retrievedTitle.getName());
        Assertions.assertEquals(academicTitle.getId(), retrievedTitle.getId());
    }

    @Test
    void deleteTest() {
        AcademicTitle forDeleting = academicTitleRepository.findById(academicTitle.getId()).orElse(null);
        Assertions.assertNotNull(forDeleting);
        
        academicTitleRepository.delete(forDeleting);
        
        forDeleting = academicTitleRepository.findById(academicTitle.getId()).orElse(null);
        Assertions.assertNull(forDeleting);
    }
}
