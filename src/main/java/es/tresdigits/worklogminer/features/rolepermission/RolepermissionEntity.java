package es.tresdigits.worklogminer.features.rolepermission;

import es.tresdigits.worklogminer.features.permission.PermissionEntity;
import es.tresdigits.worklogminer.features.role.RoleEntity;
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
@Table(name = "WLM_ROLPERMISSION")
public class RolepermissionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "RPE_CODE", nullable = false)
  private Integer rpeCode;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "RPE_CODROL", nullable = false)
  private RoleEntity rpeCodrol;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "RPE_CODPER", nullable = false)
  private PermissionEntity rpeCodper;


  public RoleEntity getRpeCodrol() {
    return rpeCodrol;
  }

  public void setRpeCodrol(RoleEntity rpeCodrol) {
    this.rpeCodrol = rpeCodrol;
  }

  public Integer getRpeCode() {
    return rpeCode;
  }

  public void setRpeCode(Integer rpeCode) {
    this.rpeCode = rpeCode;
  }

  public PermissionEntity getRpeCodper() {
    return rpeCodper;
  }

  public void setRpeCodper(PermissionEntity rpeCodper) {
    this.rpeCodper = rpeCodper;
  }
}
