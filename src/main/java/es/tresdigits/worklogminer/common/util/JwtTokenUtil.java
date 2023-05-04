package es.tresdigits.worklogminer.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;

public class JwtTokenUtil {

  private JwtTokenUtil() {
    throw new IllegalArgumentException("Util class");
  }

  /**
   * Genera un token con las variables pasadas por parámetro
   *
   * @param secret        El secreto en el cual se basa para firmar el token.
   * @param username      El valor que tendrá el token en el claim <i>Subject</i>.
   * @param role          El rol del usuario
   * @param audience      El valor que tendrá el token en el claim <i>Audience</i>.
   * @param tokenValidity Se le suma esta valor a la fecha actual para calcular la fecha de
   *                      caducidad del token.
   * @return Devuelve un genera un token basándose en los valores pasados por parámetro. Utiliza el
   * algoritmo HMAC256 para firmar el token.
   */
  public static String generate(String secret, String username, String role, String audience,
      Long tokenValidity) {
    return JWT.create()
        .withIssuedAt(new Date(System.currentTimeMillis()))
        .withAudience(audience)
        .withExpiresAt(new Date(System.currentTimeMillis() + tokenValidity))
        .withSubject(username)
        .withClaim("role", role)
        .sign(Algorithm.HMAC256(secret));
  }

  /**
   * Comprueba si el token pasado por parámetro ha caducado
   *
   * @param token Texto generado por un JWT.Creator.Builder que contiene información sobre el
   *              usuario autenticado.
   * @return Devuelve true si el token no ha caducado o false en el caso contrario
   */
  public static boolean validate(String token, String secret) {
    DecodedJWT decodedToken = decode(token, secret);
    Date expTokenDate = decodedToken.getExpiresAt();
    Date currentDate = new Date(System.currentTimeMillis());
    return expTokenDate.after(currentDate);
  }


  /**
   * Recupera el valor del claim Subject, que es el que contendrá el username
   *
   * @param token  Texto generado por un JWT.Creator.Builder que contiene información sobre el
   *               usuario autenticado.
   * @param secret Clave para validar el token
   * @return Devuelve el valor del claim <i>Subject</i>, que es el nombre del usuario autenticado.
   */
  public static String getUsername(String token, String secret) {
    return decode(token, secret).getSubject();
  }

  /**
   * @param token  Texto generado por un JWT.Creator.Builder que contiene información sobre el
   *               usuario autenticado.
   * @param secret Clave para validar el token
   * @return Devuelve el valor del claim <i>Role</i>, que es el rol del usuario autenticado.
   */
  public static String getRole(String token, String secret) {
    return decode(token, secret).getClaim("role").asString();
  }

  /**
   * Verifica que el secreto del token coincida con el secreto de la aplicación y lo devuelve
   * decodificado
   *
   * @param token Texto generado por un JWT.Creator.Builder que contiene información sobre el
   *              usuario autenticado.
   * @return Devuelve un objeto DecodedJWT que contiene los valores que almacenaba el token pasado
   * por parámetro
   */
  private static DecodedJWT decode(String token, String secret) {
    JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
    return verifier.verify(token);
  }
}
