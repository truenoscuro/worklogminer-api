package es.tresdigits.worklogminer.handlers;

import es.tresdigits.worklogminer.common.model.CommonRs;
import es.tresdigits.worklogminer.common.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<CommonRs<Object>> handleException(Exception ex) {
    log.error(ex.getMessage(), ex);
    return new ResponseEntity<>(CommonUtil.processErrorResponse(ex), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}