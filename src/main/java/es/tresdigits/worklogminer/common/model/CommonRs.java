package es.tresdigits.worklogminer.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommonRs<T> {
  private String languageCode;
  private T data;
  @JsonProperty("totalrows")
  private int totalRows;
  private ErrorResponse error;
}