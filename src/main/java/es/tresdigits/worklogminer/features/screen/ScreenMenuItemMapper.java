package es.tresdigits.worklogminer.features.screen;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ScreenMenuItemMapper {

  @Mapping(source = "scrLabel", target = "label")
  @Mapping(source = "scrIcon", target = "icon")
  @Mapping(source = "scrRouteId", target = "routeId")
  @Mapping(source = "breadcrumbRoutes", target = "breadcrumbRoutes")
  @Mapping(source = "scrBreadcrumbRouteId", target = "breadcrumbRouteId")
  @Mapping(source = "childrenScreen", target = "children")
  MenuItem entityToDto(ScreenEntity screenEntity);

  @InheritInverseConfiguration
  ScreenEntity dtoToEntity(MenuItem menuItem);

  List<MenuItem> entityListToDto(List<ScreenEntity> screenEntityList);

}