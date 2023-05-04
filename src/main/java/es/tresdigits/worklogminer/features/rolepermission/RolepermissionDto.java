package es.tresdigits.worklogminer.features.rolepermission;

import es.tresdigits.worklogminer.common.model.CommonDTO;
import es.tresdigits.worklogminer.features.permission.PermissionDto;
import es.tresdigits.worklogminer.features.role.RoleDto;
import java.io.Serializable;

public class RolepermissionDto extends CommonDTO implements Serializable {

  private RoleDto codrol;
  private PermissionDto codper;

  public RoleDto getCodrol() {
    return codrol;
  }

  public void setCodrol(RoleDto codrol) {
    this.codrol = codrol;
  }

  public PermissionDto getCodper() {
    return codper;
  }

  public void setCodper(PermissionDto codper) {
    this.codper = codper;
  }
}
