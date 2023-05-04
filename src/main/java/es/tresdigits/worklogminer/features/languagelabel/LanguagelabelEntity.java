package es.tresdigits.worklogminer.features.languagelabel;

import es.tresdigits.worklogminer.features.label.LabelEntity;
import es.tresdigits.worklogminer.features.language.LanguageEntity;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "WLM_LANGUAGELABEL")
public class LanguagelabelEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LAL_CODE", nullable = false)
  private Integer lalCode;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "LAL_CODLAN", nullable = false)
  private LanguageEntity lalCodlan;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "LAL_CODLAB", nullable = false)
  private LabelEntity lalCodlab;

  @Column(name = "LAL_VALUE", nullable = false, length = 250)
  private String lalValue;
  @CreatedBy
  @Column(name = "LAL_CREUSE", nullable = false, length = 30)
  private String lalCreuse;
  @CreatedDate
  @Column(name = "LAL_CREDAT", nullable = false)
  private LocalDateTime lalCredat;
  @LastModifiedBy
  @Column(name = "LAL_MODUSE", length = 30)
  private String lalModuse;
  @LastModifiedDate
  @Column(name = "LAL_MODDAT")
  private LocalDateTime lalModdat;

  public LocalDateTime getLalModdat() {
    return lalModdat;
  }

  public void setLalModdat(LocalDateTime lalModdat) {
    this.lalModdat = lalModdat;
  }

  public String getLalModuse() {
    return lalModuse;
  }

  public void setLalModuse(String lalModuse) {
    this.lalModuse = lalModuse;
  }

  public LocalDateTime getLalCredat() {
    return lalCredat;
  }

  public void setLalCredat(LocalDateTime lalCredat) {
    this.lalCredat = lalCredat;
  }

  public String getLalCreuse() {
    return lalCreuse;
  }

  public void setLalCreuse(String lalCreuse) {
    this.lalCreuse = lalCreuse;
  }

  public String getLalValue() {
    return lalValue;
  }

  public void setLalValue(String lalValue) {
    this.lalValue = lalValue;
  }

  public LabelEntity getLalCodlab() {
    return lalCodlab;
  }

  public void setLalCodlab(LabelEntity lalCodlab) {
    this.lalCodlab = lalCodlab;
  }

  public LanguageEntity getLalCodlan() {
    return lalCodlan;
  }

  public void setLalCodlan(LanguageEntity lalCodlan) {
    this.lalCodlan = lalCodlan;
  }

  public Integer getLalCode() {
    return lalCode;
  }

  public void setLalCode(Integer lalCode) {
    this.lalCode = lalCode;
  }
}
