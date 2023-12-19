/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.converter;

import org.mapstruct.Mapper;
import rs.ac.bg.fon.njt.webapp.domain.EmployeeAcademicTitle;
import rs.ac.bg.fon.njt.webapp.domain.HistoryItemID;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeAcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.HistoryItemIdDto;

/**
 *
 * @author aleks
 */
@Mapper(componentModel = "spring")
public interface HistoryItemIdMapper {

    HistoryItemID historyItemIdDtoToHistoryItemId(HistoryItemIdDto historyItemIdDto);

    HistoryItemIdDto historyItemIdToHistoryItemIdDto(HistoryItemID historyItemID);
    
}
