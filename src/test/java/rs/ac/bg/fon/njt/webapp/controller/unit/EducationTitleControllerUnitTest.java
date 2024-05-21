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
import rs.ac.bg.fon.njt.webapp.security.service.JwtService;
import rs.ac.bg.fon.njt.webapp.configuration.TestConfiguration;
import rs.ac.bg.fon.njt.webapp.controller.EducationTitleController;
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;
import rs.ac.bg.fon.njt.webapp.service.EducationTitleService;

//static imports: MockMvcRequestBuilders.*, MockMvcResultMatchers.*;
/**
 *
 * @author aleks
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(EducationTitleController.class)
@Import(TestConfiguration.class)
public class EducationTitleControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private EducationTitleService educationTitleService;

    private EducationTitleDto educationTitleDto;

    @BeforeEach
    public void setup() {
        educationTitleDto = new EducationTitleDto();
        educationTitleDto.setId(1L);
        educationTitleDto.setName("PhD");
    }

    @Test
    @WithMockUser
    public void testGetAll() throws Exception {
        Mockito.when(educationTitleService.findAll()).thenReturn(Arrays.asList(educationTitleDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/educationTitle/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("PhD"));
    }

    @Test
    @WithMockUser
    public void testGetById() throws Exception {
        Mockito.when(educationTitleService.findById(1L)).thenReturn(educationTitleDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/educationTitle/get/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("PhD"));
    }
    @Test
    public void testGetAllUnauthorized() throws Exception {
        Mockito.when(educationTitleService.findAll()).thenReturn(Arrays.asList(educationTitleDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/educationTitle/getAll"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void testGetByIdUnauthorized() throws Exception {
        Mockito.when(educationTitleService.findById(1L)).thenReturn(educationTitleDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/educationTitle/get/1"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testSave() throws Exception {
        Mockito.when(educationTitleService.save(educationTitleDto)).thenReturn(educationTitleDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/educationTitle/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"PhD\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("PhD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }

    @Test
    @WithMockUser(authorities = "USER")
    public void testSaveForbiddenRoleUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/educationTitle/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"PhD\"}"))
                .andExpect(MockMvcResultMatchers.status().is(500));   
    }
    @Test
    public void testSaveForbiddenNoUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/educationTitle/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"PhD\"}"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());   
    }
}
