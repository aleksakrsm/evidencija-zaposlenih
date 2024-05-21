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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import rs.ac.bg.fon.njt.webapp.security.service.JwtService;
import rs.ac.bg.fon.njt.webapp.configuration.TestConfiguration;
import rs.ac.bg.fon.njt.webapp.controller.SubjectController;
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;
import rs.ac.bg.fon.njt.webapp.dto.SubjectDto;
import rs.ac.bg.fon.njt.webapp.dto.SubjectFilterDto;
import rs.ac.bg.fon.njt.webapp.service.SubjectService;

//static imports: MockMvcRequestBuilders.*, MockMvcResultMatchers.*;
/**
 *
 * @author aleks
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(SubjectController.class)
@Import(TestConfiguration.class)
public class SubjectControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private SubjectService subjectService;

    private SubjectDto subjectDto;

    @BeforeEach
    public void setup() {
        subjectDto = new SubjectDto(1l, "Njt", 5, StudiesType.UNDERGRADUATE);
    }

    @Test
    @WithMockUser
    public void testGetById() throws Exception {
        Mockito.when(subjectService.findById(1L)).thenReturn(subjectDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/subject/get/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Njt"))
                .andExpect(MockMvcResultMatchers.jsonPath("ects").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("studiesType").value(StudiesType.UNDERGRADUATE.name()));
    }
    @Test
    public void testGetByIdUnauthorized() throws Exception {
        Mockito.when(subjectService.findById(1L)).thenReturn(subjectDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/subject/get/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void testGetAll() throws Exception {
        Mockito.when(subjectService.findAll()).thenReturn(Arrays.asList(subjectDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/subject/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Njt"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].ects").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].studiesType").value(StudiesType.UNDERGRADUATE.name()));
    }
    @Test
    public void testGetAllUnauthorized() throws Exception {
        Mockito.when(subjectService.findAll()).thenReturn(Arrays.asList(subjectDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/subject/getAll"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testSave() throws Exception {
        Mockito.when(subjectService.save(subjectDto)).thenReturn(subjectDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/subject/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Njt\", \"ects\":5,\"studiesType\":\"UNDERGRADUATE\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Njt"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ects").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.studiesType").value(StudiesType.UNDERGRADUATE.name()));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testSaveFailInvalidName() throws Exception {
        Mockito.when(subjectService.save(subjectDto)).thenReturn(subjectDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/subject/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"NJ5T\", \"ects\":5,\"studiesType\":\"UNDERGRADUATE\"}"))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testSaveFailInvalidECTS() throws Exception {
        Mockito.when(subjectService.save(subjectDto)).thenReturn(subjectDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/subject/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Njt\", \"ects\":-1,\"studiesType\":\"UNDERGRADUATE\"}"))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    @WithMockUser(authorities = "USER")
    public void testSaveForbiddenRoleUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/subject/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Njt\", \"ects\":1,\"studiesType\":\"UNDERGRADUATE\"}"))
                .andExpect(MockMvcResultMatchers.status().is(500));
    }

    @Test
    public void testSaveForbiddenNoUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/subject/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Njt\", \"ects\":-1,\"studiesType\":\"UNDERGRADUATE\"}"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testEdit() throws Exception {
        Mockito.when(subjectService.edit(subjectDto)).thenReturn(subjectDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/subject/update")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Njt\", \"ects\":5,\"studiesType\":\"UNDERGRADUATE\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Njt"))
                .andExpect(MockMvcResultMatchers.jsonPath("ects").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("studiesType").value(StudiesType.UNDERGRADUATE.name()));

    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testEditFailInvalidName() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/subject/update")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"NJ5T\", \"ects\":5,\"studiesType\":\"UNDERGRADUATE\"}"))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testEditFailInvalidECTS() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/subject/update")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Njt\", \"ects\":-5,\"studiesType\":\"UNDERGRADUATE\"}"))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    @WithMockUser(authorities = "USER")
    public void testEditForbiddenRoleUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/subject/update")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Njt\", \"ects\":5,\"studiesType\":\"UNDERGRADUATE\"}"))
                .andExpect(MockMvcResultMatchers.status().is(500));
    }

    @Test
    public void testEditForbiddenNoUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/subject/update")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Njt\", \"ects\":5,\"studiesType\":\"UNDERGRADUATE\"}"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void testPage() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());
        Page<SubjectDto> page = new PageImpl<>(Arrays.asList(subjectDto), pageable, 1);

        Mockito.when(subjectService.findAll(pageable)).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/subject/page")
                .param("page", "0")
                .param("pageSize", "10")
                .param("sortBy", "name")
                .param("sortDirection", "asc"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Njt"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].ects").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(0));
    }
    @Test
    public void testPageUnauthorized() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());
        Page<SubjectDto> page = new PageImpl<>(Arrays.asList(subjectDto), pageable, 1);

        Mockito.when(subjectService.findAll(pageable)).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/subject/page")
                .param("page", "0")
                .param("pageSize", "10")
                .param("sortBy", "name")
                .param("sortDirection", "asc"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = "USER")
    public void testPageFilterPaginate() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());
        Page<SubjectDto> page = new PageImpl<>(Arrays.asList(subjectDto), pageable, 1);
        SubjectFilterDto subjectFilter = new SubjectFilterDto(StudiesType.UNDERGRADUATE);
        Mockito.when(subjectService.pageFilterPaginate(subjectFilter, pageable)).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.post("/subject/pageFilterPaginate")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"studiesType\":\"UNDERGRADUATE\"}")
                .param("page", "0")
                .param("pageSize", "10")
                .param("sortBy", "name")
                .param("sortDirection", "asc"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Njt"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].ects").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(0))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testPageFilterPaginateUnauthorized() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());
        Page<SubjectDto> page = new PageImpl<>(Arrays.asList(subjectDto), pageable, 1);
        SubjectFilterDto subjectFilter = new SubjectFilterDto(StudiesType.UNDERGRADUATE);
        Mockito.when(subjectService.pageFilterPaginate(subjectFilter, pageable)).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.post("/subject/pageFilterPaginate")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"studiesType\":\"UNDERGRADUATE\"}")
                .param("page", "0")
                .param("pageSize", "10")
                .param("sortBy", "name")
                .param("sortDirection", "asc"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}
