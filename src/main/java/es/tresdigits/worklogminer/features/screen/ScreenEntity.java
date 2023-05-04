package es.tresdigits.worklogminer.features.screen;

import es.tresdigits.worklogminer.features.screen_permission.ScreenPermissionEntity;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "WLM_SCREEN")
public class ScreenEntity {

  @Id
  @Column(name = "SCR_CODE", nullable = false)
  private Integer scrCode;

  @Column(name = "SCR_LABEL", nullable = false, length = 50)
  private String scrLabel;

  @Column(name = "SCR_ICON", length = 50)
  private String scrIcon;

  @Column(name = "SCR_ROUTEID", length = 50)
  private String scrRouteId;

  @Column(name = "SCR_BREADCRUMBROUTEID")
  private String scrBreadcrumbRouteId;

  @Column(name = "SCR_ORDER", nullable = false)
  private Integer scrOrder;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SCR_CODSCRPARENT")
  private ScreenEntity parentScreen;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SCR_CODSCRBREADCRUMB")
  private ScreenEntity breadcrumbParentScreen;

  @OneToMany(mappedBy = "parentScreen", fetch = FetchType.LAZY)
  private List<ScreenEntity> childrenScreen;

  @OneToMany(mappedBy = "breadcrumbParentScreen", fetch = FetchType.LAZY)
  private List<ScreenEntity> breadcrumbRoutes;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "scpCodscr")
  List<ScreenPermissionEntity> screenPemissions;

  public Integer getScrOrder() {
    return scrOrder;
  }

  public void setScrOrder(Integer scrOrder) {
    this.scrOrder = scrOrder;
  }

  public String getScrRouteId() {
    return scrRouteId;
  }

  public void setScrRouteId(String scrRouterlink) {
    this.scrRouteId = scrRouterlink;
  }

  public String getScrIcon() {
    return scrIcon;
  }

  public void setScrIcon(String scrIcon) {
    this.scrIcon = scrIcon;
  }

  public String getScrLabel() {
    return scrLabel;
  }

  public void setScrLabel(String scrLabel) {
    this.scrLabel = scrLabel;
  }

  public ScreenEntity getParentScreen() {
    return parentScreen;
  }

  public void setParentScreen(ScreenEntity parentScreen) {
    this.parentScreen = parentScreen;
  }

  public List<ScreenEntity> getChildrenScreen() {
    return childrenScreen;
  }

  public void setChildrenScreen(
      List<ScreenEntity> childrenScreen) {
    this.childrenScreen = childrenScreen;
  }

  public Integer getScrCode() {
    return scrCode;
  }

  public void setScrCode(Integer scrCode) {
    this.scrCode = scrCode;
  }

  public List<ScreenPermissionEntity> getScreenPemissions() {
    return screenPemissions;
  }

  public void setScreenPemissions(
      List<ScreenPermissionEntity> screenPemissions) {
    this.screenPemissions = screenPemissions;
  }

  public ScreenEntity getBreadcrumbParentScreen() {
    return breadcrumbParentScreen;
  }

  public void setBreadcrumbParentScreen(
      ScreenEntity breadcrumbParentScreen) {
    this.breadcrumbParentScreen = breadcrumbParentScreen;
  }

  public List<ScreenEntity> getBreadcrumbRoutes() {
    return breadcrumbRoutes;
  }

  public void setBreadcrumbRoutes(
      List<ScreenEntity> breadcrumbRoutes) {
    this.breadcrumbRoutes = breadcrumbRoutes;
  }

  public String getScrBreadcrumbRouteId() {
    return scrBreadcrumbRouteId;
  }

  public void setScrBreadcrumbRouteId(String scrBreadcrumbRouteId) {
    this.scrBreadcrumbRouteId = scrBreadcrumbRouteId;
  }
}
