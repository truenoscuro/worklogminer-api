package es.tresdigits.worklogminer.common.model;

import java.util.List;
import lombok.Data;

@Data
public class PaginatedResponse<T> {

  private Long totalRows;
  private List<T> data;
}
