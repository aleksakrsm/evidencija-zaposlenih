/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.njt.webapp.converter.EmployeeAcademicTitleMapper;
import rs.ac.bg.fon.njt.webapp.converter.HistoryItemIdMapper;
import rs.ac.bg.fon.njt.webapp.domain.Employee;
import rs.ac.bg.fon.njt.webapp.domain.EmployeeAcademicTitle;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeAcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.HistoryItemIdDto;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.AcademicTitleRepository;
import rs.ac.bg.fon.njt.webapp.repository.EmployeeAcademicTitleRepository;
import rs.ac.bg.fon.njt.webapp.repository.EmployeeRepository;
import rs.ac.bg.fon.njt.webapp.service.EmployeeAcademicTitleService;

/**
 * Service implementation for managing employee academic titles. Provides
 * methods for saving, editing, finding, and deleting employee academic titles.
 *
 * This class interacts with the database through {@link EmployeeAcademicTitleRepository},
 * {@link EmployeeRepository}, and {@link AcademicTitleRepository}, and utilizes
 * {@link EmployeeAcademicTitleMapper} and {@link HistoryItemIdMapper} for
 * DTO-to-entity mapping.
 *
 * @see EmployeeAcademicTitleService
 * @see EmployeeAcademicTitleRepository
 * @see EmployeeRepository
 * @see AcademicTitleRepository
 * @see EmployeeAcademicTitleMapper
 * @see HistoryItemIdMapper
 * @see EmployeeAcademicTitleDto
 * @see EmployeeAcademicTitle
 * @see InvalidDataException
 *
 * This implementation ensures that:
 * <ul>
 * <li>An employee academic title cannot be saved if the associated employee or
 * academic title does not exist.
 * <li>An employee academic title cannot be saved if its ID, employee ID, or
 * academic title ID is null.
 * <li>An employee academic title cannot be saved if it already exists.
 * <li>An employee academic title cannot be edited if it does not exist or if
 * the associated employee or academic title does not exist.
 * <li>An employee academic title cannot be deleted if it does not exist.
 * </ul>
 *
 * @author aleks
 */
@Service
public class EmployeeAcademicTitleServiceImpl implements EmployeeAcademicTitleService {

    @Autowired
    private EmployeeAcademicTitleRepository historyItemRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AcademicTitleRepository academicTitleRepository;

    @Autowired
    private EmployeeAcademicTitleMapper historyItemMapper;

    @Autowired
    private HistoryItemIdMapper historyItemIdMapper;

    /**
     * Saves a new employee academic title.
     *
     * @param historyItemDto The DTO of the employee academic title to save.
     * @return The saved EmployeeAcademicTitleDto.
     * @throws InvalidDataException if the historyItemDto is null, or if the
     * associated employee or academic title does not exist, or if the ID,
     * employee ID, or academic title ID is null, or if the employee already has
     * the same academic title.
     */
    @Override
    public EmployeeAcademicTitleDto save(EmployeeAcademicTitleDto historyItemDto) {
        if (historyItemDto == null) {
            throw new InvalidDataException("historyItemDto ne sme da bude null");
        }

        EmployeeAcademicTitle historyItem = historyItemMapper.historyItemDtoToHistoryItem(historyItemDto);

        if (historyItem.getHistoryItemID() == null) {
            throw new InvalidDataException("id je  null");
        }

        if (historyItem.getHistoryItemID().getAcademicTitle() == null || historyItem.getHistoryItemID().getEmployee() == null) {
            throw new InvalidDataException("deo id je  null");
        }

        if (historyItemDto.getHistoryItemIdDto().getBeginDate() == null) {
            throw new InvalidDataException("beginDate je obavezno polje");
        }

        if (academicTitleRepository.findById(historyItem.getHistoryItemID().getAcademicTitle().getId()).isEmpty()) {
            throw new InvalidDataException("ne postoji akademska titula sa prosledjenim id");
        }

        if (employeeRepository.findById(historyItem.getHistoryItemID().getEmployee().getId()).isEmpty()) {
            throw new InvalidDataException("ne postoji zaposleni sa prosledjenim id");
        }

        Optional<EmployeeAcademicTitle> optionalHistoryItem = historyItemRepository.findById(historyItem.getHistoryItemID());
        // proveriti da li postoje titula i employee i sta bi se desilo ako ne postoje, a to nije provereno prethodno

        if (optionalHistoryItem.isPresent()) {
            throw new InvalidDataException("zaposleni vec ima taj historyItem");
        }

        return historyItemMapper.historyItemToHistoryItemDto(historyItemRepository.save(historyItem));
    }

    /**
     * Saves a list of new employee academic titles.
     *
     * @param historyItemsDto The list of DTOs of employee academic titles to
     * save.
     * @return The list of saved EmployeeAcademicTitleDto.
     * @throws InvalidDataException if any of the historyItemDto is null, or if
     * the associated employee or academic title does not exist, or if the ID,
     * employee ID, or academic title ID is null, or if any of the employees
     * already have the same academic title.
     */
    @Override
    @Transactional
    public List<EmployeeAcademicTitleDto> saveAll(List<EmployeeAcademicTitleDto> historyItemsDto) {

        List<EmployeeAcademicTitleDto> list = new ArrayList<>();

        for (EmployeeAcademicTitleDto historyItemDto : historyItemsDto) {
            if (historyItemDto == null) {
                throw new InvalidDataException("historyItemDto ne sme da bude null");
            }
            EmployeeAcademicTitle historyItem = historyItemMapper.historyItemDtoToHistoryItem(historyItemDto);
            if (historyItem.getHistoryItemID() == null) {
                throw new InvalidDataException("id je  null");
            }
            if (historyItem.getHistoryItemID().getAcademicTitle() == null || historyItem.getHistoryItemID().getEmployee() == null || historyItem.getHistoryItemID().getBeginDate() == null) {
                throw new InvalidDataException("deo id je  null");
            }

            // proveriti da li postoje titula i employee i sta bi se desilo ako ne postoje, a to nije provereno prethodno
            if (academicTitleRepository.findById(historyItem.getHistoryItemID().getAcademicTitle().getId()).isEmpty()) {
                throw new InvalidDataException("ne postoji akademska titula sa prosledjenim id");
            }

            if (employeeRepository.findById(historyItem.getHistoryItemID().getEmployee().getId()).isEmpty()) {
                throw new InvalidDataException("ne postoji zaposleni sa prosledjenim id");
            }

            Optional<EmployeeAcademicTitle> optionalHistoryItem = historyItemRepository.findById(historyItem.getHistoryItemID());
            if (optionalHistoryItem.isPresent()) {
                throw new InvalidDataException("zaosleni vec ima taj historyItem");
            }
            list.add(historyItemMapper.historyItemToHistoryItemDto(historyItemRepository.save(historyItem)));
        }
        return list;
    }

    /**
     * Edits an existing employee academic title.
     *
     * @param historyItemDto The DTO of the employee academic title to edit.
     * @return The edited EmployeeAcademicTitleDto.
     * @throws InvalidDataException if the historyItemDto is null, or if the
     * associated employee or academic title does not exist, or if the ID,
     * employee ID, or academic title ID is null, or if the employee does not
     * have the specified academic title.
     */
    @Override
    public EmployeeAcademicTitleDto edit(EmployeeAcademicTitleDto historyItemDto) {
        if (historyItemDto == null) {
            throw new InvalidDataException("historyItemDto ne sme da bude null");
        }
        EmployeeAcademicTitle historyItem = historyItemMapper.historyItemDtoToHistoryItem(historyItemDto);
        if (historyItem.getHistoryItemID() == null) {
            throw new InvalidDataException("id je  null");
        }
        if (historyItem.getHistoryItemID().getAcademicTitle() == null || historyItem.getHistoryItemID().getEmployee() == null) {
            throw new InvalidDataException("deo id je  null");
        }

        // proveriti da li postoje titula i employee i sta bi se desilo ako ne postoje, a to nije provereno prethodno
        if (academicTitleRepository.findById(historyItem.getHistoryItemID().getAcademicTitle().getId()).isEmpty()) {
            throw new InvalidDataException("ne postoji akademska titula sa prosledjenim id");
        }

        if (employeeRepository.findById(historyItem.getHistoryItemID().getEmployee().getId()).isEmpty()) {
            throw new InvalidDataException("ne postoji zaposleni sa prosledjenim id");
        }

        Optional<EmployeeAcademicTitle> optionalHistoryItem = historyItemRepository.findById(historyItem.getHistoryItemID());
        if (optionalHistoryItem.isEmpty()) {
            throw new InvalidDataException("zaposleni nema ima taj historyItem");
        }

        return historyItemMapper.historyItemToHistoryItemDto(historyItemRepository.save(historyItem));
    }

    /**
     * Finds an employee academic title by its ID.
     *
     * @param idDto The DTO containing the IDs of the employee and academic
     * title.
     * @return The EmployeeAcademicTitleDto with the given IDs.
     * @throws InvalidDataException if the idDto is null, or if no employee
     * academic title exists with the given IDs.
     */
    @Override
    public EmployeeAcademicTitleDto findById(HistoryItemIdDto idDto) {
        if (idDto == null) {
            throw new InvalidDataException("idDto je null");
        }
        Optional<EmployeeAcademicTitle> historyItemOptional = historyItemRepository.findById(historyItemIdMapper.historyItemIdDtoToHistoryItemId(idDto));
        if (historyItemOptional.isEmpty()) {
            throw new InvalidDataException("ne postoji sa tim id");
        }
        return historyItemMapper.historyItemToHistoryItemDto(historyItemOptional.get());
    }

    /**
     * Finds all employee academic titles associated with a specific employee.
     *
     * @param employeeDto The DTO of the employee.
     * @return A list of EmployeeAcademicTitleDto associated with the given
     * employee.
     * @throws InvalidDataException if the employeeDto is null, or if the
     * employee ID is null, or if no employee exists with the given employee ID.
     */
    @Override
    public List<EmployeeAcademicTitleDto> findByEmployee(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            throw new InvalidDataException("prosledjeni argument je null");
        }
        if (employeeDto.getId() == null) {
            throw new InvalidDataException("id zaposlenog je null");
        }

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeDto.getId());
        if (optionalEmployee.isEmpty()) {
            throw new InvalidDataException("zaposleni sa tim id ne postoji");
        }

        return historyItemRepository.findByEmployeeIdOrderByBeginDateAsc(employeeDto.getId()).stream().
                map(dao -> historyItemMapper.historyItemToHistoryItemDto(dao)).
                collect(Collectors.toList());
    }

    /**
     * Finds all employee academic titles associated with a specific employee.
     *
     * @param empId The ID of the employee.
     * @return A list of EmployeeAcademicTitleDto associated with the given
     * employee ID.
     * @throws InvalidDataException if the employee ID is null, or if no
     * employee exists with the given employee ID.
     */
    @Override
    public List<EmployeeAcademicTitleDto> findByEmployee(Long empId) {
        if (empId == null) {
            throw new InvalidDataException("id zaposlenog je null");
        }

        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
        if (optionalEmployee.isEmpty()) {
            throw new InvalidDataException("zaposleni sa tim id ne postoji");
        }

        return historyItemRepository.findByEmployeeIdOrderByBeginDateAsc(empId).stream().
                map(dao -> historyItemMapper.historyItemToHistoryItemDto(dao)).
                collect(Collectors.toList());
//        return historyItemRepository.findByEmployeeIdOrderByBeginDateAsc(empId).stream().
//                map(dao -> {
//                    System.out.println(dao);
//                    System.out.println(historyItemMapper.historyItemToHistoryItemDto(dao).getHistoryItemIdDto());
//                    return historyItemMapper.historyItemToHistoryItemDto(dao);
//                }).
//                collect(Collectors.toList());
    }

    /**
     * Deletes an history item by its ID.
     *
     * @param idDto The ID of the history item to delete.
     * @throws InvalidDataException if idDto is null or no history item exists
     * with the given ID.
     */
    @Override
    public void delete(HistoryItemIdDto idDto) {
        if (idDto == null) {
            throw new InvalidDataException("idDto je null");
        }
        Optional<EmployeeAcademicTitle> historyItemOptional = historyItemRepository.findById(historyItemIdMapper.historyItemIdDtoToHistoryItemId(idDto));
        if (historyItemOptional.isEmpty()) {
            throw new InvalidDataException("ne postoji sa tim id");
        }
        historyItemRepository.delete(historyItemOptional.get());
    }

    /**
     * Saves changes to employee academic titles and deletes specified titles.
     *
     * This method is transactional and ensures the consistency of the data. It
     * first deletes the employee academic titles specified in the
     * deleteItemsDto list, then saves the employee academic titles specified in
     * the historyItemsDto list.
     *
     * Note: This method does not perform validation of academic title
     * intervals.
     *
     * @param historyItemsDto The list of DTOs of employee academic titles to
     * save.
     * @param deleteItemsDto The list of DTOs of employee academic titles to
     * delete.
     * @return Null, as the method does not return any meaningful data after
     * saving changes.
     * @throws InvalidDataException if any of the employee academic titles to
     * delete do not exist, or if any of the employee academic titles to save
     * are invalid.
     */
    @Override
    @Transactional
    public List<EmployeeAcademicTitleDto> saveChanges(List<EmployeeAcademicTitleDto> historyItemsDto, List<EmployeeAcademicTitleDto> deleteItemsDto) {
//        if(!areIntervalsValid(historyItemsDto,deleteItemsDto)){
//            throw new InvalidDataException("academic titles history is not valid!");
//        }
        for (EmployeeAcademicTitleDto deleteItem : deleteItemsDto) {
            historyItemRepository.deleteById(historyItemIdMapper.historyItemIdDtoToHistoryItemId(deleteItem.getHistoryItemIdDto()));
        }
        for (EmployeeAcademicTitleDto itemDto : historyItemsDto) {
            historyItemRepository.save(historyItemMapper.historyItemDtoToHistoryItem(itemDto));
        }
        return null;
    }

//    private boolean areIntervalsValid(List<EmployeeAcademicTitleDto> historyItemsDto, List<EmployeeAcademicTitleDto> deleteItemsDto) {
//        List<EmployeeAcademicTitle> finalList = historyItemRepository.findAll().stream()..collect(Collectors.toList());
//    }
}
