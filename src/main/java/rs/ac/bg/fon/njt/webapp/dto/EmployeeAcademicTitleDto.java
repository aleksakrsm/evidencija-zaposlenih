/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author aleks
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAcademicTitleDto {
    
    @NotNull(message = "id je obavezno polje")
    private HistoryItemIdDto historyItemIdDto;
    
//    @NotNull(message = "beginDate je obavezno polje")
//    @Past(message = "beginDate mora da bude u proslosti")
//    private LocalDate beginDate;
    
//    @NotNull(message = "endDate je obavezno polje")
//    @Past(message = "endDate mora da bude u proslosti")
    private LocalDate endDate;
    
}
