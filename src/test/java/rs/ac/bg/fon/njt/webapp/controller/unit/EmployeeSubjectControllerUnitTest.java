/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.controller.unit;

import com.sun.mail.imap.ACL;
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
import rs.ac.bg.fon.njt.webapp.controller.EmployeeSubjectController;
import rs.ac.bg.fon.njt.webapp.domain.enums.ClassType;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeSubjectDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeSubjectIdDto;
import rs.ac.bg.fon.njt.webapp.dto.SubjectDto;
import rs.ac.bg.fon.njt.webapp.security.service.JwtService;
import rs.ac.bg.fon.njt.webapp.service.EmployeeSubjectService;

/**
 *
 * @author aleks
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeSubjectController.class)
@Import(TestConfiguration.class)
public class EmployeeSubjectControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private EmployeeSubjectService service;

    private EmployeeSubjectDto es1;
    private EmployeeSubjectDto es2;
    private SubjectDto subject;
    private EmployeeDto employeeDto1;
    private EmployeeDto employeeDto2;
    private AcademicTitleDto academicTitleDto;

    @BeforeEach
    public void setup() {
        subject = new SubjectDto(1l, "Njt", 5, StudiesType.MASTER);
        academicTitleDto = new AcademicTitleDto(1l, "Profesor");
        employeeDto1 = new EmployeeDto(1l, "Aleksa", "Krsmanovic",
                LocalDate.of(1999, Month.MARCH, 21),
                new DepartmentDto(1l, "Silab", "SILAB"),
                academicTitleDto,
                new EducationTitleDto(1l, "Phd"),
                Status.ACTIVE);
        employeeDto2 = new EmployeeDto(2l, "Marko", "Markovic",
                LocalDate.of(1999, Month.MARCH, 21),
                new DepartmentDto(1l, "Silab", "SILAB"),
                academicTitleDto,
                new EducationTitleDto(1l, "Phd"),
                Status.ACTIVE);

        es1 = new EmployeeSubjectDto(new EmployeeSubjectIdDto(employeeDto1, subject), ClassType.LECTURES);
        es2 = new EmployeeSubjectDto(new EmployeeSubjectIdDto(employeeDto2, subject), ClassType.LECTURES);
    }

    @Test
    @WithMockUser
    public void testGetBySubject() throws Exception {
        Mockito.when(service.findBySubject(1l)).thenReturn(Arrays.asList(es1, es2));
        mockMvc.perform(MockMvcRequestBuilders.get("/employeeSubject/get/subject/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//dovde sam stigao
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id.employee.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id.employee.firstname").value("Aleksa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id.subject.name").value("Njt"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id.subject.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id.employee.id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id.employee.firstname").value("Marko"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id.subject.name").value("Njt"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id.subject.id").value("1"));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testSaveChangesPass() throws Exception {
        Mockito.when(service.saveChanges(Arrays.asList(es1), Arrays.asList(es2))).thenReturn(null);
String employeeSubjectJsonSave = "{"
        + "\"id\": {"
        +     "\"employee\": {"
        +         "\"id\": 1,"
        +         "\"firstname\": \"Aleksa\","
        +         "\"lastname\": \"Krsmanovic\","
        +         "\"birthday\": \"1999-03-21\","
        +         "\"department\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Silab\","
        +             "\"shortName\": \"SILAB\""
        +         "},"
        +         "\"academicTitle\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Profesor\""
        +         "},"
        +         "\"educationTitle\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Phd\""
        +         "},"
        +         "\"status\": \"ACTIVE\""
        +     "},"
        +     "\"subject\": {"
        +         "\"id\": 1,"
        +         "\"name\": \"Njt\","
        +         "\"ects\": 5,"
        +         "\"studiesType\":\"MASTER\""
        +     "}"
        + "},"
        + "\"classType\": \"LECTURES\""
        + "}";
String employeeSubjectJsonDelete = "{"
        + "\"id\": {"
        +     "\"employee\": {"
        +         "\"id\": 2,"
        +         "\"firstName\": \"Marko\","
        +         "\"lastName\": \"Markovic\","
        +         "\"birthday\": \"1999-03-21\","
        +         "\"department\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Silab\","
        +             "\"shortName\": \"SILAB\""
        +         "},"
        +         "\"academicTitle\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Profesor\""
        +         "},"
        +         "\"educationTitle\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Phd\""
        +         "},"
        +         "\"status\": \"ACTIVE\""
        +     "},"
        +     "\"subject\": {"
        +         "\"id\": 1,"
        +         "\"name\": \"Njt\","
        +         "\"ects\": 5,"
        +         "\"studiesType\":\"MASTER\""
        +     "}"
        + "},"
        + "\"classType\": \"LECTURES\""
        + "}";
        String content = new StringBuilder("{")
                .append("\"toSave\":["+employeeSubjectJsonSave+"]")
                .append(",")
                .append("\"toDelete\":["+employeeSubjectJsonDelete+"]")
                .append("}")
                .toString();
                
                
        
        mockMvc.perform(MockMvcRequestBuilders.post("/employeeSubject/saveChanges")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    @WithMockUser(authorities = "USER")
    public void testSaveChangesUSERRole() throws Exception {
        Mockito.when(service.saveChanges(Arrays.asList(es1), Arrays.asList(es2))).thenReturn(null);
String employeeSubjectJsonSave = "{"
        + "\"id\": {"
        +     "\"employee\": {"
        +         "\"id\": 1,"
        +         "\"firstname\": \"Aleksa\","
        +         "\"lastname\": \"Krsmanovic\","
        +         "\"birthday\": \"1999-03-21\","
        +         "\"department\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Silab\","
        +             "\"shortName\": \"SILAB\""
        +         "},"
        +         "\"academicTitle\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Profesor\""
        +         "},"
        +         "\"educationTitle\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Phd\""
        +         "},"
        +         "\"status\": \"ACTIVE\""
        +     "},"
        +     "\"subject\": {"
        +         "\"id\": 1,"
        +         "\"name\": \"Njt\","
        +         "\"ects\": 5,"
        +         "\"studiesType\":\"MASTER\""
        +     "}"
        + "},"
        + "\"classType\": \"LECTURES\""
        + "}";
String employeeSubjectJsonDelete = "{"
        + "\"id\": {"
        +     "\"employee\": {"
        +         "\"id\": 2,"
        +         "\"firstName\": \"Marko\","
        +         "\"lastName\": \"Markovic\","
        +         "\"birthday\": \"1999-03-21\","
        +         "\"department\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Silab\","
        +             "\"shortName\": \"SILAB\""
        +         "},"
        +         "\"academicTitle\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Profesor\""
        +         "},"
        +         "\"educationTitle\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Phd\""
        +         "},"
        +         "\"status\": \"ACTIVE\""
        +     "},"
        +     "\"subject\": {"
        +         "\"id\": 1,"
        +         "\"name\": \"Njt\","
        +         "\"ects\": 5,"
        +         "\"studiesType\":\"MASTER\""
        +     "}"
        + "},"
        + "\"classType\": \"LECTURES\""
        + "}";
        String content = new StringBuilder("{")
                .append("\"toSave\":["+employeeSubjectJsonSave+"]")
                .append(",")
                .append("\"toDelete\":["+employeeSubjectJsonDelete+"]")
                .append("}")
                .toString();
        
        mockMvc.perform(MockMvcRequestBuilders.post("/employeeSubject/saveChanges")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().is(500));
    }
    @Test
    public void testSaveChangesUnauthorized() throws Exception {
        Mockito.when(service.saveChanges(Arrays.asList(es1), Arrays.asList(es2))).thenReturn(null);
String employeeSubjectJsonSave = "{"
        + "\"id\": {"
        +     "\"employee\": {"
        +         "\"id\": 1,"
        +         "\"firstname\": \"Aleksa\","
        +         "\"lastname\": \"Krsmanovic\","
        +         "\"birthday\": \"1999-03-21\","
        +         "\"department\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Silab\","
        +             "\"shortName\": \"SILAB\""
        +         "},"
        +         "\"academicTitle\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Profesor\""
        +         "},"
        +         "\"educationTitle\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Phd\""
        +         "},"
        +         "\"status\": \"ACTIVE\""
        +     "},"
        +     "\"subject\": {"
        +         "\"id\": 1,"
        +         "\"name\": \"Njt\","
        +         "\"ects\": 5,"
        +         "\"studiesType\":\"MASTER\""
        +     "}"
        + "},"
        + "\"classType\": \"LECTURES\""
        + "}";
String employeeSubjectJsonDelete = "{"
        + "\"id\": {"
        +     "\"employee\": {"
        +         "\"id\": 2,"
        +         "\"firstName\": \"Marko\","
        +         "\"lastName\": \"Markovic\","
        +         "\"birthday\": \"1999-03-21\","
        +         "\"department\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Silab\","
        +             "\"shortName\": \"SILAB\""
        +         "},"
        +         "\"academicTitle\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Profesor\""
        +         "},"
        +         "\"educationTitle\": {"
        +             "\"id\": 1,"
        +             "\"name\": \"Phd\""
        +         "},"
        +         "\"status\": \"ACTIVE\""
        +     "},"
        +     "\"subject\": {"
        +         "\"id\": 1,"
        +         "\"name\": \"Njt\","
        +         "\"ects\": 5,"
        +         "\"studiesType\":\"MASTER\""
        +     "}"
        + "},"
        + "\"classType\": \"LECTURES\""
        + "}";
        String content = new StringBuilder("{")
                .append("\"toSave\":["+employeeSubjectJsonSave+"]")
                .append(",")
                .append("\"toDelete\":["+employeeSubjectJsonDelete+"]")
                .append("}")
                .toString();
        
        mockMvc.perform(MockMvcRequestBuilders.post("/employeeSubject/saveChanges")
                .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }


}
