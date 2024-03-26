/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;
import rs.ac.bg.fon.njt.webapp.dto.SubjectDto;

/**
 *
 * @author aleks
 */
public interface SubjectService {

    SubjectDto save(SubjectDto subjectDto);
    SubjectDto edit(SubjectDto subjectDto);
    List<SubjectDto> findAll();
    List<SubjectDto> findAll(Pageable pageable);
    List<SubjectDto> search(String searchName);
    List<SubjectDto> filter(StudiesType studiesType);
    SubjectDto findById(Long id);
    SubjectDto findByName(String name);
    void delete(Long id);

}
