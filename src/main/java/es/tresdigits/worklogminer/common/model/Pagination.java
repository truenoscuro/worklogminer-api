package es.tresdigits.worklogminer.common.model;

import lombok.Data;

@Data
public class Pagination {
  private int page;
  private int delta;
}