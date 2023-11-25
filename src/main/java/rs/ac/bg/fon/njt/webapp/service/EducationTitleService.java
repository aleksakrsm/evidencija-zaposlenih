/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service;

import java.util.List;
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;

/**
 *
 * @author aleks
 */
public interface EducationTitleService {

    EducationTitleDto save(EducationTitleDto educationTitleDto);
    EducationTitleDto edit(EducationTitleDto educationTitleDto);
    List<EducationTitleDto> findAll();
    EducationTitleDto findById(Long id);
    EducationTitleDto findByName(String name);
    void delete(Long id);

}
