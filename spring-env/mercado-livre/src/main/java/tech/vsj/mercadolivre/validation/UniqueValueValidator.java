package tech.vsj.mercadolivre.validation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

  private String fieldName;
  private Class<?> domainClass;

  @PersistenceContext
  private EntityManager manager;

  @Override
  public void initialize(UniqueValue constraintAnnotation) {
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

    Assert.state(list.size() <= 1, "Foi encontrado mais de um " + this.domainClass
        + "com o memso atributo " + this.fieldName + "value");

    return list.isEmpty();


  }

}
