package es.tresdigits.worklogminer.features.languagelabel;

import es.tresdigits.worklogminer.common.model.CommonDTO;
import es.tresdigits.worklogminer.features.label.LabelDto;
import es.tresdigits.worklogminer.features.language.LanguageDto;
import java.io.Serializable;

public class LanguagelabelDto extends CommonDTO implements Serializable {

  private Integer codlan;
  private Integer codlab;
  private LabelDto labelDto;
  private LanguageDto languageDto;
  private String value;

  public Integer getCodlan() {
    return codlan;
  }

  public void setCodlan(Integer codlan) {
    this.codlan = codlan;
  }

  public Integer getCodlab() {
    return codlab;
  }

  public void setCodlab(Integer codlab) {
    this.codlab = codlab;
  }

  public LabelDto getLabelDto() {
    return labelDto;
  }

  public void setLabelDto(LabelDto labelDto) {
    this.labelDto = labelDto;
  }

  public LanguageDto getLanguageDto() {
    return languageDto;
  }

  public void setLanguageDto(LanguageDto languageDto) {
    this.languageDto = languageDto;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
