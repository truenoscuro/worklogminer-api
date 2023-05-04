package es.tresdigits.worklogminer.features.role;

import es.tresdigits.worklogminer.features.permission.PermissionEntity;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "WLM_ROL")
public class RoleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ROL_CODE", nullable = false)
  private Integer rolCode;

  @Column(name = "ROL_NAME", nullable = false, length = 50)
  private String rolName;

  @Column(name = "ROL_ACTIVE", nullable = false)
  private Boolean rolActive = false;
  @CreatedBy
  @Column(name = "ROL_CREUSE", nullable = false, length = 30)
  private String rolCreuse;
  @CreatedDate
  @Column(name = "ROL_CREDAT", nullable = false)
  private LocalDateTime rolCredat;
  @LastModifiedBy
  @Column(name = "ROL_MODUSE", length = 30)
  private String rolModuse;
  @LastModifiedDate
  @Column(name = "ROL_MODDAT")
  private LocalDateTime rolModdat;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "WLM_ROLPERMISSION",
      joinColumns = @JoinColumn(name = "RPE_CODROL"),
      inverseJoinColumns = @JoinColumn(name = "RPE_CODPER")
  )
  private List<PermissionEntity> rolPermissions;

  public LocalDateTime getRolModdat() {
    return rolModdat;
  }

  public void setRolModdat(LocalDateTime rolModdat) {
    this.rolModdat = rolModdat;
  }

  public String getRolModuse() {
    return rolModuse;
  }

  public void setRolModuse(String rolModuse) {
    this.rolModuse = rolModuse;
  }

  public LocalDateTime getRolCredat() {
    return rolCredat;
  }

  public void setRolCredat(LocalDateTime rolCredat) {
    this.rolCredat = rolCredat;
  }

  public String getRolCreuse() {
    return rolCreuse;
  }

  public void setRolCreuse(String rolCreuse) {
    this.rolCreuse = rolCreuse;
  }

  public Boolean getRolActive() {
    return rolActive;
  }

  public void setRolActive(Boolean rolActive) {
    this.rolActive = rolActive;
  }

  public String getRolName() {
    return rolName;
  }

  public void setRolName(String rolName) {
    this.rolName = rolName;
  }

  public Integer getRolCode() {
    return rolCode;
  }

  public void setRolCode(Integer code) {
    this.rolCode = code;
  }

  public List<PermissionEntity> getRolPermissions() {
    return rolPermissions;
  }

  public void setRolPermissions(
      List<PermissionEntity> rolPermissions) {
    this.rolPermissions = rolPermissions;
  }
}
