package tech.vsj.mercadolivre.validation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistValueValidator implements ConstraintValidator<ExistValue, Object> {

  private String fieldName;
  private Class<?> domainClass;

  @PersistenceContext
  private EntityManager manager;

  @Override
  public void initialize(ExistValue constraintAnnotation) {
    this.fieldName = constraintAnnotation.fieldName();
    this.domainClass = constraintAnnotation.domainClass();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    if (value == null)
      return true;


    var query = manager.createQuery(
        "SELECT 1 FROM " + this.domainClass.getName() + " WHERE " + this.fieldName + " =: value");
    query.setParameter("value", value);
    List<?> list = query.getResultList();
    return !list.isEmpty();
  }

}
