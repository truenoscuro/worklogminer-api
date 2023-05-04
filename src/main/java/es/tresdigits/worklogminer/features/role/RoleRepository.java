package es.tresdigits.worklogminer.features.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer>,
    JpaSpecificationExecutor<RoleEntity> {

}