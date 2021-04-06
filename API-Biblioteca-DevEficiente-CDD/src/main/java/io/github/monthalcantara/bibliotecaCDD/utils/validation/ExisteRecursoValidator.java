package io.github.monthalcantara.bibliotecaCDD.utils.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ExisteRecursoValidator implements ConstraintValidator<ExisteRecurso, Object> {

    @PersistenceContext
    private EntityManager manager;
    private Class domainClass;
    private String fieldName;

    @Override
    public void initialize(ExisteRecurso constraintAnnotation) {
        this.domainClass = constraintAnnotation.domainClass();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) return true;

        String query = "Select 1 from " + this.domainClass.getSimpleName() + " where " + this.fieldName + "=:value";

        return !manager.createQuery(query)
                .setParameter("value", value)
                .getResultList()
                .isEmpty();

    }
}
