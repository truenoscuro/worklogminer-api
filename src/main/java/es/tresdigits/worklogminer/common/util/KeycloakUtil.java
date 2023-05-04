package es.tresdigits.worklogminer.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import es.tresdigits.worklogminer.common.model.KeycloakRealmInfo;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.annotation.PostConstruct;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakUtil {
  private static final Logger LOG = LoggerFactory.getLogger(KeycloakUtil.class);

  @Value("${keycloak.auth-server-url}")
  private String authServerUrl;

  @Value("${keycloak.realm}")
  private String realm;

  private String publicKeyRealm;

  private DecodedJWT decodedJWT;

  @PostConstruct
  private void initPublicKey() {
    publicKeyRealm = getPublicKey();
  }

  /**
   * Comprueba que el token pasado por parámetro sea válido, es decir, que esté codificado con el
   * secreto correcto y que no haya vencido
   *
   * @param token
   * @return
   */
  public boolean validate(String token) {
    DecodedJWT jwt = JWT.decode(token);
    try {
      PublicKey publicKey = generatePublicKey(publicKeyRealm);

      Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, null);
      JWTVerifier verifier = JWT.require(algorithm).build();

      verifier.verify(jwt);
      return true;

    } catch (JWTVerificationException ex) {
      // Invalid signature or claims
      LOG.error(ex.getMessage(), ex);
    }

    return false;
  }

  /**
   * Genera una publicKey para validar y/o verificar el token
   *
   * @param publicKeyString
   * @return
   */
  private PublicKey generatePublicKey(String publicKeyString) {
    try {
      byte[] keyBytes = Base64.getDecoder().decode(publicKeyString);
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      return keyFactory.generatePublic(keySpec);
    } catch (Exception e) {
      throw new RuntimeException("Error getting public key: " + e.getMessage(), e);
    }
  }

  /**
   * Recupera el email de las claims del token
   *
   * @param token
   * @return
   */
  public String getUserEmail(String token) {
    return decode(token).getClaims().get("email").asString();
  }

  /**
   * Decodifica el token y devuelve un DecodedJWT para acceder a sus claims
   *
   * @param token
   * @return
   */
  private DecodedJWT decode(String token) {
    Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) generatePublicKey(publicKeyRealm), null);
    JWTVerifier verifier = JWT.require(algorithm).build();
    return verifier.verify(token);
  }

  /**
   * Recupera la publicKey del realm configurado en el application.yaml
   *
   * @return
   */
  private String getPublicKey() {
    // Obtener el Issuer de la API de Keycloak
    try (CloseableHttpClient client = HttpClients.createDefault()) {

      HttpGet httpGet = new HttpGet(authServerUrl + "/realms/" + realm);
      org.apache.http.HttpResponse response = client.execute(httpGet);
      KeycloakRealmInfo realmInfo = JsonUtil.jsonToObject(
          EntityUtils.toString(response.getEntity()), KeycloakRealmInfo.class);

      return realmInfo.getPublicKey();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }




}
