package es.tresdigits.worklogminer.features.language;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "WLM_LANGUAGE")
public class LanguageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LAN_CODE", nullable = false)
  private Integer lanCode;

  @Column(name = "LAN_NAME", nullable = false, length = 50)
  private String lanName;

  @Column(name = "LAN_DESCRIPTION", length = 250)
  private String lanDescription;

  @Column(name = "LAN_ORDER", nullable = false)
  private Integer lanOrder;

  @Column(name = "LAN_ACTIVE", nullable = false)
  private Boolean lanActive = false;
  @CreatedBy
  @Column(name = "LAN_CREUSE", nullable = false, length = 30)
  private String lanCreuse;
  @CreatedDate
  @Column(name = "LAN_CREDAT", nullable = false)
  private LocalDateTime lanCredat;
  @LastModifiedBy
  @Column(name = "LAN_MODUSE", length = 30)
  private String lanModuse;
  @LastModifiedDate
  @Column(name = "LAN_MODDAT")
  private LocalDateTime lanModdat;

  public LocalDateTime getLanModdat() {
    return lanModdat;
  }

  public void setLanModdat(LocalDateTime lanModdat) {
    this.lanModdat = lanModdat;
  }

  public String getLanModuse() {
    return lanModuse;
  }

  public void setLanModuse(String lanModuse) {
    this.lanModuse = lanModuse;
  }

  public LocalDateTime getLanCredat() {
    return lanCredat;
  }

  public void setLanCredat(LocalDateTime lanCredat) {
    this.lanCredat = lanCredat;
  }

  public String getLanCreuse() {
    return lanCreuse;
  }

  public void setLanCreuse(String lanCreuse) {
    this.lanCreuse = lanCreuse;
  }

  public Boolean getLanActive() {
    return lanActive;
  }

  public void setLanActive(Boolean lanActive) {
    this.lanActive = lanActive;
  }

  public Integer getLanOrder() {
    return lanOrder;
  }

  public void setLanOrder(Integer lanOrder) {
    this.lanOrder = lanOrder;
  }

  public String getLanDescription() {
    return lanDescription;
  }

  public void setLanDescription(String lanDescription) {
    this.lanDescription = lanDescription;
  }

  public String getLanName() {
    return lanName;
  }

  public void setLanName(String lanName) {
    this.lanName = lanName;
  }

  public Integer getLanCode() {
    return lanCode;
  }

  public void setLanCode(Integer lanCode) {
    this.lanCode = lanCode;
  }


}
