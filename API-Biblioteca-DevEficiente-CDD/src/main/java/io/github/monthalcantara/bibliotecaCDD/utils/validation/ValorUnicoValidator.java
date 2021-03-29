package io.github.monthalcantara.bibliotecaCDD.utils.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {

    private String fieldName;
    private Class<?> domainClass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
        this.domainClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("Select 1 from " + domainClass.getSimpleName() + " where " + fieldName + " =: value").setParameter("value", value);

        List resultList = query.getResultList();

        return resultList.isEmpty();
    }
}
