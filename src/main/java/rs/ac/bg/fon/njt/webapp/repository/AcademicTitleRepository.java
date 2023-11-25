/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.webapp.domain.AcademicTitle;

/**
 *
 * @author aleks
 */
public interface AcademicTitleRepository extends JpaRepository<AcademicTitle, Long>{
    Optional<AcademicTitle> findByName(String name);
}
