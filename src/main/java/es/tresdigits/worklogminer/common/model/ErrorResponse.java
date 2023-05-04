package es.tresdigits.worklogminer.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
  private String errorMessage;
  private String exceptionName;
  private String stackTrace;
}