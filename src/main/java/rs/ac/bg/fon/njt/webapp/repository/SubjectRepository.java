/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.njt.webapp.domain.Subject;

/**
 *
 * @author aleks
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>,JpaSpecificationExecutor<Subject>{
    Optional<Subject> findByName(String name);
    List<Subject> findAll(Specification<Subject> specification);
}
