/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.webapp.converter.AcademicTitleMapper;
import rs.ac.bg.fon.njt.webapp.converter.EducationTitleMapper;
import rs.ac.bg.fon.njt.webapp.converter.EmployeeMapper;
import rs.ac.bg.fon.njt.webapp.domain.AcademicTitle;
import rs.ac.bg.fon.njt.webapp.domain.Department;
import rs.ac.bg.fon.njt.webapp.domain.EducationTitle;
import rs.ac.bg.fon.njt.webapp.domain.Employee;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeFilterDto;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.AcademicTitleRepository;
import rs.ac.bg.fon.njt.webapp.repository.DepartmentRepository;
import rs.ac.bg.fon.njt.webapp.repository.EducationTitleRepository;
import rs.ac.bg.fon.njt.webapp.repository.EmployeeRepository;
import rs.ac.bg.fon.njt.webapp.repository.specifications.EmployeeSpecification;
import rs.ac.bg.fon.njt.webapp.service.EmployeeService;

/**
 * Implementation of the {@link EmployeeService} interface providing CRUD
 * operations and filtering capabilities for employee entities.
 *
 * This service class handles operations related to managing employees,
 * including saving, editing, finding, and deleting. It also provides methods
 * for filtering and paginating employee data based on various criteria.
 * Additionally, it offers functionality to count employees based on academic
 * title and department.
 *
 * Note: This service class assumes the existence of repositories for Employee,
 * Department, AcademicTitle, and EducationTitle entities, along with
 * corresponding mappers for DTO conversion.
 *
 * @author aleks
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private AcademicTitleRepository academicTitleRepository;

    @Autowired
    private EducationTitleRepository educationTitleRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

//    @Autowired
//    private DepartmentMapper departmentMapper;
//    
    @Autowired
    private AcademicTitleMapper academicTitleMapper;

    @Autowired
    private EducationTitleMapper educationTitleMapper;

    /**
     * Saves the given employee.
     *
     * This method validates the input employee DTO and ensures that the
     * associated academic title, education title, and department exist in the
     * database before saving.
     *
     * @param employeeDto The DTO representing the employee to be saved.
     * @return The DTO representing the saved employee.
     * @throws InvalidDataException if the employee DTO is invalid or if the
     * associated academic title, education title, or department does not exist.
     */
    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        if (employeeDto.getId() != null) {
            Optional<Employee> optional = employeeRepository.findById(employeeDto.getId());
            if (optional.isPresent()) {
                throw new InvalidDataException("zaposleni sa datim id vec postoji.");
            }
        }

        if (employeeDto.getAcademicTitle() != null) {
            if (employeeDto.getAcademicTitle().getId() == null) {
                throw new InvalidDataException("ne postoji podatak o id akademske titule");
            }
            Optional<AcademicTitle> optionalAcademicTitle = academicTitleRepository.findById(employeeDto.getAcademicTitle().getId());
            if (!optionalAcademicTitle.isPresent()) {
                throw new InvalidDataException("akademska titula sa prosledjenim id ne postoji");
            }
        } else {
            throw new InvalidDataException("ne postoji podatak o akademskoj tituli");
        }

        if (employeeDto.getEducationTitle() != null) {
            if (employeeDto.getEducationTitle().getId() == null) {
                throw new InvalidDataException("ne postoji podatak o id edukacione titule");
            }
            Optional<EducationTitle> optionalEducationTitle = educationTitleRepository.findById(employeeDto.getEducationTitle().getId());
            if (!optionalEducationTitle.isPresent()) {
                throw new InvalidDataException("edukaciona titula sa prosledjenim id ne postoji");
            }
        } else {
            throw new InvalidDataException("ne postoji podatak o edukacionoj tituli");
        }

        if (employeeDto.getDepartment() != null) {
            if (employeeDto.getDepartment().getId() == null) {
                throw new InvalidDataException("ne postoji podatak o id katedre");
            }
            Optional<Department> optionalDepartment = departmentRepository.findById(employeeDto.getDepartment().getId());
            if (!optionalDepartment.isPresent()) {
                throw new InvalidDataException("katedra sa prosledjenim id ne postoji");
            }
        } else {
            throw new InvalidDataException("ne postoji podatak o katedri");
        }

        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        return employeeMapper.employeeToEmployeeDto(employeeRepository.save(employee));
    }

    /**
     * Edits the given employee.
     *
     * This method validates the input employee DTO and ensures that the
     * associated academic title, education title, and department exist in the
     * database before editing.
     *
     * @param employeeDto The DTO representing the employee to be edited.
     * @return The DTO representing the edited employee.
     * @throws InvalidDataException if the employee DTO is invalid or if the
     * associated academic title, education title, or department does not exist.
     */
    @Override
    public EmployeeDto edit(EmployeeDto employeeDto) {
        if (employeeDto.getId() != null) {
            Optional<Employee> optional = employeeRepository.findById(employeeDto.getId());
            if (!optional.isPresent()) {
                throw new InvalidDataException("zaposleni sa datim id ne postoji.");
            }
        } else {
            throw new InvalidDataException("ne postoji informacija o id zaposlenog");
        }

        if (employeeDto.getAcademicTitle() != null) {
            if (employeeDto.getAcademicTitle().getId() == null) {
                throw new InvalidDataException("ne postoji podatak o id akademske titule");
            }
            Optional<AcademicTitle> optionalAcademicTitle = academicTitleRepository.findById(employeeDto.getAcademicTitle().getId());
            if (!optionalAcademicTitle.isPresent()) {
                throw new InvalidDataException("akademska titula sa prosledjenim id ne postoji");
            }
        } else {
            throw new InvalidDataException("ne postoji podatak o akademskoj tituli");
        }

        if (employeeDto.getEducationTitle() != null) {
            if (employeeDto.getEducationTitle().getId() == null) {
                throw new InvalidDataException("ne postoji podatak o id edukacione titule");
            }
            Optional<EducationTitle> optionalEducationTitle = educationTitleRepository.findById(employeeDto.getEducationTitle().getId());
            if (!optionalEducationTitle.isPresent()) {
                throw new InvalidDataException("edukaciona titula sa prosledjenim id ne postoji");
            }
        } else {
            throw new InvalidDataException("ne postoji podatak o edukacionoj tituli");
        }

        if (employeeDto.getDepartment() != null) {
            if (employeeDto.getDepartment().getId() == null) {
                throw new InvalidDataException("ne postoji podatak o id katedre");
            }
            Optional<Department> optionalDepartment = departmentRepository.findById(employeeDto.getDepartment().getId());
            if (!optionalDepartment.isPresent()) {
                throw new InvalidDataException("katedra sa prosledjenim id ne postoji");
            }
        } else {
            throw new InvalidDataException("ne postoji podatak o katedri");
        }

        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        employeeDto = employeeMapper.employeeToEmployeeDto(employeeRepository.save(employee));
        System.out.println(employeeDto.getStatus() + "........................");
        return employeeDto;
//        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
//        return employeeMapper.employeeToEmployeeDto(employeeRepository.save(employee));
    }

    /**
     * Retrieves all employees with pagination support.
     *
     * This method fetches a paginated list of all employees from the database.
     *
     * @param pageable The pagination information.
     * @return A list of DTOs representing the paginated employees.
     * @throws InvalidDataException if the requested page does not exist.
     */
    @Override
    public List<EmployeeDto> findAll(Pageable pageable) {
        Page<Employee> pageEmp = employeeRepository.findAll(pageable);
        if (pageEmp.getTotalPages() < pageable.getPageNumber() && pageable.getPageNumber() != 0) {
            System.out.println("NE POSTOJI STRANA");
            throw new InvalidDataException("ne postoji strana");
        }
        return pageEmp.getContent().stream().
                map(dao -> employeeMapper.employeeToEmployeeDto(dao)).collect(Collectors.toList());
    }

    /**
     * Retrieves a page of filtered employees with pagination support.
     *
     * This method fetches a paginated list of employees filtered based on the
     * provided filter criteria.
     *
     * @param filterDto The filter criteria for employee search.
     * @param pageable The pagination information.
     * @return A page of DTOs representing the filtered employees.
     * @throws InvalidDataException if the requested page does not exist.
     */
    @Override
    public Page<EmployeeDto> pageFilterPaginate(EmployeeFilterDto filterDto, Pageable pageable) {
        Page<Employee> pageEmp = employeeRepository.findAll(EmployeeSpecification.filterEmployees(filterDto), pageable);
        if (pageEmp.getTotalPages() <= pageable.getPageNumber() && pageable.getPageNumber() != 0) {
            System.out.println("NE POSTOJI STRANA");
            throw new InvalidDataException("ne postoji strana");
        }
        Page<EmployeeDto> dtoPage = pageEmp.map(this::convertToDto);
        return dtoPage;
    }

    /**
     * Converts Employee to EmployeeDto
     *
     * @param employee Employee for conversion.
     * @return converted EmployeeDto.
     */
    private EmployeeDto convertToDto(Employee employee) {
        return employeeMapper.employeeToEmployeeDto(employee);
    }

    /**
     * Retrieves a list of filtered employees without pagination.
     *
     * This method fetches a list of employees filtered based on the provided
     * filter criteria.
     *
     * @param filterDto The filter criteria for employee search.
     * @param pageable The pagination information.
     * @return A list of DTOs representing the filtered employees.
     * @throws InvalidDataException if the requested page does not exist.
     */
    @Override
    public List<EmployeeDto> filterPaginate(EmployeeFilterDto filterDto, Pageable pageable) {
        Page<Employee> pageEmp = employeeRepository.findAll(EmployeeSpecification.filterEmployees(filterDto), pageable);
        System.out.print("-----------page no -------");
        System.out.println(pageable.getPageNumber());
        System.out.print("-----------total -------");
        System.out.println(pageEmp.getTotalPages());
        if (pageEmp.getTotalPages() <= pageable.getPageNumber() && pageable.getPageNumber() != 0) {
            System.out.println("NE POSTOJI STRANA");
            throw new InvalidDataException("ne postoji strana");
        }
        return pageEmp.getContent().stream().
                map(dao -> employeeMapper.employeeToEmployeeDto(dao)).collect(Collectors.toList());
    }

    /**
     * Retrieves all employees without pagination.
     *
     * This method fetches all employees from the database without pagination.
     *
     * @return A list of DTOs representing all employees.
     */
    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream().map(
                dao -> employeeMapper.employeeToEmployeeDto(dao)
        ).collect(Collectors.toList());
    }

    /**
     * Filters employees based on the provided filter criteria.
     *
     * This method filters employees based on the provided filter criteria.
     *
     * @param filterDto The filter criteria for employee search.
     * @return A list of DTOs representing the filtered employees.
     */
    @Override
    public List<EmployeeDto> filter(EmployeeFilterDto filterDto) {
        System.out.println("----------------------------------------------filter metoda---------------------------------------");
        System.out.println(filterDto);

        return employeeRepository.findAll(EmployeeSpecification.filterEmployees(filterDto)).stream().map(
                dao -> employeeMapper.employeeToEmployeeDto(dao)
        ).collect(Collectors.toList());
    }

    /**
     * Searches for employees based on the provided search term.
     *
     * This method searches for employees whose attributes match the provided
     * search term.
     *
     * @param term The search term.
     * @return A list of DTOs representing the employees matching the search
     * term.
     */
    @Override
    public List<EmployeeDto> search(String term) {
        return employeeRepository.findAll(EmployeeSpecification.searchEmployees(term.trim())).stream().map(
                dao -> employeeMapper.employeeToEmployeeDto(dao)
        ).collect(Collectors.toList());
    }

    /**
     * Retrieves the employee with the specified ID.
     *
     * This method retrieves the employee from the database with the specified
     * ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The DTO representing the retrieved employee.
     * @throws InvalidDataException if no employee with the specified ID is
     * found.
     */
    @Override
    public EmployeeDto findById(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidDataException("zaposleni sa datim id ne postoji.");
        }
        return employeeMapper.employeeToEmployeeDto(optional.get());
    }

    /**
     * Retrieves employees belonging to the specified department DTO.
     *
     * This method retrieves employees belonging to the department specified in
     * the provided department DTO.
     *
     * @param departmentDto The DTO representing the department.
     * @return A list of DTOs representing the employees belonging to the
     * department.
     * @throws InvalidDataException if the provided department DTO is invalid or
     * if no department with the specified ID is found.
     */
    @Override
    public List<EmployeeDto> findByDepartmentDto(DepartmentDto departmentDto) {
//        Optional<Department> optionalDepartment;
        if (departmentDto == null) {
            throw new InvalidDataException("nije prosledjen validan argument");
        } else {
            if (departmentDto.getId() == null) {
                throw new InvalidDataException("id katedre je null");// da li sad da pretrazi po imenu???
            }
            List<Employee> findByDepartmentId = employeeRepository.findByDepartmentId(departmentDto.getId());
            return findByDepartmentId.stream().map(dao -> employeeMapper.employeeToEmployeeDto(dao)).collect(Collectors.toList());
        }
    }

    /**
     * Retrieves employees belonging to the department with the specified ID.
     *
     * This method retrieves employees belonging to the department with the
     * specified ID.
     *
     * @param departmentId The ID of the department.
     * @return A list of DTOs representing the employees belonging to the
     * department.
     * @throws InvalidDataException if no department with the specified ID is
     * found.
     */
    @Override
    public List<EmployeeDto> findByDepartmentId(Long departmentId) {
//        Optional<Department> optionalDepartment;
        if (departmentId == null) {
            throw new InvalidDataException("nije prosledjen validan argument");
        } else {
            List<Employee> findByDepartmentId = employeeRepository.findByDepartmentId(departmentId);
            return findByDepartmentId.stream().map(dao -> employeeMapper.employeeToEmployeeDto(dao)).collect(Collectors.toList());
        }
    }

    /**
     * Retrieves employees with the specified status.
     *
     * This method retrieves employees with the specified status from the
     * database.
     *
     * @param status The status of the employees to retrieve.
     * @return A list of DTOs representing the employees with the specified
     * status.
     */
    @Override
    public List<EmployeeDto> findByStatus(Status status) {
        return employeeRepository.findByStatus(status).stream().map(dao -> employeeMapper.employeeToEmployeeDto(dao)).collect(Collectors.toList());
    }

    /**
     * Deletes the employee with the specified ID.
     *
     * This method deletes the employee from the database with the specified ID.
     *
     * @param id The ID of the employee to delete.
     * @throws InvalidDataException if no employee with the specified ID is
     * found.
     */
    @Override
    public void delete(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji zaposleni sa tim id");
        } else {
            employeeRepository.delete(optional.get());
        }
    }

    /**
     * Counts the number of employees based on academic title and department.
     *
     * This method counts the number of employees based on the specified
     * academic title ID and department ID. If either ID is null, all employees
     * are counted regardless of that parameter.
     *
     * @param academicTitleId The ID of the academic title.
     * @param departmentId The ID of the department.
     * @return The number of employees matching the specified criteria.
     */
    @Override
    public Long countByAcademicTitleAndDepartment(Long academicTitleId, Long departmentId) {
        if (departmentId == null) {
            departmentId = -1l;
        }
        if (academicTitleId == null) {
            academicTitleId = -1l;
        }
        return employeeRepository.count(EmployeeSpecification.countEmployees(academicTitleId, departmentId));
    }
}
