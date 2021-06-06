package tech.vsj.mercadolivre.shared;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {


  private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


  private TokenManager tokenManager;
  private UsersService usersService;

  public JwtAuthenticationFilter(TokenManager tokenManager, UsersService usersService) {
    this.tokenManager = tokenManager;
    this.usersService = usersService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    Optional<String> possibleToken = getToketFromRequest(request);
    if (possibleToken.isPresent() && tokenManager.isValid(possibleToken.get())) {
      log.info(String.format("Token encontado: %s", possibleToken.get()));
      String userName = tokenManager.getUserName(possibleToken.get());

      UserDetails userDetails = usersService.loadUserByUsername(userName);

      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    filterChain.doFilter(request, response);



  }

  private Optional<String> getToketFromRequest(HttpServletRequest request) {
    String authToken = request.getHeader("Authorization");
    return Optional.ofNullable(authToken);
  }



}
