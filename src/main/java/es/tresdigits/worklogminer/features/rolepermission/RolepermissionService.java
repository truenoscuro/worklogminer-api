package es.tresdigits.worklogminer.features.rolepermission;

import es.tresdigits.worklogminer.common.MapperBuilder;
import es.tresdigits.worklogminer.features.permission.PermissionEntity;
import es.tresdigits.worklogminer.features.role.RoleEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RolepermissionService implements IRolepermissionService {

  private final RolepermissionRepository rolepermissionRepository;
  private final RolepermissionMapper rolepermissionMapper;

  @Autowired
  public RolepermissionService(RolepermissionRepository rolepermissionRepository) {
    this.rolepermissionRepository = rolepermissionRepository;
    rolepermissionMapper = MapperBuilder.ROLEPERMISSION_MAPPER;
  }

  @Override
  public List<RolepermissionDto> list(Integer id) {
    RoleEntity roleEntity = new RoleEntity();
    roleEntity.setRolCode(id);
    List<RolepermissionEntity> list = rolepermissionRepository.findAllByRpeCodrol(roleEntity);
    return rolepermissionMapper.entityToDtoList(list);
  }

  @Override
  public List<RolepermissionDto> listByRole(RoleEntity roleEntity) {
    return rolepermissionMapper.entityToDtoList(
        rolepermissionRepository.findAllByRpeCodrol(roleEntity));
  }

  @Override
  public RolepermissionDto create(RolepermissionDto rp) {
    rolepermissionRepository.save(rolepermissionMapper.dtoToEntity(rp));
    return rp;
  }

  @Override
  public RolepermissionDto create(Integer rolCode, Integer perCode) {
    return rolepermissionMapper.entityToDto(
        rolepermissionRepository.save(setRelationships(rolCode, perCode)));
  }

  @Override
  public void delete(Integer rolCode, Integer perCode) {
    RolepermissionEntity rolepermission = setRelationships(rolCode, perCode);
    rolepermissionRepository.delete(
        rolepermissionRepository.findFirstByRpeCodrolAndRpeCodper(rolepermission.getRpeCodrol(),
            rolepermission.getRpeCodper()));
  }

  private RolepermissionEntity setRelationships(Integer rolCode, Integer perCode) {
    RolepermissionEntity rolepermissionEntity = new RolepermissionEntity();
    PermissionEntity permissionEntity = new PermissionEntity();
    permissionEntity.setPerCode(perCode);
    RoleEntity roleEntity = new RoleEntity();
    roleEntity.setRolCode(rolCode);
    rolepermissionEntity.setRpeCodper(permissionEntity);
    rolepermissionEntity.setRpeCodrol(roleEntity);
    return rolepermissionEntity;
  }
}
