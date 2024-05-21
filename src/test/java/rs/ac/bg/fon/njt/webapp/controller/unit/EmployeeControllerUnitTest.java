/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.controller.unit;

import java.time.LocalDate;
import java.time.Month;
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
import rs.ac.bg.fon.njt.webapp.configuration.TestConfiguration;
import rs.ac.bg.fon.njt.webapp.controller.EmployeeController;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeFilterDto;
import rs.ac.bg.fon.njt.webapp.security.service.JwtService;
import rs.ac.bg.fon.njt.webapp.service.EmployeeService;

/**
 *
 * @author aleks
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeController.class)
@Import(TestConfiguration.class)
public class EmployeeControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private EmployeeService employeeService;

    private EmployeeDto employeeDto;
    String employeeJson;

    @BeforeEach
    public void setup() {
        employeeDto = new EmployeeDto(1l, "Aleksa", "Krsmanovic",
                LocalDate.of(1999, Month.MARCH, 21),
                new DepartmentDto(1l, "Silab", "SILAB"),
                new AcademicTitleDto(1l, "Profesor"),
                new EducationTitleDto(1l, "Phd"),
                Status.ACTIVE);
        employeeJson = "{"
                + "\"id\": 1,"
                + "\"firstname\": \"Aleksa\","
                + "\"lastname\": \"Krsmanovic\","
                + "\"birthday\": \"1999-03-21\","
                + "\"department\": {"
                + "\"id\": 1,"
                + "\"name\": \"Silab\","
                + "\"shortName\": \"SILAB\""
                + "},"
                + "\"academicTitle\": {"
                + "\"id\": 1,"
                + "\"name\": \"Profesor\""
                + "},"
                + "\"educationTitle\": {"
                + "\"id\": 1,"
                + "\"name\": \"Phd\""
                + "},"
                + "\"status\": \"ACTIVE\""
                + "}";
    }

    @Test
    @WithMockUser
    public void testGetById() throws Exception {
        Mockito.when(employeeService.findById(1L)).thenReturn(employeeDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/getById/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("firstname").value("Aleksa"))
                .andExpect(MockMvcResultMatchers.jsonPath("lastname").value("Krsmanovic"));
    }
    @Test
    public void testGetByIdUnauthorized() throws Exception {
        Mockito.when(employeeService.findById(1L)).thenReturn(employeeDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/getById/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void testGetAllUnauthorized() throws Exception {
        Mockito.when(employeeService.findAll()).thenReturn(Arrays.asList(employeeDto));
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/getAll"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    @Test
    @WithMockUser
    public void testGetAll() throws Exception {
        Mockito.when(employeeService.findAll()).thenReturn(Arrays.asList(employeeDto));
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/getAll"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstname").value("Aleksa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastname").value("Krsmanovic"));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testSave() throws Exception {
        Mockito.when(employeeService.save(employeeDto)).thenReturn(employeeDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/employee/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("Aleksa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value("Krsmanovic"));
    }
    
    
    @Test
    @WithMockUser(authorities = "USER")
    public void testSaveFailureForbiddenRoleUser() throws Exception {
        Mockito.when(employeeService.save(employeeDto)).thenReturn(employeeDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/employee/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(MockMvcResultMatchers.status().is(500));
    }
    
    @Test
    public void testSaveFailureNoUser() throws Exception {
        Mockito.when(employeeService.save(employeeDto)).thenReturn(employeeDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/employee/save")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    
    
    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testEdit() throws Exception {
        Mockito.when(employeeService.edit(employeeDto)).thenReturn(employeeDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/employee/update")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("Aleksa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value("Krsmanovic"));
    }
    @Test
    @WithMockUser(authorities = "USER")
    public void testEditFailureForbiddenRoleUser() throws Exception {
        Mockito.when(employeeService.edit(employeeDto)).thenReturn(employeeDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/employee/update")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(MockMvcResultMatchers.status().is(500));
    }
    @Test
    public void testEditFailureNoUser() throws Exception {
        Mockito.when(employeeService.edit(employeeDto)).thenReturn(employeeDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/employee/update")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    @Test
    @WithMockUser
    public void testCount() throws Exception {
        Mockito.when(employeeService.countByAcademicTitleAndDepartment(1l, 1l)).thenReturn(1l);
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/count")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .param("academicTitleId", "1")
                .param("departmentId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(1)));;
    }
    @Test
    public void testCountFailUnauthorized() throws Exception {
        Mockito.when(employeeService.countByAcademicTitleAndDepartment(1l, 1l)).thenReturn(1l);
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/count")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .param("academicTitleId", "1")
                .param("departmentId", "1"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = "USER")
    public void testPageFilterPaginate() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("firstname").ascending());
        Page<EmployeeDto> page = new PageImpl<>(Arrays.asList(employeeDto), pageable, 1);
        EmployeeFilterDto employeeFilter = new EmployeeFilterDto(1l, 1l, 1l, -1l, Status.ACTIVE);
        Mockito.when(employeeService.pageFilterPaginate(employeeFilter, pageable)).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/pageFilterPaginate")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"academicTitleId\":1,\"educationTitleId\":1,\"departmentId\":1,\"subjectId\":-1,\"status\":\"ACTIVE\"}")
                .param("page", "0")
                .param("pageSize", "10")
                .param("sortBy", "firstname")
                .param("sortDirection", "asc"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].firstname").value("Aleksa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].lastname").value("Krsmanovic"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(0))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testPageFilterPaginateFailUnauthorize() throws Exception {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("firstname").ascending());
        Page<EmployeeDto> page = new PageImpl<>(Arrays.asList(employeeDto), pageable, 1);
        EmployeeFilterDto employeeFilter = new EmployeeFilterDto(1l, 1l, 1l, -1l, Status.ACTIVE);
        Mockito.when(employeeService.pageFilterPaginate(employeeFilter, pageable)).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/pageFilterPaginate")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"academicTitleId\":1,\"educationTitleId\":1,\"departmentId\":1,\"subjectId\":-1,\"status\":\"ACTIVE\"}")
                .param("page", "0")
                .param("pageSize", "10")
                .param("sortBy", "firstname")
                .param("sortDirection", "asc"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}
