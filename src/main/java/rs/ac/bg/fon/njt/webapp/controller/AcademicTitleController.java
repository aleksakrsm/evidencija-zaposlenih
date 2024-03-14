/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.controller;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.service.AcademicTitleService;

/**
 *
 * @author aleks
 */
@RestController
@RequestMapping("/academicTitle")
public class AcademicTitleController {

    @Autowired
    private AcademicTitleService academicTitleService;

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(academicTitleService.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(academicTitleService.findById(id));
    }
    
    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody AcademicTitleDto academicTitleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(academicTitleService.save(academicTitleDto));
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity getByName(@PathVariable String name) {
        return new ResponseEntity(academicTitleService.findByName(name), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
            academicTitleService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("izbrisana je titula sa id: " + id);
    }
    
    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody AcademicTitleDto dto) {
            return ResponseEntity.status(HttpStatus.OK).body(academicTitleService.edit(dto));
    }
    
//    @GetMapping("/getAll")
//    public ResponseEntity getAll() {
//        System.out.println("getall");
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(academicTitleService.findAll());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
//    }   

//    @GetMapping("/get/{id}")
////    http://localhost:8080/webapp/academicTitle/get/2
//    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
//        try {
//            return ResponseEntity.status(HttpStatus.FOUND).body(academicTitleService.findById(id));
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
//    }
//    @GetMapping("/get")
////    http://localhost:8080/webapp/academicTitle/get?id=2
//    public ResponseEntity getById(@RequestParam Long id) {
//        try {
//            return ResponseEntity.status(HttpStatus.CREATED).body(academicTitleService.findById(id));
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
//    }
//    @PostMapping("/save")
//    public ResponseEntity save(@Valid @RequestBody AcademicTitleDto academicTitleDto) {
//        try {
//            return ResponseEntity.status(HttpStatus.CREATED).body(academicTitleService.save(academicTitleDto));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            System.out.println(ex.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
//    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity delete(@PathVariable Long id) {
//        try {
//            academicTitleService.delete(id);
//            return ResponseEntity.status(HttpStatus.OK).body("izbrisana je titula sa id: " + id);
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
//    }

//    @PutMapping("/update")
//    public ResponseEntity update(@Valid @RequestBody AcademicTitleDto dto) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(academicTitleService.edit(dto));
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
//    }

//    @GetMapping("/proba")
//    public String proba() {
//        System.out.println("proba breeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee------------------------");
//        return "proba";
//    }

}
