package es.tresdigits.worklogminer.features.screen_permission;

import es.tresdigits.worklogminer.common.model.CommonDTO;
import es.tresdigits.worklogminer.features.permission.PermissionDto;
import es.tresdigits.worklogminer.features.screen.MenuItem;
import java.io.Serializable;

public class ScreenPermissionDto extends CommonDTO implements Serializable {

  private MenuItem screen;
  private PermissionDto permissionDto;
  private Integer codscr;
  private Integer codper;

  public MenuItem getScreen() {
    return screen;
  }

  public void setScreen(MenuItem screen) {
    this.screen = screen;
  }

  public PermissionDto getPermissionDto() {
    return permissionDto;
  }

  public void setPermissionDto(PermissionDto permissionDto) {
    this.permissionDto = permissionDto;
  }

  public Integer getCodscr() {
    return codscr;
  }

  public void setCodscr(Integer codscr) {
    this.codscr = codscr;
  }

  public Integer getCodper() {
    return codper;
  }

  public void setCodper(Integer codper) {
    this.codper = codper;
  }
}
