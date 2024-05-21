/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.controller.unit;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import rs.ac.bg.fon.njt.webapp.controller.AcademicTitleController;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.security.service.JwtService;
import rs.ac.bg.fon.njt.webapp.service.AcademicTitleService;
import rs.ac.bg.fon.njt.webapp.configuration.TestConfiguration;

//static imports: MockMvcRequestBuilders.*, MockMvcResultMatchers.*;
/**
 *
 * @author aleks
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(AcademicTitleController.class)
@Import(TestConfiguration.class)
public class AcademicTitleControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AcademicTitleService academicTitleService;

    private AcademicTitleDto academicTitleDto;

    @BeforeEach
    public void setup() {
        academicTitleDto = new AcademicTitleDto();
        academicTitleDto.setId(1L);
        academicTitleDto.setName("Professor");
    }

    @Test
    @WithMockUser
    public void testGetAll() throws Exception {
        Mockito.when(academicTitleService.findAll()).thenReturn(Arrays.asList(academicTitleDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/academicTitle/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Professor"));
    }

    @Test
    @WithMockUser
    public void testGetById() throws Exception {
        Mockito.when(academicTitleService.findById(1L)).thenReturn(academicTitleDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/academicTitle/get/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Professor"));
    }
    @Test
    public void testGetAllUnauthorized() throws Exception {
        Mockito.when(academicTitleService.findAll()).thenReturn(Arrays.asList(academicTitleDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/academicTitle/getAll"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void testGetByIdUnauthorized() throws Exception {
        Mockito.when(academicTitleService.findById(1L)).thenReturn(academicTitleDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/academicTitle/get/1"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testSave() throws Exception {
        Mockito.when(academicTitleService.save(academicTitleDto)).thenReturn(academicTitleDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/academicTitle/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Professor\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Professor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }
    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testSaveFailureDigitInName() throws Exception {
        Mockito.when(academicTitleService.save(academicTitleDto)).thenReturn(academicTitleDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/academicTitle/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Professor1\"}"))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }
    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testSaveFailureUpperCaseLastLetter() throws Exception {
        Mockito.when(academicTitleService.save(academicTitleDto)).thenReturn(academicTitleDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/academicTitle/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"professoR\"}"))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    @WithMockUser(authorities = "USER")
    public void testSaveForbiddenRoleUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/academicTitle/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Professor\"}"))
                .andExpect(MockMvcResultMatchers.status().is(500));   
    }
    @Test
    public void testSaveForbiddenNoUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/academicTitle/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Professor\"}"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());   
    }
}
