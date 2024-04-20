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
            if (optionalHistoryItem.isPresent()) {
                throw new InvalidDataException("zaosleni vec ima taj historyItem");
            }
            list.add(historyItemMapper.historyItemToHistoryItemDto(historyItemRepository.save(historyItem)));
        }
        return list;
    }

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

    @Override
    @Transactional
    public List<EmployeeAcademicTitleDto> saveChanges(List<EmployeeAcademicTitleDto> historyItemsDto, List<EmployeeAcademicTitleDto> deleteItems) {
        for (EmployeeAcademicTitleDto deleteItem : deleteItems) {
            historyItemRepository.deleteById(historyItemIdMapper.historyItemIdDtoToHistoryItemId(deleteItem.getHistoryItemIdDto()));
        }
        for (EmployeeAcademicTitleDto itemDto : historyItemsDto) {
            historyItemRepository.save(historyItemMapper.historyItemDtoToHistoryItem(itemDto));
        }
        return null;
    }

}
