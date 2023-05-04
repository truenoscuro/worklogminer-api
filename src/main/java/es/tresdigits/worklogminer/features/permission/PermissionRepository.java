package es.tresdigits.worklogminer.features.permission;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer>,
    JpaSpecificationExecutor<PermissionEntity> {

  List<PermissionEntity> findByPerActiveIsTrue();

  @Query("select distinct p from PermissionEntity p join fetch p.perRoles t where t.rolName = ?1")
  List<PermissionEntity> findByPerRole(String roleName);
}
