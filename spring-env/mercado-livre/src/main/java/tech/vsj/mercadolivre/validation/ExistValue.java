package tech.vsj.mercadolivre.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD, METHOD, PARAMETER, TYPE_USE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ExistValueValidator.class)
@Documented
public @interface ExistValue {

  String fieldName();

  Class<?> domainClass();

  String message() default "valor não existe na base";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @Target({FIELD, METHOD, PARAMETER, TYPE_USE, ANNOTATION_TYPE})
  @Retention(RUNTIME)
  @Documented
  @interface List {
    ExistValue[] value();
  }


}
