package tech.vsj.mercadolivre.validation;

import java.util.Set;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import tech.vsj.mercadolivre.form.ProdutoRequestForm;

public class ProibeCarecteristicasIguaisValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return ProdutoRequestForm.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors())
      return;
    
    ProdutoRequestForm requestForm = (ProdutoRequestForm) target;
    Set<String> nomeIguais = requestForm.buscaCaracteristicasIguas();
    if(!nomeIguais.isEmpty()) {
      errors.reject("caracteristicas",null, "Apresenta Caractiristicas iguais " + nomeIguais);
    }
    

  }

}
