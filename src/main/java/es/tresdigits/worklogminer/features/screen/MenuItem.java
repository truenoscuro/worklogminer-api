package es.tresdigits.worklogminer.features.screen;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.List;

@JsonInclude(Include.NON_EMPTY)
public class MenuItem implements Serializable {

  private String label;
  private String icon;
  private String routeId;
  private List<MenuItem> breadcrumbRoutes;
  private String breadcrumbRouteId;
  private List<MenuItem> children;

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }

  public List<MenuItem> getBreadcrumbRoutes() {
    return breadcrumbRoutes;
  }

  public void setBreadcrumbRoutes(
      List<MenuItem> breadcrumbRoutes) {
    this.breadcrumbRoutes = breadcrumbRoutes;
  }

  public String getBreadcrumbRouteId() {
    return breadcrumbRouteId;
  }

  public void setBreadcrumbRouteId(String breadcrumbRouteId) {
    this.breadcrumbRouteId = breadcrumbRouteId;
  }

  public List<MenuItem> getChildren() {
    return children;
  }

  public void setChildren(List<MenuItem> children) {
    this.children = children;
  }
}