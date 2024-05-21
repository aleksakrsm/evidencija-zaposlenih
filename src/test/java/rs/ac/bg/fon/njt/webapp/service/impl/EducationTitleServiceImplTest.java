/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service.impl;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.fon.njt.webapp.converter.EducationTitleMapper;
import rs.ac.bg.fon.njt.webapp.domain.EducationTitle;
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;
import rs.ac.bg.fon.njt.webapp.repository.EducationTitleRepository;

/**
 *
 * @author aleks
 */
@ExtendWith(MockitoExtension.class)// zbog mock objekata
public class EducationTitleServiceImplTest {

    @Mock
    private EducationTitleRepository educationTitleRepository;

    @Mock
    private EducationTitleMapper educationTitleMapper;

    @InjectMocks//mora klasa a ne interfejs
    private EducationTitleServiceImpl educationTitleService;

    private EducationTitle educationTitle1;
    private EducationTitle educationTitle2;
    private EducationTitleDto educationTitleDto1;
    private EducationTitleDto educationTitleDto2;

    @BeforeEach
    public void setUp() {
        educationTitle1 = new EducationTitle(1L,"PhD");
        educationTitle1 = new EducationTitle(2L,"MSc");
        
        educationTitleDto1 = new EducationTitleDto(1L,"PhD");
        educationTitleDto2 = new EducationTitleDto(2L,"MSc");
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    void findAllTest() {
        Mockito.when(educationTitleRepository.findAll()).thenReturn(Arrays.asList(educationTitle1, educationTitle2));
        Mockito.when(educationTitleMapper.educationTitleToEducationTitleDto(educationTitle1)).thenReturn(educationTitleDto1);
        Mockito.when(educationTitleMapper.educationTitleToEducationTitleDto(educationTitle2)).thenReturn(educationTitleDto2);

        List<EducationTitleDto> resultTitles = educationTitleService.findAll();

        Assertions.assertEquals(2, resultTitles.size());
        Assertions.assertEquals("PhD", resultTitles.get(0).getName());
        Assertions.assertEquals(educationTitleDto1, resultTitles.get(0));
        Assertions.assertEquals("MSc", resultTitles.get(1).getName());
        Assertions.assertEquals(educationTitleDto2, resultTitles.get(1));
    }

}
