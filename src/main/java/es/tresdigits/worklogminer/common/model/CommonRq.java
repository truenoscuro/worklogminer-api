package es.tresdigits.worklogminer.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CommonRq<T> extends BasicRq {
  private T data;
}