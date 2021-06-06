package tech.vsj.mercadolivre.shared;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import tech.vsj.mercadolivre.persistence.model.Usuario;
import tech.vsj.mercadolivre.shared.form.UsuarioLogado;

@Configuration
public class  AppUserDetailsMapper implements UserDetailsMapper{

  @Override
  public UserDetails map(Object shouldBeASystemUser) {
    return new UsuarioLogado((Usuario)shouldBeASystemUser);
  }

}
