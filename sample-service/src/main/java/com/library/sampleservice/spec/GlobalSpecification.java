package com.library.sampleservice.spec;

import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.List;

/**
 * Especificações globais que podem ser usadas por qualquer entidade
 *
 * @author Felipe Martins
 */
public class GlobalSpecification {

    /**
     * Compara dois objetos
     * @param field nome do campo que será comparado
     * @param value
     * @param <T>
     * @param <AT>
     * @return
     */
    public static <T, AT> Specification<T> equals(final String field, final AT value) {
        return (root, query, cb) -> cb.equal(root.<AT>get(field), value);
    }

    public static <T, AT> Specification<T> in(final String field, final List<AT> values) {
        return (root, query, cb) -> cb.in(root.<AT>get(field)).in(values);
    }

    public static <T> Specification<T> dateFieldNotNull(final String field) {
        return (root, query, cb) -> cb.isNotNull(root.<Date>get(field));
    }

    public static <T> Specification<T> integerFieldBetween(final String field, final Integer initialValue, final Integer finalValue) {
        return (root, query, cb) -> cb.between(root.<Integer>get(field), initialValue, finalValue);
    }

    public static <T> Specification<T> booleanFieldEqual(final String field, final Enum value) {
        return (root, query, cb) -> cb.equal(root.<Integer>get(field), value);
    }

    public static <T> Specification<T> dateFieldBetween(final String field, final Date initialValue, final Date finalValue) {
        return (root, query, cb) -> cb.between(root.<Date>get(field), initialValue, finalValue);
    }

    public static <T> Specification<T> stringFieldAnywhere(final String field, final String value) {
        return (root, query, cb) -> cb.like(root.<String>get(field), "%" + value + "%");
    }

    public static <T> Specification<T> booleanFieldEqual(final String field, final Boolean value) {
        return (root, query, cb) -> cb.equal(root.<Boolean>get(field), value);
    }

}
