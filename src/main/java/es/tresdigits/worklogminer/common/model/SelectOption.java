package es.tresdigits.worklogminer.common.model;

import lombok.Data;

@Data
public class SelectOption {

  public SelectOption() {
  }

  public SelectOption(String label, Object value) {
    this.label = label;
    this.value = value;
  }

  private String label;
  private Object value;
}
