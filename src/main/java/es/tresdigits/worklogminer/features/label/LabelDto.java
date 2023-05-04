package es.tresdigits.worklogminer.features.label;

import es.tresdigits.worklogminer.common.model.CommonDTO;
import es.tresdigits.worklogminer.features.labelgroup.LabelGroupDto;
import java.io.Serializable;

public class LabelDto extends CommonDTO implements Serializable {

  private Integer codlgr;
  private LabelGroupDto labelGroupDto;
  private String id;

  public Integer getCodlgr() {
    return codlgr;
  }

  public void setCodlgr(Integer codlgr) {
    this.codlgr = codlgr;
  }

  public LabelGroupDto getLabelGroupDto() {
    return labelGroupDto;
  }

  public void setLabelGroupDto(LabelGroupDto labelGroupDto) {
    this.labelGroupDto = labelGroupDto;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
