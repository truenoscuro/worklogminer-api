package es.tresdigits.worklogminer.features.labelgroup;

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
@Table(name = "WLM_LABELGROUP")
public class LabelGroupEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LGR_CODE", nullable = false)
  private Integer lgrCode;

  @Column(name = "LGR_ID", nullable = false, length = 50)
  private String lgrId;
  @CreatedBy
  @Column(name = "LGR_CREUSE", nullable = false, length = 30)
  private String lgrCreuse;
  @CreatedDate
  @Column(name = "LGR_CREDAT", nullable = false)
  private LocalDateTime lgrCredat;
  @LastModifiedBy
  @Column(name = "LGR_MODUSE", length = 30)
  private String lgrModuse;
  @LastModifiedDate
  @Column(name = "LGR_MODDAT")
  private LocalDateTime lgrModdat;

  public LocalDateTime getLgrModdat() {
    return lgrModdat;
  }

  public void setLgrModdat(LocalDateTime lgrModdat) {
    this.lgrModdat = lgrModdat;
  }

  public String getLgrModuse() {
    return lgrModuse;
  }

  public void setLgrModuse(String lgrModuse) {
    this.lgrModuse = lgrModuse;
  }

  public LocalDateTime getLgrCredat() {
    return lgrCredat;
  }

  public void setLgrCredat(LocalDateTime lgrCredat) {
    this.lgrCredat = lgrCredat;
  }

  public String getLgrCreuse() {
    return lgrCreuse;
  }

  public void setLgrCreuse(String lgrCreuse) {
    this.lgrCreuse = lgrCreuse;
  }

  public String getLgrId() {
    return lgrId;
  }

  public void setLgrId(String lgrId) {
    this.lgrId = lgrId;
  }

  public Integer getLgrCode() {
    return lgrCode;
  }

  public void setLgrCode(Integer lgrCode) {
    this.lgrCode = lgrCode;
  }
}
