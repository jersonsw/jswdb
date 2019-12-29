package io.inouty.jswdb.data.specifications;

import io.inouty.jswdb.data.entities.Movie;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

public class MoviesSearchSpecification implements Specification<Movie> {

    private Map<String, String> parameters;
    private Boolean isConjunction;

    public MoviesSearchSpecification(Map<String, String> parameters, Boolean isConjunction) {
        this.parameters = parameters;
        this.isConjunction = isConjunction;
    }

    @Override
    public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Predicate predicate = isConjunction ? builder.conjunction() : builder.disjunction();
        String year = parameters.get("year");
        if (year != null) {
            year = year.trim();
            if(!year.isEmpty()){
                predicate.getExpressions().add(builder.equal(root.get("year"), year));
            }
        }
        String title = parameters.get("title");
        if (title != null) {
            title = title.trim();
            if(!title.isEmpty()){
                predicate.getExpressions().add(builder.like(builder.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }
        }
        String certificate = parameters.get("certificate");
        if (certificate != null) {
            certificate = certificate.trim();
            if(!certificate.isEmpty()){
                predicate.getExpressions().add(builder.equal(root.get("certificate"), certificate.toLowerCase()));
            }
        }
        return predicate;
    }
}
