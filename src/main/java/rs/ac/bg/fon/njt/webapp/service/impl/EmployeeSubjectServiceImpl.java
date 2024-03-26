/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.webapp.converter.EmployeeSubjectMapper;
import rs.ac.bg.fon.njt.webapp.domain.EmployeeSubject;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeSubjectDto;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.EmployeeRepository;
import rs.ac.bg.fon.njt.webapp.repository.EmployeeSubjectRepository;
import rs.ac.bg.fon.njt.webapp.repository.SubjectRepository;
import rs.ac.bg.fon.njt.webapp.service.EmployeeSubjectService;

/**
 *
 * @author aleks
 */
@Service
public class EmployeeSubjectServiceImpl implements EmployeeSubjectService{

    @Autowired
    private EmployeeSubjectRepository empSubRep;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private EmployeeSubjectMapper empSubMapper;
    
    @Override
    public EmployeeSubjectDto save(EmployeeSubjectDto dto) {
        if (dto == null) {
            throw new InvalidDataException("EmployeeSubjectDto ne sme da bude null");
        }

        EmployeeSubject employeeSubject = empSubMapper.employeeSubjectDtoToEmployeeSubject(dto);

        if (employeeSubject.getId() == null) {
            throw new InvalidDataException("id je  null");
        }

        if (employeeSubject.getId().getEmployee() == null || employeeSubject.getId().getSubject()== null) {
            throw new InvalidDataException("deo slozenog kljuca je  null");
        }

        if (employeeRepository.findById(employeeSubject.getId().getEmployee().getId()).isEmpty()) {
            throw new InvalidDataException("ne postoji Employee sa prosledjenim id");
        }
        if (subjectRepository.findById(employeeSubject.getId().getSubject().getId()).isEmpty()) {
            throw new InvalidDataException("ne postoji Subject sa prosledjenim id");
        }
        // sad znam da postoje i subject i employee sigurno
//        sad ova metoda samo cuva novu stavku, a mozda bi mogla da cuva i nove stavke i izmenjene 
//                                                ali bi onda mozda bilo exceptiona zbog PK. u tom sl bi se rollback transakcija i top
        Optional<EmployeeSubject> optional = empSubRep.findById(employeeSubject.getId());

        if (optional.isPresent()) {
            throw new InvalidDataException("zaposleni vec ima taj subject");
        }

        return empSubMapper.employeeSubjectToEmployeeSubjectDto(empSubRep.save(employeeSubject));
    }

//    @Override
//    public List<EmployeeSubjectDto> saveAll(List<EmployeeSubjectDto> list) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public EmployeeSubjectDto edit(EmployeeSubjectDto dto) {
        if (dto == null) {
            throw new InvalidDataException("EmployeeSubjectDto ne sme da bude null");
        }

        EmployeeSubject employeeSubject = empSubMapper.employeeSubjectDtoToEmployeeSubject(dto);

        if (employeeSubject.getId() == null) {
            throw new InvalidDataException("id je  null");
        }

        if (employeeSubject.getId().getEmployee() == null || employeeSubject.getId().getSubject()== null) {
            throw new InvalidDataException("deo slozenog kljuca je  null");
        }

        if (employeeRepository.findById(employeeSubject.getId().getEmployee().getId()).isEmpty()) {
            throw new InvalidDataException("ne postoji Employee sa prosledjenim id");
        }
        if (subjectRepository.findById(employeeSubject.getId().getSubject().getId()).isEmpty()) {
            throw new InvalidDataException("ne postoji Subject sa prosledjenim id");
        }
        // sad znam da postoje i subject i employee sigurno
        Optional<EmployeeSubject> optional = empSubRep.findById(employeeSubject.getId());

        if (optional.isEmpty()) {
            throw new InvalidDataException("zaposleni nema taj subject. subject prvo morabiti unet u bazu da bi bio izmenjen.");
        }

        if(optional.get().getClassType().name().equals(dto.getClassType().name())){
            return dto; ///nista nije ni izmenjeno. ne treba zvati repozitorijum
        }
        return empSubMapper.employeeSubjectToEmployeeSubjectDto(empSubRep.save(employeeSubject));
    }

    @Override
    public List<EmployeeSubjectDto> findByEmployee(Long id) {
        if(id==null){
            throw new InvalidDataException("id == null");
        }
        if (employeeRepository.findById(id).isEmpty()) {
            throw new InvalidDataException("ne postoji Employee sa prosledjenim id");
        }
        List<EmployeeSubjectDto> employeeSubjectDtos = 
                empSubRep.findByIdEmployeeId(id).stream().map(dao->empSubMapper.employeeSubjectToEmployeeSubjectDto(dao)).collect(Collectors.toList());
        
        return employeeSubjectDtos;
    }

    @Override
    public List<EmployeeSubjectDto> findBySubject(Long id) {
        if(id==null){
            throw new InvalidDataException("id == null");
        }
        if (subjectRepository.findById(id).isEmpty()) {
            throw new InvalidDataException("ne postoji Subject sa prosledjenim id");
        }
        List<EmployeeSubjectDto> employeeSubjectDtos = 
                empSubRep.findByIdSubjectId(id).stream().map(dao->empSubMapper.employeeSubjectToEmployeeSubjectDto(dao)).collect(Collectors.toList());
        
        return employeeSubjectDtos;
    }

    @Override
    public void delete(EmployeeSubjectDto dto) {
        if (dto == null) {
            throw new InvalidDataException("EmployeeSubjectDto ne sme da bude null");
        }

        EmployeeSubject employeeSubject = empSubMapper.employeeSubjectDtoToEmployeeSubject(dto);

        if (employeeSubject.getId() == null) {
            throw new InvalidDataException("id je  null");
        }

        if (employeeSubject.getId().getEmployee() == null || employeeSubject.getId().getSubject()== null) {
            throw new InvalidDataException("deo slozenog kljuca je  null");
        }

        if (employeeRepository.findById(employeeSubject.getId().getEmployee().getId()).isEmpty()) {
            throw new InvalidDataException("ne postoji Employee sa prosledjenim id");
        }
        if (subjectRepository.findById(employeeSubject.getId().getSubject().getId()).isEmpty()) {
            throw new InvalidDataException("ne postoji Subject sa prosledjenim id");
        }
        empSubRep.delete(employeeSubject);
    }
    
}
