/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository.specifications;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import rs.ac.bg.fon.njt.webapp.domain.Employee;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeFilterDto;

/**
 *
 * @author aleks
 */
public class EmployeeSpecification {

    public static Specification<Employee> filterEmployees(EmployeeFilterDto filterDto) {
        return ((root, query, criteriaBuilder) -> {
            Predicate academicTitlePredicate
                    = criteriaBuilder.like(root.get("academicTitle"),
                            (filterDto.getAcademicTitleId() == -1l) ? likePattern("") : filterDto.getAcademicTitleId().intValue() + "");
//                            (filterDto.getAcademicTitleId() == null) ? likePattern("") : filterDto.getAcademicTitleId().intValue() + "");
            Predicate educationTitlePredicate
                    = criteriaBuilder.like(root.get("educationTitle"),
                            (filterDto.getEducationTitleId() == -1l) ? likePattern("") : filterDto.getEducationTitleId().intValue() + "");
            Predicate departmentPredicate
                    = criteriaBuilder.like(root.get("department"),
                            (filterDto.getDepartmentId() == -1l) ? likePattern("") : filterDto.getDepartmentId().intValue() + "");
            Predicate statusPredicate
                    = criteriaBuilder.equal(root.get("status"), filterDto.getStatus());
            return criteriaBuilder.and(academicTitlePredicate, educationTitlePredicate, departmentPredicate, statusPredicate);
        });
    }
    public static Specification<Employee> searchEmployees(String term) {
        return ((root, query, criteriaBuilder) -> {
            Predicate firstnamePredicate
                    = criteriaBuilder.like(root.get("firstname"),likePattern(term));
            Predicate lastnamePredicate
                    = criteriaBuilder.like(root.get("lastname"),likePattern(term));
            Expression<String> nameSpaceExpression = criteriaBuilder.concat(root.get("firstname"), " ");
            Expression<String> fullnameExpression = criteriaBuilder.concat(nameSpaceExpression,root.get("lastname"));
            Predicate fullNamePredicate = criteriaBuilder.like(fullnameExpression, likePattern(term));
            return criteriaBuilder.or(firstnamePredicate, lastnamePredicate,fullNamePredicate);
        });
    }

    private static String likePattern(String term) {
        return "%" + term + "%";
    }
}
