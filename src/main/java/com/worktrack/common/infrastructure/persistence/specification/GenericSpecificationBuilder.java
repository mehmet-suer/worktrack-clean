package com.worktrack.common.infrastructure.persistence.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GenericSpecificationBuilder<T> {

    public enum CombineType { AND, OR }

    private final List<Specification<T>> specifications = new ArrayList<>();
    private final CombineType combineType;

    public GenericSpecificationBuilder() {
        this(CombineType.AND);
    }

    public GenericSpecificationBuilder(CombineType combineType) {
        this.combineType = combineType;
    }

    public GenericSpecificationBuilder<T> add(Specification<T> specification) {
        if (specification != null) {
            specifications.add(specification);
        }
        return this;
    }

    public <V> GenericSpecificationBuilder<T> addIfPresent(V value, Function<V, Specification<T>> specFunction) {
        if (value != null && !(value instanceof String str && str.isBlank())) {
            Specification<T> spec = specFunction.apply(value);
            if (spec != null) {
                specifications.add(spec);
            }
        }
        return this;
    }

    public Specification<T> build() {
        if (specifications.isEmpty()) {
            return null;
        }

        Specification<T> result = specifications.get(0);
        for (int i = 1; i < specifications.size(); i++) {
            result = combineType == CombineType.AND
                    ? result.and(specifications.get(i))
                    : result.or(specifications.get(i));
        }

        return result;
    }
}
