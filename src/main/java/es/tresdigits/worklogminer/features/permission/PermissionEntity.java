package es.tresdigits.worklogminer.features.permission;

import es.tresdigits.worklogminer.features.role.RoleEntity;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "WLM_PERMISSION")
public class PermissionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PER_CODE", nullable = false)
  private Integer perCode;

  @Column(name = "PER_USECODE", nullable = false, length = 30)
  private String perUsecode;

  @Column(name = "PER_NAME", nullable = false, length = 50)
  private String perName;

  @Column(name = "PER_ACTIVE", nullable = false)
  private Boolean perActive = false;
  @CreatedBy
  @Column(name = "PER_CREUSE", nullable = false, length = 30)
  private String perCreuse;
  @CreatedDate
  @Column(name = "PER_CREDAT", nullable = false)
  private LocalDateTime perCredat;
  @LastModifiedBy
  @Column(name = "PER_MODUSE", length = 30)
  private String perModuse;
  @LastModifiedDate
  @Column(name = "PER_MODDAT")
  private LocalDateTime perModdat;

  @ManyToMany(mappedBy = "rolPermissions")
  private List<RoleEntity> perRoles;

  public LocalDateTime getPerModdat() {
    return perModdat;
  }

  public void setPerModdat(LocalDateTime perModdat) {
    this.perModdat = perModdat;
  }

  public String getPerModuse() {
    return perModuse;
  }

  public void setPerModuse(String perModuse) {
    this.perModuse = perModuse;
  }

  public LocalDateTime getPerCredat() {
    return perCredat;
  }

  public void setPerCredat(LocalDateTime perCredat) {
    this.perCredat = perCredat;
  }

  public String getPerCreuse() {
    return perCreuse;
  }

  public void setPerCreuse(String perCreuse) {
    this.perCreuse = perCreuse;
  }

  public Boolean getPerActive() {
    return perActive;
  }

  public void setPerActive(Boolean perActive) {
    this.perActive = perActive;
  }

  public String getPerName() {
    return perName;
  }

  public void setPerName(String perName) {
    this.perName = perName;
  }

  public String getPerUsecode() {
    return perUsecode;
  }

  public void setPerUsecode(String perUsecode) {
    this.perUsecode = perUsecode;
  }

  public Integer getPerCode() {
    return perCode;
  }

  public void setPerCode(Integer perCode) {
    this.perCode = perCode;
  }

  public List<RoleEntity> getPerRoles() {
    return perRoles;
  }

  public void setPerRoles(List<RoleEntity> perRoles) {
    this.perRoles = perRoles;
  }
}
