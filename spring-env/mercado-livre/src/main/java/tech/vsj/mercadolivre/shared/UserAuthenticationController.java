package tech.vsj.mercadolivre.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vsj.mercadolivre.shared.form.LoginRequestForm;

@RestController
@RequestMapping("/api/auth")
public class UserAuthenticationController {
  
  private static final Logger log = LoggerFactory.getLogger(UserAuthenticationController.class);
  
  @Autowired
  private AuthenticationManager autManager;
  
  @Autowired
  private TokenManager tokenManager;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> authenticate(@RequestBody LoginRequestForm loginInfo) {

    UsernamePasswordAuthenticationToken authenticationToken = loginInfo.build();
    try {
      Authentication authentication = autManager.authenticate(authenticationToken);
      String jwt = tokenManager.generateToken(authentication);
      return ResponseEntity.ok(jwt);
      
    } catch (Exception e) {
      log.error("[Autenticacao] {}",e);
      return ResponseEntity.badRequest().build();
    }

  }

}
