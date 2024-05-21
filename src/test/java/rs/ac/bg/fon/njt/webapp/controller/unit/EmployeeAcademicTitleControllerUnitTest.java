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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import rs.ac.bg.fon.njt.webapp.configuration.TestConfiguration;
import rs.ac.bg.fon.njt.webapp.controller.EmployeeAcademicTitleController;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeAcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.HistoryItemIdDto;
import rs.ac.bg.fon.njt.webapp.security.service.JwtService;
import rs.ac.bg.fon.njt.webapp.service.EmployeeAcademicTitleService;

/**
 *
 * @author aleks
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeAcademicTitleController.class)
@Import(TestConfiguration.class)
public class EmployeeAcademicTitleControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private EmployeeAcademicTitleService service;

    private EmployeeAcademicTitleDto eat1;
    private EmployeeAcademicTitleDto eat2;
    private EmployeeDto employeeDto;
    private AcademicTitleDto academicTitleDto1;
    private AcademicTitleDto academicTitleDto2;

    @BeforeEach
    public void setup() {
        academicTitleDto1 = new AcademicTitleDto(1l, "Profesor");
        academicTitleDto2 = new AcademicTitleDto(2l, "Saradnik");
        employeeDto = new EmployeeDto(1l, "Aleksa", "Krsmanovic",
                LocalDate.of(1999, Month.MARCH, 21),
                new DepartmentDto(1l, "Silab", "SILAB"),
                academicTitleDto1,
                new EducationTitleDto(1l, "Phd"),
                Status.ACTIVE);

        eat1 = new EmployeeAcademicTitleDto(new HistoryItemIdDto(employeeDto, academicTitleDto1, LocalDate.of(2022, Month.MARCH, 3)), LocalDate.now());
        eat2 = new EmployeeAcademicTitleDto(new HistoryItemIdDto(employeeDto, academicTitleDto2, LocalDate.of(2020, Month.MARCH, 3)), LocalDate.of(2022, Month.MARCH, 3));
    }

    @Test
    @WithMockUser
    public void testGetByEmployee() throws Exception {
        Mockito.when(service.findByEmployee(1l)).thenReturn(Arrays.asList(eat1, eat2));

        mockMvc.perform(MockMvcRequestBuilders.get("/historyItem/get/employee/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].historyItemIdDto.employee.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].historyItemIdDto.employee.firstname").value("Aleksa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].historyItemIdDto.academicTitle.name").value("Profesor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].historyItemIdDto.academicTitle.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].historyItemIdDto.employee.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].historyItemIdDto.employee.firstname").value("Aleksa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].historyItemIdDto.academicTitle.name").value("Saradnik"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].historyItemIdDto.academicTitle.id").value("2"));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testSaveChangesPass() throws Exception {
        Mockito.when(service.saveChanges(Arrays.asList(eat1), Arrays.asList(eat2))).thenReturn(null);

        String content = new StringBuilder("{")
                .append("\"toSave\":[{\n" +
"    \"historyItemIdDto\": {\n" +
"        \"employee\": {\n" +
"            \"id\": 1,\n" +
"            \"firstname\": \"Aleksa\",\n" +
"            \"lastname\": \"Krsmanovic\",\n" +
"            \"birthday\": \"1999-03-21\",\n" +
"            \"department\": {\n" +
"                \"id\": 1,\n" +
"                \"name\": \"Silab\",\n" +
"                \"shortName\": \"SILAB\"\n" +
"            },\n" +
"            \"academicTitle\": {\n" +
"                \"id\": 1,\n" +
"                \"name\": \"Profesor\"\n" +
"            },\n" +
"            \"educationTitle\": {\n" +
"                \"id\": 1,\n" +
"                \"name\": \"Phd\"\n" +
"            },\n" +
"            \"status\": \"ACTIVE\"\n" +
"        },\n" +
"        \"academicTitle\": {\n" +
"            \"id\": 1,\n" +
"            \"name\": \"Profesor\"\n" +
"        },\n" +
"        \"beginDate\": \"2022-03-03\"\n" +
"    },\n" +
"    \"endDate\": \"2024-05-20\"\n" +
"}]")
                .append(",")
                .append("\"toDelete\":[{\n" +
"    \"historyItemIdDto\": {\n" +
"        \"employee\": {\n" +
"            \"id\": 1,\n" +
"            \"firstname\": \"Aleksa\",\n" +
"            \"lastname\": \"Krsmanovic\",\n" +
"            \"birthday\": \"1999-03-21\",\n" +
"            \"department\": {\n" +
"                \"id\": 1,\n" +
"                \"name\": \"Silab\",\n" +
"                \"shortName\": \"SILAB\"\n" +
"            },\n" +
"            \"academicTitle\": {\n" +
"                \"id\": 2,\n" +
"                \"name\": \"Saradnik\"\n" +
"            },\n" +
"            \"educationTitle\": {\n" +
"                \"id\": 1,\n" +
"                \"name\": \"Phd\"\n" +
"            },\n" +
"            \"status\": \"ACTIVE\"\n" +
"        },\n" +
"        \"academicTitle\": {\n" +
"            \"id\": 1,\n" +
"            \"name\": \"Profesor\"\n" +
"        },\n" +
"        \"beginDate\": \"2020-03-03\"\n" +
"    },\n" +
"    \"endDate\": \"2022-03-03\"\n" +
"}]")
                .append("}")
                .toString();
                
                
        
        mockMvc.perform(MockMvcRequestBuilders.post("/historyItem/saveChanges")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    @WithMockUser(authorities = "USER")
    public void testSaveChangesFailUser() throws Exception {
        Mockito.when(service.saveChanges(Arrays.asList(eat1), Arrays.asList(eat2))).thenReturn(null);

        String content = new StringBuilder("{")
                .append("\"toSave\":[{\n" +
"    \"historyItemIdDto\": {\n" +
"        \"employee\": {\n" +
"            \"id\": 1,\n" +
"            \"firstname\": \"Aleksa\",\n" +
"            \"lastname\": \"Krsmanovic\",\n" +
"            \"birthday\": \"1999-03-21\",\n" +
"            \"department\": {\n" +
"                \"id\": 1,\n" +
"                \"name\": \"Silab\",\n" +
"                \"shortName\": \"SILAB\"\n" +
"            },\n" +
"            \"academicTitle\": {\n" +
"                \"id\": 1,\n" +
"                \"name\": \"Profesor\"\n" +
"            },\n" +
"            \"educationTitle\": {\n" +
"                \"id\": 1,\n" +
"                \"name\": \"Phd\"\n" +
"            },\n" +
"            \"status\": \"ACTIVE\"\n" +
"        },\n" +
"        \"academicTitle\": {\n" +
"            \"id\": 1,\n" +
"            \"name\": \"Profesor\"\n" +
"        },\n" +
"        \"beginDate\": \"2022-03-03\"\n" +
"    },\n" +
"    \"endDate\": \"2024-05-20\"\n" +
"}]")
                .append(",")
                .append("\"toDelete\":[{\n" +
"    \"historyItemIdDto\": {\n" +
"        \"employee\": {\n" +
"            \"id\": 1,\n" +
"            \"firstname\": \"Aleksa\",\n" +
"            \"lastname\": \"Krsmanovic\",\n" +
"            \"birthday\": \"1999-03-21\",\n" +
"            \"department\": {\n" +
"                \"id\": 1,\n" +
"                \"name\": \"Silab\",\n" +
"                \"shortName\": \"SILAB\"\n" +
"            },\n" +
"            \"academicTitle\": {\n" +
"                \"id\": 2,\n" +
"                \"name\": \"Saradnik\"\n" +
"            },\n" +
"            \"educationTitle\": {\n" +
"                \"id\": 1,\n" +
"                \"name\": \"Phd\"\n" +
"            },\n" +
"            \"status\": \"ACTIVE\"\n" +
"        },\n" +
"        \"academicTitle\": {\n" +
"            \"id\": 1,\n" +
"            \"name\": \"Profesor\"\n" +
"        },\n" +
"        \"beginDate\": \"2020-03-03\"\n" +
"    },\n" +
"    \"endDate\": \"2022-03-03\"\n" +
"}]")
                .append("}")
                .toString();
                
                
        
        mockMvc.perform(MockMvcRequestBuilders.post("/historyItem/saveChanges")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().is(500));
    }
    @Test
    public void testSaveChangesFailUnauthorized() throws Exception {
        Mockito.when(service.saveChanges(Arrays.asList(eat1), Arrays.asList(eat2))).thenReturn(null);

        String content = new StringBuilder("{")
                .append("\"toSave\":[{\n" +
"    \"historyItemIdDto\": {\n" +
"        \"employee\": {\n" +
"            \"id\": 1,\n" +
"            \"firstname\": \"Aleksa\",\n" +
"            \"lastname\": \"Krsmanovic\",\n" +
"            \"birthday\": \"1999-03-21\",\n" +
"            \"department\": {\n" +
"                \"id\": 1,\n" +
"                \"name\": \"Silab\",\n" +
"                \"shortName\": \"SILAB\"\n" +
"            },\n" +
"            \"academicTitle\": {\n" +
"                \"id\": 1,\n" +
"                \"name\": \"Profesor\"\n" +
"            },\n" +
"            \"educationTitle\": {\n" +
"                \"id\": 1,\n" +
"                \"name\": \"Phd\"\n" +
"            },\n" +
"            \"status\": \"ACTIVE\"\n" +
"        },\n" +
"        \"academicTitle\": {\n" +
"            \"id\": 1,\n" +
"            \"name\": \"Profesor\"\n" +
"        },\n" +
"        \"beginDate\": \"2022-03-03\"\n" +
"    },\n" +
"    \"endDate\": \"2024-05-20\"\n" +
"}]")
                .append(",")
                .append("\"toDelete\":[{\n" +
"    \"historyItemIdDto\": {\n" +
"        \"employee\": {\n" +
"            \"id\": 1,\n" +
"            \"firstname\": \"Aleksa\",\n" +
"            \"lastname\": \"Krsmanovic\",\n" +
"            \"birthday\": \"1999-03-21\",\n" +
"            \"department\": {\n" +
"                \"id\": 1,\n" +
"                \"name\": \"Silab\",\n" +
"                \"shortName\": \"SILAB\"\n" +
"            },\n" +
"            \"academicTitle\": {\n" +
"                \"id\": 2,\n" +
"                \"name\": \"Saradnik\"\n" +
"            },\n" +
"            \"educationTitle\": {\n" +
"                \"id\": 1,\n" +
"                \"name\": \"Phd\"\n" +
"            },\n" +
"            \"status\": \"ACTIVE\"\n" +
"        },\n" +
"        \"academicTitle\": {\n" +
"            \"id\": 1,\n" +
"            \"name\": \"Profesor\"\n" +
"        },\n" +
"        \"beginDate\": \"2020-03-03\"\n" +
"    },\n" +
"    \"endDate\": \"2022-03-03\"\n" +
"}]")
                .append("}")
                .toString();
                
                
        
        mockMvc.perform(MockMvcRequestBuilders.post("/historyItem/saveChanges")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }


}
