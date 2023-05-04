package es.tresdigits.worklogminer.features.translations;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TranslationValue {

  private Integer code;
  private String lang;
  private String value;
}

