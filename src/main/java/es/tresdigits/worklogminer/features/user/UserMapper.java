package es.tresdigits.worklogminer.features.user;

import es.tresdigits.worklogminer.features.example.ExampleMapper;
import es.tresdigits.worklogminer.features.language.LanguageMapper;
import es.tresdigits.worklogminer.features.role.RoleMapper;
import java.util.List;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(uses = {RoleMapper.class, ExampleMapper.class, LanguageMapper.class})
public interface UserMapper {

  @Named("regularMapping")
  @Mapping(source = "useCode", target = "code")
  @Mapping(source = "useCodrol.rolCode", target = "codRol")
  @Mapping(source = "useCodlan.lanCode", target = "codLan")
  @Mapping(source = "useName", target = "name")
  @Mapping(source = "useSurname", target = "surname")
  @Mapping(source = "useEmail", target = "email")
  @Mapping(source = "useActive", target = "active")
  @Mapping(source = "useCreuse", target = "creuse")
  @Mapping(source = "useCredat", target = "credat")
  @Mapping(source = "useModuse", target = "moduse")
  @Mapping(source = "useModdat", target = "moddat")
  UserDto entityToDto(UserEntity src);

  @InheritConfiguration(name = "entityToDto")
  @Mapping(source = "useCodrol", target = "roleDto")
  @Mapping(source = "useCodlan", target = "languageDto")
  UserDto entityToDtoWithRelationships(UserEntity src);

  @IterableMapping(qualifiedByName = "regularMapping")
  List<UserDto> entityToDtoList(List<UserEntity> src);

  @InheritInverseConfiguration(name = "entityToDto")
  UserEntity dtoToEntity(UserDto src);
}