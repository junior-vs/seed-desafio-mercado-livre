package tech.vsj.mercadolivre.shared.form;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import tech.vsj.mercadolivre.persistence.model.Usuario;

@SuppressWarnings("serial")
public class UsuarioLogado implements UserDetails {

  private Usuario usuario;
  private User springUserDetails;

  public UsuarioLogado(Usuario usuario) {
    this.usuario = usuario;
    this.springUserDetails = new User(usuario.getUsername(), usuario.getPassword(), List.of());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.springUserDetails.getAuthorities();
  }

  @Override
  public String getPassword() {
    return this.springUserDetails.getPassword();
  }

  @Override
  public String getUsername() {
    return this.springUserDetails.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.springUserDetails.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.springUserDetails.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.springUserDetails.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return this.springUserDetails.isEnabled();
  }

  public Usuario get() {
    return usuario;
  }
}
