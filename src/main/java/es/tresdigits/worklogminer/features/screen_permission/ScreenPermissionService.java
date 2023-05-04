package es.tresdigits.worklogminer.features.screen_permission;

import es.tresdigits.worklogminer.common.MapperBuilder;
import es.tresdigits.worklogminer.features.screen.ScreenEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ScreenPermissionService implements IScreenPermissionService {

  private final ScreenPermissionRepository screenPermissionRepository;
  private final ScreenPermissionMapper screenPermissionMapper;

  @Autowired
  public ScreenPermissionService(ScreenPermissionRepository screenPermissionRepository) {
    this.screenPermissionRepository = screenPermissionRepository;
    this.screenPermissionMapper = MapperBuilder.SCREEN_PERMISSION_MAPPER;
  }

  @Override
  public List<ScreenPermissionDto> list() {
    return screenPermissionMapper.entityListToDto(this.screenPermissionRepository.findAll());
  }

  @Override
  public List<ScreenPermissionDto> listByScreen(ScreenEntity screen) {
    return screenPermissionMapper.entityListToDto(
        this.screenPermissionRepository.findAllByScpCodscr(screen));
  }

  @Override
  public ScreenPermissionDto get(Integer id) {
    Optional<ScreenPermissionEntity> screenPermission = this.screenPermissionRepository.findById(
        id);
    return screenPermission.map(screenPermissionMapper::entityToDto).orElseThrow(
        () -> new DataIntegrityViolationException("No existe un registro con ese código"));
  }

  @Override
  public ScreenPermissionDto create(ScreenPermissionDto screenPermissionDto) {
    return screenPermissionMapper.entityToDto(this.screenPermissionRepository.save(
        this.screenPermissionMapper.dtoToEntity(screenPermissionDto)));
  }

  @Override
  public ScreenPermissionDto update(ScreenPermissionDto screenPermissionDto) {
    if (screenPermissionRepository.existsById(screenPermissionDto.getCode())) {
      ScreenPermissionEntity result = screenPermissionRepository.save(
          screenPermissionMapper.dtoToEntity(screenPermissionDto));
      return screenPermissionMapper.entityToDto(result);
    }
    throw new DataIntegrityViolationException("No existe un registro con ese código");
  }

  @Override
  public void delete(Integer id) {
    screenPermissionRepository.deleteById(id);
  }
}
