package es.tresdigits.worklogminer.common.model;

import java.time.LocalDateTime;

public abstract class CommonDTO {

  private Integer code;
  private String creuse;
  private LocalDateTime credat;
  private String moduse;
  private LocalDateTime moddat;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getCreuse() {
    return creuse;
  }

  public void setCreuse(String creuse) {
    this.creuse = creuse;
  }

  public LocalDateTime getCredat() {
    return credat;
  }

  public void setCredat(LocalDateTime credat) {
    this.credat = credat;
  }

  public String getModuse() {
    return moduse;
  }

  public void setModuse(String moduse) {
    this.moduse = moduse;
  }

  public LocalDateTime getModdat() {
    return moddat;
  }

  public void setModdat(LocalDateTime moddat) {
    this.moddat = moddat;
  }
}
