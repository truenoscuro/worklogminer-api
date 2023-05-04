package es.tresdigits.worklogminer.features.screen_permission;

import es.tresdigits.worklogminer.features.permission.PermissionEntity;
import es.tresdigits.worklogminer.features.screen.ScreenEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "WLM_SCREENPERMISSION")
public class ScreenPermissionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SCP_CODE", nullable = false)
  private Integer scpCode;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "SCP_CODSCR", nullable = false)
  private ScreenEntity scpCodscr;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "SCP_CODPER", nullable = false)
  private PermissionEntity scpCodper;

  public PermissionEntity getScpCodper() {
    return scpCodper;
  }

  public void setScpCodper(PermissionEntity scpCodper) {
    this.scpCodper = scpCodper;
  }

  public ScreenEntity getScpCodscr() {
    return scpCodscr;
  }

  public void setScpCodscr(ScreenEntity scpCodscr) {
    this.scpCodscr = scpCodscr;
  }

  public Integer getScpCode() {
    return scpCode;
  }

  public void setScpCode(Integer scpCode) {
    this.scpCode = scpCode;
  }
}
