/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository.specifications;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import rs.ac.bg.fon.njt.webapp.domain.Subject;
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;
import rs.ac.bg.fon.njt.webapp.dto.SubjectFilterDto;

/**
 *
 * @author aleks
 */
public class SubjectSpecification {


    public static Specification<Subject> filter(SubjectFilterDto filterDto) {
        return ((root, query, criteriaBuilder) -> {
            Predicate predicateStudiesType = criteriaBuilder.equal(root.get("studiestype"), filterDto.getStudiesType());
            return predicateStudiesType;
        });
    }

    public static Specification<Subject> search(String searchTerm) {
        return ((root, query, criteriaBuilder) -> {
            Predicate predicateName = criteriaBuilder.like(root.get("name"), likePattern(searchTerm.trim()));
            return predicateName;
        });
    }

    private static String likePattern(String term) {
        return "%" + term + "%";
    }

}
