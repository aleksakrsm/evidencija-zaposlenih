/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service;

import java.util.List;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;

/**
 *
 * @author aleks
 */
public interface AcademicTitleService {

    AcademicTitleDto save(AcademicTitleDto academicTitleDto);
    AcademicTitleDto edit(AcademicTitleDto academicTitleDto);
    List<AcademicTitleDto> findAll();
    AcademicTitleDto findById(Long id);
    AcademicTitleDto findByName(String name);
    void delete(Long id);
    
    
//    AcademicTitleDto save(AcademicTitleDto academicTitleDto) throws Exception;
//    AcademicTitleDto edit(AcademicTitleDto academicTitleDto) throws Exception;
//    List<AcademicTitleDto> findAll();
//    AcademicTitleDto findById(Long id) throws Exception;
//    AcademicTitleDto findByName(String name);
//    void delete(Long id) throws Exception;

}
