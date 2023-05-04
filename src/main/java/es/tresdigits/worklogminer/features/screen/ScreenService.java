package es.tresdigits.worklogminer.features.screen;

import es.tresdigits.worklogminer.common.MapperBuilder;
import java.util.ArrayList;
import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ScreenService implements IScreenService {

  private final ScreenRepository screenRepository;
  private final ScreenMenuItemMapper screenMenuItemMapper;

  @Autowired
  public ScreenService(ScreenRepository screenRepository) {
    this.screenRepository = screenRepository;
    this.screenMenuItemMapper = MapperBuilder.SCREEN_MENU_ITEM_MAPPER;
  }

  @Override
  public MenuContainer list() {
    List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) SecurityContextHolder.getContext()
        .getAuthentication().getAuthorities();

    List<String> roleUseCodes = authorities.stream()
        .map(SimpleGrantedAuthority::getAuthority)
        .toList();

    MenuContainer menuContainer = new MenuContainer();

    List<MenuItem> menuItems = new ArrayList<>();
    List<ScreenEntity> screenEntities = screenRepository.findAll(
        Sort.by(Direction.ASC, "scrOrder"));

    for (ScreenEntity screen : screenEntities) {
      if (!screen.getScreenPemissions().stream()
          .filter(screenPermissionEntity -> roleUseCodes.contains(
              screenPermissionEntity.getScpCodper().getPerUsecode()))
          .toList().isEmpty() &&
          (screen.getParentScreen() == null)) {
        menuItems.add(screenMenuItemMapper.entityToDto(screen));
      }
    }

    menuContainer.setMenu(menuItems);
    return menuContainer;
  }

}
