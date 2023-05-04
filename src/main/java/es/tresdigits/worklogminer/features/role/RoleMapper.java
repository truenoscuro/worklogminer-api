package es.tresdigits.worklogminer.features.role;

import es.tresdigits.worklogminer.common.model.SelectOption;
import es.tresdigits.worklogminer.features.permission.PermissionMapper;
import es.tresdigits.worklogminer.features.rolepermission.RolepermissionMapper;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {RolepermissionMapper.class, PermissionMapper.class})
public interface RoleMapper {

  @Mapping(source = "rolCode", target = "code")
  @Mapping(source = "rolName", target = "name")
  @Mapping(source = "rolActive", target = "active")
  @Mapping(source = "rolCreuse", target = "creuse")
  @Mapping(source = "rolCredat", target = "credat")
  @Mapping(source = "rolModuse", target = "moduse")
  @Mapping(source = "rolModdat", target = "moddat")
  RoleDto entityToDto(RoleEntity src);

  @InheritInverseConfiguration
  List<RoleDto> entityToDtoList(List<RoleEntity> src);

  @InheritInverseConfiguration
  RoleEntity dtoToEntity(RoleDto src);

  @Mapping(source = "rolName", target = "label")
  @Mapping(source = "rolCode", target = "value")
  SelectOption entityToSelectOption(RoleEntity src);

  @InheritInverseConfiguration
  List<SelectOption> entityToSelectOptionList(List<RoleEntity> src);
}
