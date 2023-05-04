package es.tresdigits.worklogminer.features.example;

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
@Table(name = "WLM_EXAMPLE")
public class ExampleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "EXA_CODE", nullable = false)
  private Integer exaCode;

  @Column(name = "EXA_NAME", nullable = false, length = 100)
  private String exaName;

  @Column(name = "EXA_DESCRIPTION", nullable = false, length = 300)
  private String exaDescription;

  @Column(name = "EXA_DATE", nullable = false)
  private LocalDateTime exaDate;

  @Column(name = "EXA_SIZE", nullable = false)
  private Integer exaSize;

  @Column(name = "EXA_COLOR", nullable = false)
  private Integer exaColor;

  @CreatedBy
  @Column(name = "EXA_CREUSE", nullable = false, length = 30)
  private String exaCreuse;
  @CreatedDate
  @Column(name = "EXA_CREDAT", nullable = false)
  private LocalDateTime exaCredat;
  @LastModifiedBy
  @Column(name = "EXA_MODUSE", length = 30)
  private String exaModuse;
  @LastModifiedDate
  @Column(name = "EXA_MODDAT")
  private LocalDateTime exaModdat;

  public Integer getExaCode() {
    return exaCode;
  }

  public void setExaCode(Integer exaCode) {
    this.exaCode = exaCode;
  }

  public String getExaName() {
    return exaName;
  }

  public void setExaName(String exaName) {
    this.exaName = exaName;
  }

  public String getExaDescription() {
    return exaDescription;
  }

  public void setExaDescription(String exaDescription) {
    this.exaDescription = exaDescription;
  }

  public LocalDateTime getExaDate() {
    return exaDate;
  }

  public void setExaDate(LocalDateTime exaDate) {
    this.exaDate = exaDate;
  }

  public Integer getExaSize() {
    return exaSize;
  }

  public void setExaSize(Integer exaSize) {
    this.exaSize = exaSize;
  }

  public Integer getExaColor() {
    return exaColor;
  }

  public void setExaColor(Integer exaColor) {
    this.exaColor = exaColor;
  }

  public String getExaCreuse() {
    return exaCreuse;
  }

  public void setExaCreuse(String exaCreuse) {
    this.exaCreuse = exaCreuse;
  }

  public LocalDateTime getExaCredat() {
    return exaCredat;
  }

  public void setExaCredat(LocalDateTime exaCredat) {
    this.exaCredat = exaCredat;
  }

  public String getExaModuse() {
    return exaModuse;
  }

  public void setExaModuse(String exaModuse) {
    this.exaModuse = exaModuse;
  }

  public LocalDateTime getExaModdat() {
    return exaModdat;
  }

  public void setExaModdat(LocalDateTime exaModdat) {
    this.exaModdat = exaModdat;
  }
}
