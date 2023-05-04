package es.tresdigits.worklogminer.features.rolepermission;

import es.tresdigits.worklogminer.features.permission.PermissionMapper;
import es.tresdigits.worklogminer.features.role.RoleMapper;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {RoleMapper.class, PermissionMapper.class})
public interface RolepermissionMapper {

  @Mapping(source = "rpeCode", target = "code")
  @Mapping(source = "rpeCodrol", target = "codrol")
  @Mapping(source = "rpeCodper", target = "codper")
  RolepermissionDto entityToDto(RolepermissionEntity rp);

  @InheritInverseConfiguration
  RolepermissionEntity dtoToEntity(RolepermissionDto rp);

  List<RolepermissionDto> entityToDtoList(List<RolepermissionEntity> rp);

}
