package tech.vsj.mercadolivre.persistence.model;

import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@SuppressWarnings("serial")
public class Usuario implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IDUSER_SEQ")
  @SequenceGenerator(sequenceName = "IDUSER_SEQ", name = "IDUSER_SEQ", allocationSize = 1)
  private Long idUser;

  @NotBlank
  @Email
  @Column(unique = true)
  private String username;
  @NotBlank
  private String password;
  private LocalDate dtCriacao;

  public Usuario(@NotBlank @Email String username, @NotBlank String password, LocalDate dtCriacao) {
    this.username = username;
    this.password = password;
    this.dtCriacao = dtCriacao;
  }

  public Usuario() {
    // Auto-generated constructor stub
  }


  public String getPassword() {
    return password;
  }

  public LocalDate getDtCriacao() {
    return dtCriacao;
  }

  @Override
  public String toString() {
    return String.format("Usuario [userName=%s, password=%s, dtCriacao=%s]", username, password,
        dtCriacao);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return false;
  }

}
