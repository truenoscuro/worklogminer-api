package es.tresdigits.worklogminer.features.screen_permission;

import es.tresdigits.worklogminer.features.screen.ScreenEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenPermissionRepository extends JpaRepository<ScreenPermissionEntity, Integer> {

  List<ScreenPermissionEntity> findAllByScpCodscr(ScreenEntity scpCodscr);
}
