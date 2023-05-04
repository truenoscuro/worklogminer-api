package es.tresdigits.worklogminer.features.authentication;

import es.tresdigits.worklogminer.common.util.KeycloakUtil;
import es.tresdigits.worklogminer.features.permission.PermissionEntity;
import es.tresdigits.worklogminer.features.permission.PermissionService;
import es.tresdigits.worklogminer.features.user.UserEntity;
import es.tresdigits.worklogminer.features.user.UserService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final UserService userService;
  private final KeycloakUtil keycloakUtil;
  private final PermissionService permissionService;
  @Value("${keycloak.credentials.secret}")
  private String secret;

  @Autowired
  public JwtAuthenticationFilter(UserService userService, PermissionService permissionService,
      KeycloakUtil keycloakUtil) {
    this.userService = userService;
    this.permissionService = permissionService;
    this.keycloakUtil = keycloakUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    // Comprobar si el header de Authorizatiom contiene algún valor. Si lo contiene,
    // Comprobar que el token comienza con el prefijo "Bearer "
    if (!Strings.isEmpty(header) && header.startsWith("Bearer ")) {
      // Elimina el prefijo del token
      final String token = header.replace("Bearer ", "");
      // Valida el token
      if (keycloakUtil.validate(token)) {
        Optional<UserEntity> user = userService.getByEmail(keycloakUtil.getUserEmail(token));
        if (user.isPresent()) {
          SecurityContextHolder.getContext()
              .setAuthentication(generateUsernamePasswordAuthenticationToken(user.get(),
                  user.get().getUseCodrol().getRolName()));
        } else {
          filterChain.doFilter(request, response);
          return;
        }
      } else {
        filterChain.doFilter(request, response);
        return;
      }
    }
    //Pasa al siguiente filtro de la cadena de filtros
    filterChain.doFilter(request, response);
  }

  /**
   * Genera un UsernamePasswordAuthenticationToken en base a un UserEntity
   *
   * @param userEntity El usuario proveniente de la base de datos
   * @param role       El rol almacenado en el token
   * @return Dado un UserEntity genera una instancia de UserDetaills la cual registra dentro de un
   * UsernamePasswordAuthenticationToken y lo devuelve
   */
  private UsernamePasswordAuthenticationToken generateUsernamePasswordAuthenticationToken(
      UserEntity userEntity, String role) {
    List<GrantedAuthority> authorities = generateGrantedAuthorities(
        permissionService.listByRoleName(role));
    UserDetails user = User
        .withUsername(userEntity.getUseEmail())
        .password("")
        .roles(role)
        .authorities(authorities)
        .build();
    return new UsernamePasswordAuthenticationToken(
        user, null, authorities);
  }

  List<GrantedAuthority> generateGrantedAuthorities(List<PermissionEntity> permissions) {
    List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList();
    for (PermissionEntity permission : permissions) {
      authorities.add(new SimpleGrantedAuthority(permission.getPerUsecode()));
    }
    return authorities;
  }

}

