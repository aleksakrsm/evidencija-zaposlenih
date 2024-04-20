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
 *
 * @author aleks
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private DepartmentRepository  departmentRepository;
    
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
    
    
    
    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        if(employeeDto.getId()!=null){
            Optional<Employee> optional = employeeRepository.findById(employeeDto.getId());
            if(optional.isPresent())
                throw new InvalidDataException("zaposleni sa datim id vec postoji.");
        }
        
        if(employeeDto.getAcademicTitle()!=null){
            if(employeeDto.getAcademicTitle().getId()== null)
                throw new InvalidDataException("ne postoji podatak o id akademske titule");
            Optional<AcademicTitle> optionalAcademicTitle = academicTitleRepository.findById(employeeDto.getAcademicTitle().getId());
            if(!optionalAcademicTitle.isPresent())
                throw new InvalidDataException("akademska titula sa prosledjenim id ne postoji");
        } else
            throw new InvalidDataException("ne postoji podatak o akademskoj tituli");
        
        if(employeeDto.getEducationTitle()!=null){
            if(employeeDto.getEducationTitle().getId()== null)
                throw new InvalidDataException("ne postoji podatak o id edukacione titule");
            Optional<EducationTitle> optionalEducationTitle = educationTitleRepository.findById(employeeDto.getEducationTitle().getId());
            if(!optionalEducationTitle.isPresent())
                throw new InvalidDataException("edukaciona titula sa prosledjenim id ne postoji");
        } else
            throw new InvalidDataException("ne postoji podatak o edukacionoj tituli");
        
        if(employeeDto.getDepartment()!=null){
            if(employeeDto.getDepartment().getId()== null)
                throw new InvalidDataException("ne postoji podatak o id katedre");
            Optional<Department> optionalDepartment = departmentRepository.findById(employeeDto.getDepartment().getId());
            if(!optionalDepartment.isPresent())
                throw new InvalidDataException("katedra sa prosledjenim id ne postoji");
        } else
            throw new InvalidDataException("ne postoji podatak o katedri");
        
        
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        return employeeMapper.employeeToEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto edit(EmployeeDto employeeDto) {
        if(employeeDto.getId()!=null){
            Optional<Employee> optional = employeeRepository.findById(employeeDto.getId());
            if(!optional.isPresent())
                throw new InvalidDataException("zaposleni sa datim id ne postoji.");
        }else 
            throw new InvalidDataException("ne postoji informacija o id zaposlenog");
        
        if(employeeDto.getAcademicTitle()!=null){
            if(employeeDto.getAcademicTitle().getId()== null)
                throw new InvalidDataException("ne postoji podatak o id akademske titule");
            Optional<AcademicTitle> optionalAcademicTitle = academicTitleRepository.findById(employeeDto.getAcademicTitle().getId());
            if(!optionalAcademicTitle.isPresent())
                throw new InvalidDataException("akademska titula sa prosledjenim id ne postoji");
        } else
            throw new InvalidDataException("ne postoji podatak o akademskoj tituli");
        
        if(employeeDto.getEducationTitle()!=null){
            if(employeeDto.getEducationTitle().getId()== null)
                throw new InvalidDataException("ne postoji podatak o id edukacione titule");
            Optional<EducationTitle> optionalEducationTitle = educationTitleRepository.findById(employeeDto.getEducationTitle().getId());
            if(!optionalEducationTitle.isPresent())
                throw new InvalidDataException("edukaciona titula sa prosledjenim id ne postoji");
        } else
            throw new InvalidDataException("ne postoji podatak o edukacionoj tituli");
        
        if(employeeDto.getDepartment()!=null){
            if(employeeDto.getDepartment().getId()== null)
                throw new InvalidDataException("ne postoji podatak o id katedre");
            Optional<Department> optionalDepartment = departmentRepository.findById(employeeDto.getDepartment().getId());
            if(!optionalDepartment.isPresent())
                throw new InvalidDataException("katedra sa prosledjenim id ne postoji");
        } else
            throw new InvalidDataException("ne postoji podatak o katedri");
        
        
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        return employeeMapper.employeeToEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public List<EmployeeDto> findAll(Pageable pageable){
        Page<Employee> pageEmp = employeeRepository.findAll(pageable);
        if(pageEmp.getTotalPages()<pageable.getPageNumber() && pageable.getPageNumber()!=0){
            System.out.println("NE POSTOJI STRANA");
            throw new InvalidDataException("ne postoji strana");
        }
        return pageEmp.getContent().stream().
                map(dao->employeeMapper.employeeToEmployeeDto(dao)).collect(Collectors.toList());
    }
    @Override
    public Page<EmployeeDto> pageFilterPaginate(EmployeeFilterDto filterDto,Pageable pageable){
        Page<Employee> pageEmp = employeeRepository.findAll(EmployeeSpecification.filterEmployees(filterDto),pageable);
        if(pageEmp.getTotalPages()<=pageable.getPageNumber() && pageable.getPageNumber()!=0){
            System.out.println("NE POSTOJI STRANA");
            throw new InvalidDataException("ne postoji strana");
        }
        Page<EmployeeDto> dtoPage = pageEmp.map(this::convertToDto);
        return dtoPage;
    }
    private EmployeeDto convertToDto(Employee employee) {
        return employeeMapper.employeeToEmployeeDto(employee);
    }
    @Override
    public List<EmployeeDto> filterPaginate(EmployeeFilterDto filterDto,Pageable pageable){
        Page<Employee> pageEmp = employeeRepository.findAll(EmployeeSpecification.filterEmployees(filterDto),pageable);
        System.out.print("-----------page no -------");
        System.out.println(pageable.getPageNumber());
        System.out.print("-----------total -------");
        System.out.println(pageEmp.getTotalPages());
        if(pageEmp.getTotalPages()<=pageable.getPageNumber() && pageable.getPageNumber()!=0){
            System.out.println("NE POSTOJI STRANA");
            throw new InvalidDataException("ne postoji strana");
        }
        return pageEmp.getContent().stream().
                map(dao->employeeMapper.employeeToEmployeeDto(dao)).collect(Collectors.toList());
    }
    
    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream().map(
                dao->employeeMapper.employeeToEmployeeDto(dao)
        ).collect(Collectors.toList());
    }
    @Override
    public List<EmployeeDto> filter(EmployeeFilterDto filterDto) {
        System.out.println("----------------------------------------------filter metoda---------------------------------------");
        System.out.println(filterDto);
        
        return employeeRepository.findAll(EmployeeSpecification.filterEmployees(filterDto)).stream().map(
                dao->employeeMapper.employeeToEmployeeDto(dao)
        ).collect(Collectors.toList());
    }
    
    @Override
    public List<EmployeeDto> search(String term){        
        return employeeRepository.findAll(EmployeeSpecification.searchEmployees(term.trim())).stream().map(
                dao->employeeMapper.employeeToEmployeeDto(dao)
        ).collect(Collectors.toList());
    }
    
    @Override
    public EmployeeDto findById(Long id) {
            Optional<Employee> optional = employeeRepository.findById(id);
            if(!optional.isPresent())
                throw new InvalidDataException("zaposleni sa datim id ne postoji.");
            return employeeMapper.employeeToEmployeeDto(optional.get());
    }

    @Override
    public List<EmployeeDto> findByDepartmentDto(DepartmentDto departmentDto) {
//        Optional<Department> optionalDepartment;
        if(departmentDto== null){
            throw new InvalidDataException("nije prosledjen validan argument");
        } else {
            if(departmentDto.getId() == null)
                throw new InvalidDataException("id katedre je null");// da li sad da pretrazi po imenu???
            List<Employee> findByDepartmentId = employeeRepository.findByDepartmentId(departmentDto.getId());
            return findByDepartmentId.stream().map(dao-> employeeMapper.employeeToEmployeeDto(dao)).collect(Collectors.toList());
        }
    }
    @Override
    public List<EmployeeDto> findByDepartmentId(Long departmentId) {
//        Optional<Department> optionalDepartment;
        if(departmentId== null){
            throw new InvalidDataException("nije prosledjen validan argument");
        } else {
            List<Employee> findByDepartmentId = employeeRepository.findByDepartmentId(departmentId);
            return findByDepartmentId.stream().map(dao-> employeeMapper.employeeToEmployeeDto(dao)).collect(Collectors.toList());
        }
    }

    @Override
    public List<EmployeeDto> findByStatus(Status status) {
        return employeeRepository.findByStatus(status).stream().map(dao->employeeMapper.employeeToEmployeeDto(dao)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (!optional.isPresent()) {
            throw new InvalidDataException("ne postoji zaposleni sa tim id");
        } else {
            employeeRepository.delete(optional.get());
        }
    }

    
    
    
            
    
}
