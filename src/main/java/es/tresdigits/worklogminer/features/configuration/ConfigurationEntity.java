package es.tresdigits.worklogminer.features.configuration;

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
@Table(name = "WLM_CONFIGURATION")
public class ConfigurationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CON_CODE", nullable = false)
  private Integer conCode;

  @Column(name = "CON_LABEL", nullable = false, length = 100)
  private String conLabel;

  @Column(name = "CON_KEY", nullable = false, length = 100)
  private String conKey;

  @Column(name = "CON_VALUE", nullable = false, length = 200)
  private String conValue;

  @Column(name = "CON_VALIDATION", length = 250)
  private String conValidation;
  @CreatedBy
  @Column(name = "CON_CREUSE", nullable = false, length = 30)
  private String conCreuse;
  @CreatedDate
  @Column(name = "CON_CREDAT", nullable = false)
  private LocalDateTime conCredat;
  @LastModifiedBy
  @Column(name = "CON_MODUSE", length = 30)
  private String conModuse;
  @LastModifiedDate
  @Column(name = "CON_MODDAT")
  private LocalDateTime conModdat;

  public LocalDateTime getConModdat() {
    return conModdat;
  }

  public void setConModdat(LocalDateTime conModdat) {
    this.conModdat = conModdat;
  }

  public String getConModuse() {
    return conModuse;
  }

  public void setConModuse(String conModuse) {
    this.conModuse = conModuse;
  }

  public LocalDateTime getConCredat() {
    return conCredat;
  }

  public void setConCredat(LocalDateTime conCredat) {
    this.conCredat = conCredat;
  }

  public String getConCreuse() {
    return conCreuse;
  }

  public void setConCreuse(String conCreuse) {
    this.conCreuse = conCreuse;
  }

  public String getConValidation() {
    return conValidation;
  }

  public void setConValidation(String conValidation) {
    this.conValidation = conValidation;
  }

  public String getConValue() {
    return conValue;
  }

  public void setConValue(String conValue) {
    this.conValue = conValue;
  }

  public String getConKey() {
    return conKey;
  }

  public void setConKey(String conKey) {
    this.conKey = conKey;
  }

  public String getConLabel() {
    return conLabel;
  }

  public void setConLabel(String conLabel) {
    this.conLabel = conLabel;
  }

  public Integer getConCode() {
    return conCode;
  }

  public void setConCode(Integer conCode) {
    this.conCode = conCode;
  }
}
