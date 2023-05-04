package es.tresdigits.worklogminer.common.util;

import es.tresdigits.worklogminer.common.model.BasicRq;
import es.tresdigits.worklogminer.common.model.CommonRs;
import es.tresdigits.worklogminer.common.model.ErrorResponse;
import es.tresdigits.worklogminer.common.model.StatusResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonUtil {

  private CommonUtil() {
    throw new IllegalArgumentException("Utility class");
  }

  /**
   * Método utilitario para generar las respuestas de la API REST.
   *
   * @param rq             objeto de la RQ realizada al controlador
   * @param statusResponse tipo de operación
   * @param data           a devolver
   * @param <D>            tipo de los datos a devolver
   * @return RS para devolver directamente al frontal
   */
  public static <D> ResponseEntity<CommonRs<D>> processResponse(BasicRq rq,
      StatusResponse statusResponse, D data) {
    CommonRs<D> rs = new CommonRs<>();
    rs.setData(data);
    rs.setLanguageCode(rq.getLanguageCode());

    return new ResponseEntity<>(rs, generateStatus(statusResponse, data == null));
  }

  /**
   * Método utilitario para generar las respuestas de información poginada.
   *
   * @param rq             objeto de la RQ realizada al controlador
   * @param statusResponse tipo de operación
   * @param data           a devolver
   * @param <D>            tipo de los datos a devolver
   * @param totalRows      número total de registros
   * @return RS para devolver directamente al frontal
   */
  public static <D> ResponseEntity<CommonRs<D>> processResponse(BasicRq rq,
      StatusResponse statusResponse, D data, int totalRows) {
    CommonRs<D> rs = new CommonRs<>();
    rs.setData(data);
    rs.setLanguageCode(rq.getLanguageCode());
    rs.setTotalRows(totalRows);

    return new ResponseEntity<>(rs, generateStatus(statusResponse, data == null));
  }

  /**
   * Método utilitario para generar las respuestas de error listas para que el frontal las procese.
   *
   * @param e excepcion a tratar
   * @return RS para devolver directamente al frontal.
   */
  public static <D> CommonRs<D> processErrorResponse(Exception e) {
    CommonRs<D> rs = new CommonRs<>();
    rs.setError(new ErrorResponse(e.getMessage(), e.getClass().getSimpleName(),
        ExceptionUtils.getStackTrace(e)));

    return rs;
  }

  private static HttpStatus generateStatus(StatusResponse statusResponse, Boolean dataIsNull) {
    HttpStatus statusCode = HttpStatus.OK;
    if ((StatusResponse.GET.equals(statusResponse) || StatusResponse.CREATE.equals(statusResponse)
        || StatusResponse.UPDATE.equals(statusResponse)) && dataIsNull) {
      statusCode = HttpStatus.NOT_FOUND;
    } else if (StatusResponse.CREATE.equals(statusResponse)) {
      statusCode = HttpStatus.CREATED;
    }

    return statusCode;
  }

}