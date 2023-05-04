package es.tresdigits.worklogminer.features.user;

import es.tresdigits.worklogminer.features.language.LanguageEntity;
import es.tresdigits.worklogminer.features.role.RoleEntity;
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
@Table(name = "WLM_USER")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USE_CODE", nullable = false)
  private Integer useCode;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "USE_CODROL", nullable = false)
  private RoleEntity useCodrol;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "USE_CODLAN", nullable = false)
  private LanguageEntity useCodlan;

  @Column(name = "USE_NAME", nullable = false, length = 30)
  private String useName;

  @Column(name = "USE_SURNAME", nullable = false, length = 30)
  private String useSurname;

  @Column(name = "USE_EMAIL", nullable = false, length = 30)
  private String useEmail;

  @Column(name = "USE_ACTIVE", nullable = false)
  private Boolean useActive = false;
  @CreatedBy
  @Column(name = "USE_CREUSE", nullable = false, length = 30)
  private String useCreuse;
  @CreatedDate
  @Column(name = "USE_CREDAT", nullable = false)
  private LocalDateTime useCredat;
  @LastModifiedBy
  @Column(name = "USE_MODUSE", length = 30)
  private String useModuse;
  @LastModifiedDate
  @Column(name = "USE_MODDAT")
  private LocalDateTime useModdat;

  public LocalDateTime getUseModdat() {
    return useModdat;
  }

  public void setUseModdat(LocalDateTime useModdat) {
    this.useModdat = useModdat;
  }

  public String getUseModuse() {
    return useModuse;
  }

  public void setUseModuse(String useModuse) {
    this.useModuse = useModuse;
  }

  public LocalDateTime getUseCredat() {
    return useCredat;
  }

  public void setUseCredat(LocalDateTime useCredat) {
    this.useCredat = useCredat;
  }

  public String getUseCreuse() {
    return useCreuse;
  }

  public void setUseCreuse(String useCreuse) {
    this.useCreuse = useCreuse;
  }

  public Boolean getUseActive() {
    return useActive;
  }

  public void setUseActive(Boolean useActive) {
    this.useActive = useActive;
  }

  public String getUseEmail() {
    return useEmail;
  }

  public void setUseEmail(String useEmail) {
    this.useEmail = useEmail;
  }

  public String getUseSurname() {
    return useSurname;
  }

  public void setUseSurname(String useSurname) {
    this.useSurname = useSurname;
  }

  public String getUseName() {
    return useName;
  }

  public void setUseName(String useName) {
    this.useName = useName;
  }

  public LanguageEntity getUseCodlan() {
    return useCodlan;
  }

  public void setUseCodlan(LanguageEntity useCodlan) {
    this.useCodlan = useCodlan;
  }

  public RoleEntity getUseCodrol() {
    return useCodrol;
  }

  public void setUseCodrol(RoleEntity useCodrol) {
    this.useCodrol = useCodrol;
  }

  public Integer getUseCode() {
    return useCode;
  }

  public void setUseCode(Integer code) {
    this.useCode = code;
  }
}
