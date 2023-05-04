package es.tresdigits.worklogminer.features.label;

import es.tresdigits.worklogminer.features.labelgroup.LabelGroupEntity;
import es.tresdigits.worklogminer.features.languagelabel.LanguagelabelEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "WLM_LABEL")
public class LabelEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LAB_CODE", nullable = false)
  private Integer labCode;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "LAB_CODLGR", nullable = false)
  private LabelGroupEntity labCodlgr;

  @OneToMany(mappedBy = "lalCodlab")
  private List<LanguagelabelEntity> languageLabels;

  @Column(name = "LAB_ID", nullable = false, length = 50)
  private String labId;
  @CreatedBy
  @Column(name = "LAB_CREUSE", nullable = false, length = 30)
  private String labCreuse;
  @CreatedDate
  @Column(name = "LAB_CREDAT", nullable = false)
  private LocalDateTime labCredat;
  @LastModifiedBy
  @Column(name = "LAB_MODUSE", length = 30)
  private String labModuse;
  @LastModifiedDate
  @Column(name = "LAB_MODDAT")
  private LocalDateTime labModdat;

  public LocalDateTime getLabModdat() {
    return labModdat;
  }

  public void setLabModdat(LocalDateTime labModdat) {
    this.labModdat = labModdat;
  }

  public String getLabModuse() {
    return labModuse;
  }

  public void setLabModuse(String labModuse) {
    this.labModuse = labModuse;
  }

  public LocalDateTime getLabCredat() {
    return labCredat;
  }

  public void setLabCredat(LocalDateTime labCredat) {
    this.labCredat = labCredat;
  }

  public String getLabCreuse() {
    return labCreuse;
  }

  public void setLabCreuse(String labCreuse) {
    this.labCreuse = labCreuse;
  }

  public String getLabId() {
    return labId;
  }

  public void setLabId(String labId) {
    this.labId = labId;
  }

  public LabelGroupEntity getLabCodlgr() {
    return labCodlgr;
  }

  public void setLabCodlgr(LabelGroupEntity labCodlgr) {
    this.labCodlgr = labCodlgr;
  }

  public Integer getLabCode() {
    return labCode;
  }

  public void setLabCode(Integer labCode) {
    this.labCode = labCode;
  }

  public List<LanguagelabelEntity> getLanguageLabels() {
    return languageLabels;
  }

  public void setLanguageLabels(
      List<LanguagelabelEntity> languageLabels) {
    this.languageLabels = languageLabels;
  }
}
