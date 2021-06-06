package tech.vsj.mercadolivre.form;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fasterxml.jackson.annotation.JsonCreator;
import tech.vsj.mercadolivre.persistence.model.Usuario;
import tech.vsj.mercadolivre.validation.UniqueValue;

/**
 * 
 * @author Valdir Junior
 *
 */
public class UsuarioRequestForm {

  @NotBlank
  @Email
  @UniqueValue(domainClass = Usuario.class, fieldName = "userName")
  private String userName;

  @NotBlank
  @Size(min = 6)
  private String password;

  private LocalDate dtCriacao;



  @JsonCreator
  public UsuarioRequestForm(@NotBlank @Email String userName, @NotBlank String password) {
    this.userName = userName;
    this.password = password;
    this.dtCriacao = LocalDate.now();
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public LocalDate getDtCriacao() {
    return dtCriacao;
  }

  @Override
  public String toString() {
    return String.format("UsuarioRequestForm [dtCriacao=%s, password=%s, userName=%s]", dtCriacao,
        password, userName);
  }

  public Usuario map(PasswordEncoder passwordEncoder) {
    return new Usuario(this.userName, passwordEncoder.encode(this.password), this.dtCriacao);
  }



}
