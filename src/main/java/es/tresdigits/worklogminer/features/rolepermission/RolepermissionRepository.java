package es.tresdigits.worklogminer.features.rolepermission;

import es.tresdigits.worklogminer.features.permission.PermissionEntity;
import es.tresdigits.worklogminer.features.role.RoleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolepermissionRepository extends
    JpaRepository<RolepermissionEntity, Integer> {

  List<RolepermissionEntity> findAllByRpeCodrol(RoleEntity id);

  RolepermissionEntity findFirstByRpeCodrolAndRpeCodper(RoleEntity rpeCodrol,
      PermissionEntity rpeCodper);
}