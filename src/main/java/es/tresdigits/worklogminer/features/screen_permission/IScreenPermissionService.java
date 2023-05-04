package es.tresdigits.worklogminer.features.screen_permission;

import es.tresdigits.worklogminer.features.screen.ScreenEntity;
import java.util.List;

public interface IScreenPermissionService {

  List<ScreenPermissionDto> list();

  List<ScreenPermissionDto> listByScreen(ScreenEntity screen);

  ScreenPermissionDto get(Integer id);

  ScreenPermissionDto create(ScreenPermissionDto screenPermissionDto);

  ScreenPermissionDto update(ScreenPermissionDto screenPermissionDto);

  void delete(Integer id);
}
