package io.inouty.jswdb.data.specifications;

import io.inouty.jswdb.data.entities.Movie;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class MoviesSpecifications {

    public static Specification<Movie> searchSpecification(Map<String, String> parameters, Boolean isConjunction){
        return new MoviesSearchSpecification(parameters, isConjunction);
    }
}
