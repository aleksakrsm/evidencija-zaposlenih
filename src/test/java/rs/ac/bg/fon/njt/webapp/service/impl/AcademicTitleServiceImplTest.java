/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.BDDAssumptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.bg.fon.njt.webapp.converter.AcademicTitleMapper;
import rs.ac.bg.fon.njt.webapp.domain.AcademicTitle;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.repository.AcademicTitleRepository;
import rs.ac.bg.fon.njt.webapp.service.AcademicTitleService;

/**
 *
 * @author aleks
 */
@ExtendWith(MockitoExtension.class)// zbog mock objekata
public class AcademicTitleServiceImplTest {

    @Mock
    private AcademicTitleRepository academicTitleRepository;

    @Mock
    private AcademicTitleMapper academicTitleMapper;

    @InjectMocks//mora klasa a ne interfejs
    private AcademicTitleServiceImpl academicTitleService;

    private AcademicTitle academicTitle1;
    private AcademicTitle academicTitle2;
    private AcademicTitleDto academicTitleDto1;
    private AcademicTitleDto academicTitleDto2;

    @BeforeEach
    public void setUp() {
        academicTitle1 = new AcademicTitle();
        academicTitle1.setId(1L);
        academicTitle1.setName("PhD");

        academicTitle2 = new AcademicTitle();
        academicTitle2.setId(2L);
        academicTitle2.setName("MSc");

        academicTitleDto1 = new AcademicTitleDto();
        academicTitleDto1.setId(1L);
        academicTitleDto1.setName("PhD");

        academicTitleDto2 = new AcademicTitleDto();
        academicTitleDto2.setId(2L);
        academicTitleDto2.setName("MSc");
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    void findAllTest() {
        Mockito.when(academicTitleRepository.findAll()).thenReturn(Arrays.asList(academicTitle1, academicTitle2));
        Mockito.when(academicTitleMapper.academicTitleToAcademicTitleDto(academicTitle1)).thenReturn(academicTitleDto1);
        Mockito.when(academicTitleMapper.academicTitleToAcademicTitleDto(academicTitle2)).thenReturn(academicTitleDto2);

        List<AcademicTitleDto> resultTitles = academicTitleService.findAll();

        Assertions.assertEquals(2, resultTitles.size());
        Assertions.assertEquals("PhD", resultTitles.get(0).getName());
        Assertions.assertEquals(academicTitleDto1, resultTitles.get(0));
        Assertions.assertEquals("MSc", resultTitles.get(1).getName());
        Assertions.assertEquals(academicTitleDto2, resultTitles.get(1));
    }

}
