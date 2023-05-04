package es.tresdigits.worklogminer.common.model;

import java.util.List;
import lombok.Data;

@Data
public class Filter {
  private String field;
  private String operator;
  private String value;
  private List<String> values;
}
