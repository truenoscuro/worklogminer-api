package es.tresdigits.worklogminer.features.permission;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PermissionMapper {

  @Mapping(source = "perCode", target = "code")
  @Mapping(source = "perUsecode", target = "usecode")
  @Mapping(source = "perName", target = "name")
  @Mapping(source = "perActive", target = "active")
  @Mapping(source = "perCreuse", target = "creuse")
  @Mapping(source = "perCredat", target = "credat")
  @Mapping(source = "perModuse", target = "moduse")
  @Mapping(source = "perModdat", target = "moddat")
  PermissionDto entityToDto(PermissionEntity permissionEntity);

  @InheritInverseConfiguration
  PermissionEntity dtoToEntity(PermissionDto permissionDto);

  List<PermissionDto> entityListToDto(List<PermissionEntity> permissionEntityList);
}
