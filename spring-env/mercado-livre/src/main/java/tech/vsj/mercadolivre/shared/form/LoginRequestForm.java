package tech.vsj.mercadolivre.shared.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.fasterxml.jackson.annotation.JsonCreator;

public class LoginRequestForm {

  @Email
  @NotBlank
  private String userName;

  @NotBlank
  private String password;

  @JsonCreator
  public LoginRequestForm(@Email @NotBlank String userName, @NotBlank String password) {
    super();
    this.userName = userName;
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public UsernamePasswordAuthenticationToken build() {
    return new UsernamePasswordAuthenticationToken(userName, password);
  }



}
