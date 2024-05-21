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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import rs.ac.bg.fon.njt.webapp.security.service.JwtService;
import rs.ac.bg.fon.njt.webapp.configuration.TestConfiguration;
import rs.ac.bg.fon.njt.webapp.controller.DepartmentController;
import rs.ac.bg.fon.njt.webapp.controller.EducationTitleController;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;
import rs.ac.bg.fon.njt.webapp.service.DepartmentService;
import rs.ac.bg.fon.njt.webapp.service.EducationTitleService;

//static imports: MockMvcRequestBuilders.*, MockMvcResultMatchers.*;
/**
 *
 * @author aleks
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(DepartmentController.class)
@Import(TestConfiguration.class)
public class DepartmentControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private DepartmentService departmentService;

    private DepartmentDto departmentDto;

    @BeforeEach
    public void setup() {
        departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("Katedra za softversko inzenjerstvo");
        departmentDto.setShortName("SILAB");
    }

    @Test
    @WithMockUser
    public void testGetById() throws Exception {
        Mockito.when(departmentService.findById(1L)).thenReturn(departmentDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/department/get/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Katedra za softversko inzenjerstvo"))
                .andExpect(MockMvcResultMatchers.jsonPath("shortName").value("SILAB"));
    }
    @Test
    public void testGetByIdUnauthorized() throws Exception {
        Mockito.when(departmentService.findById(1L)).thenReturn(departmentDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/department/get/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void testGetAll() throws Exception {
        Mockito.when(departmentService.findAll()).thenReturn(Arrays.asList(departmentDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/department/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Katedra za softversko inzenjerstvo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].shortName").value("SILAB"));
    }
    @Test
    public void testGetAllUnauthorized() throws Exception {
        Mockito.when(departmentService.findAll()).thenReturn(Arrays.asList(departmentDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/department/getAll"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testSave() throws Exception {
        Mockito.when(departmentService.save(departmentDto)).thenReturn(departmentDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/department/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Katedra za softversko inzenjerstvo\", \"shortName\":\"SILAB\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Katedra za softversko inzenjerstvo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shortName").value("SILAB"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }
    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testSaveFailInvalidName() throws Exception {
        Mockito.when(departmentService.save(departmentDto)).thenReturn(departmentDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/department/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Kate1dra za softversko inzenjerstvo\", \"shortName\":\"SILAB\"}"))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }
    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testSaveFailInvalidShortName() throws Exception {
        Mockito.when(departmentService.save(departmentDto)).thenReturn(departmentDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/department/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Katedra za softversko inzenjerstvo\", \"shortName\":\"SILA!B\"}"))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    @WithMockUser(authorities = "USER")
    public void testSaveForbiddenRoleUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/department/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Katedra za softversko inzenjerstvo\", \"shortName\":\"SILAB\"}"))
                .andExpect(MockMvcResultMatchers.status().is(500));   
    }
    @Test
    public void testSaveForbiddenNoUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/department/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Katedra za softversko inzenjerstvo\", \"shortName\":\"SILAB\"}"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());   
    }
    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testEdit() throws Exception {
        Mockito.when(departmentService.edit(departmentDto)).thenReturn(departmentDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/department/update")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Katedra za softversko inzenjerstvo\", \"shortName\":\"SILAB\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Katedra za softversko inzenjerstvo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shortName").value("SILAB"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }
    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testEditFailInvalidName() throws Exception {
//        Mockito.when(departmentService.save(departmentDto)).thenReturn(departmentDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/department/update")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Kate1dra za softversko inzenjerstvo\", \"shortName\":\"SILAB\"}"))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }
    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testEditFailInvalidShortName() throws Exception {
//        Mockito.when(departmentService.edit(departmentDto)).thenReturn(departmentDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/department/update")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Katedra za softversko inzenjerstvo\", \"shortName\":\"SILA!B\"}"))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    @WithMockUser(authorities = "USER")
    public void testEditForbiddenRoleUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/department/update")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Katedra za softversko inzenjerstvo\", \"shortName\":\"SILAB\"}"))
                .andExpect(MockMvcResultMatchers.status().is(500));   
    }
    @Test
    public void testEditForbiddenNoUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/department/update")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Katedra za softversko inzenjerstvo\", \"shortName\":\"SILAB\"}"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());   
    }
}
