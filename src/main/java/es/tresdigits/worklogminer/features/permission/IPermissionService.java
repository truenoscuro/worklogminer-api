package es.tresdigits.worklogminer.features.permission;

import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import java.util.List;

public interface IPermissionService {

  PaginatedResponse<PermissionDto> list(TableContextDto dto);

  PaginatedResponse<PermissionDto> listByRole(Integer id, TableContextDto dto);

  List<PermissionDto> listActive();

  PermissionDto get(Integer id);

  PermissionDto create(PermissionDto permissionDto);

  PermissionDto update(PermissionDto permissionDto);

  PermissionDto manageRolePermissions(PermissionDto permissionDto, Integer rolCode);

  void delete(Integer id);

  List<PermissionEntity> listByRoleName(String roleName);
}
