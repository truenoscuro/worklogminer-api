package es.tresdigits.worklogminer.features.rolepermission;

import es.tresdigits.worklogminer.features.role.RoleEntity;
import java.util.List;

public interface IRolepermissionService {

  List<RolepermissionDto> list(Integer id);

  List<RolepermissionDto> listByRole(RoleEntity roleEntity);

  RolepermissionDto create(RolepermissionDto rp);

  RolepermissionDto create(Integer rolCode, Integer perCode);

  void delete(Integer rolCode, Integer perCode);
}
