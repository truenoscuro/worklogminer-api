package es.tresdigits.worklogminer.features.screen_permission;

import es.tresdigits.worklogminer.features.permission.PermissionMapper;
import es.tresdigits.worklogminer.features.screen.ScreenMenuItemMapper;
import java.util.List;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(uses = {ScreenMenuItemMapper.class, PermissionMapper.class})
public interface ScreenPermissionMapper {

  @Named("regularMapping")
  @Mapping(source = "scpCode", target = "code")
  @Mapping(source = "scpCodscr.scrCode", target = "codscr")
  @Mapping(source = "scpCodper.perCode", target = "codper")
  ScreenPermissionDto entityToDto(ScreenPermissionEntity screenPermissionEntity);

  @InheritConfiguration(name = "entityToDto")
  @Mapping(source = "scpCodscr", target = "screen")
  @Mapping(source = "scpCodper", target = "permissionDto")
  ScreenPermissionDto entityToDtoWithRelationships(ScreenPermissionEntity screenPermissionEntity);

  @InheritInverseConfiguration(name = "entityToDto")
  ScreenPermissionEntity dtoToEntity(ScreenPermissionDto screenPermissionDto);

  @IterableMapping(qualifiedByName = "regularMapping")
  List<ScreenPermissionDto> entityListToDto(
      List<ScreenPermissionEntity> screenPermissionEntityList);
}
