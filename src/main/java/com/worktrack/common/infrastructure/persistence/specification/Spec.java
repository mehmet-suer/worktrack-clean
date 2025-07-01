package com.worktrack.common.infrastructure.persistence.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

public class Spec {

    private Spec() {}

    @SafeVarargs
    public static <T> Specification<T> and(Specification<T>... specs) {
        return Arrays.stream(specs)
                .filter(Objects::nonNull)
                .reduce(Specification::and)
                .orElse(null);
    }

    @SafeVarargs
    public static <T> Specification<T> or(Specification<T>... specs) {
        return Arrays.stream(specs)
                .filter(Objects::nonNull)
                .reduce(Specification::or)
                .orElse(null);
    }

    public static <T> Specification<T> in(String field, List<?> values) {
        return (root, query, cb) -> {
            if (values == null || values.isEmpty()) {
                return cb.conjunction();
            }
            return root.get(field).in(values);
        };
    }

    public static <T> Specification<T> eq(String field, Object value) {
        return (root, query, cb) -> {
            if (value == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get(field), value);
        };
    }

    public static <T> Specification<T> contains(String field, Object value) {
        return (root, query, cb) -> {
            if (value == null) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get(field)), "%" + value + "%");
        };
    }


    @SafeVarargs
    private static <T> Specification<T> andWithCombine(Specification<T> ...specs) {
        return combine(Specification::and, specs);
    }


    @SafeVarargs
    private static <T> Specification<T> combine(BiFunction<Specification<T>, Specification<T>, Specification<T>> combiner,
                                                Specification<T>... specs) {
        return Arrays.stream(specs)
                .filter(Objects::nonNull)
                .reduce(combiner::apply)
                .orElse(null);
    }


    public static <T> Specification<T> not(Specification<T> spec) {
        return (root, query, cb) -> cb.not(spec.toPredicate(root, query, cb));
    }

    public static <T> Specification<T> orTwo(Specification<T> spec1, Specification<T> spec2) {
        return spec1.or(spec2);
    }

    public static <T> Specification<T> andTwo(Specification<T> spec1, Specification<T> spec2) {
        return spec1.and(spec2);
    }


    @FunctionalInterface
    interface Combiner<T> {
        Specification<T> combine(Specification<T> spec1, Specification<T> spec2);
    }



}
