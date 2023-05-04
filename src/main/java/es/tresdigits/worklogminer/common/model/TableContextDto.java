package es.tresdigits.worklogminer.common.model;

import java.util.Map;
import lombok.Data;

@Data
public class TableContextDto {

  private Map<String, ?> filters;
  private Pagination pagination;
  private Sort sort;
}