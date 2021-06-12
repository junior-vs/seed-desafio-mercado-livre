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
public class Usuario {

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

  @Deprecated
  public Usuario() {
    // Auto-generated constructor stub
  }
  
  public Long getIdUser() {
    return idUser;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public LocalDate getDtCriacao() {
    return dtCriacao;
  }

  @Override
  public String toString() {
    return String
        .format("Usuario [idUser=%s, username=%s, dtCriacao=%s]", idUser, username, dtCriacao);
  }

}
