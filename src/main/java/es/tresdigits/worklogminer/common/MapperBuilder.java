package es.tresdigits.worklogminer.common;

import es.tresdigits.worklogminer.features.configuration.ConfigurationMapper;
import es.tresdigits.worklogminer.features.example.ExampleMapper;
import es.tresdigits.worklogminer.features.label.LabelMapper;
import es.tresdigits.worklogminer.features.labelgroup.LabelGroupMapper;
import es.tresdigits.worklogminer.features.language.LanguageMapper;
import es.tresdigits.worklogminer.features.languagelabel.LanguagelabelMapper;
import es.tresdigits.worklogminer.features.permission.PermissionMapper;
import es.tresdigits.worklogminer.features.role.RoleMapper;
import es.tresdigits.worklogminer.features.rolepermission.RolepermissionMapper;
import es.tresdigits.worklogminer.features.screen.ScreenMenuItemMapper;
import es.tresdigits.worklogminer.features.screen_permission.ScreenPermissionMapper;
import es.tresdigits.worklogminer.features.user.UserMapper;
import org.mapstruct.factory.Mappers;

public final class MapperBuilder {

  private MapperBuilder() {
    throw new IllegalStateException("Utility class");
  }

  public static final ConfigurationMapper CONFIGURATION_MAPPER = Mappers.getMapper(
      ConfigurationMapper.class);
  public static final LabelMapper LABEL_MAPPER = Mappers.getMapper(LabelMapper.class);
  public static final LabelGroupMapper LABEL_GROUP_MAPPER = Mappers.getMapper(
      LabelGroupMapper.class);
  public static final LanguageMapper LANGUAGE_MAPPER = Mappers.getMapper(LanguageMapper.class);
  public static final LanguagelabelMapper LANGUAGELABEL_MAPPER = Mappers.getMapper(
      LanguagelabelMapper.class);
  public static final PermissionMapper PERMISSION_MAPPER = Mappers.getMapper(
      PermissionMapper.class);
  public static final ExampleMapper POSITION_MAPPER = Mappers.getMapper(ExampleMapper.class);
  public static final RoleMapper ROLE_MAPPER = Mappers.getMapper(RoleMapper.class);
  public static final RolepermissionMapper ROLEPERMISSION_MAPPER = Mappers.getMapper(
      RolepermissionMapper.class);
  public static final ScreenMenuItemMapper SCREEN_MENU_ITEM_MAPPER = Mappers.getMapper(
      ScreenMenuItemMapper.class);
  public static final ScreenPermissionMapper SCREEN_PERMISSION_MAPPER = Mappers.getMapper(
      ScreenPermissionMapper.class);
  public static final UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
}
