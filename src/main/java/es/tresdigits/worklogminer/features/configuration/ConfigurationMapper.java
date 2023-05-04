package es.tresdigits.worklogminer.features.configuration;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ConfigurationMapper {

  @Mapping(source = "conCode", target = "code")
  @Mapping(source = "conLabel", target = "label")
  @Mapping(source = "conKey", target = "key")
  @Mapping(source = "conValue", target = "value")
  @Mapping(source = "conValidation", target = "validation")
  @Mapping(source = "conCreuse", target = "creuse")
  @Mapping(source = "conCredat", target = "credat")
  @Mapping(source = "conModuse", target = "moduse")
  @Mapping(source = "conModdat", target = "moddat")
  ConfigurationDto entityToDto(ConfigurationEntity src);

  @InheritInverseConfiguration
  ConfigurationEntity dtoToEntity(ConfigurationDto dest);

  List<ConfigurationDto> entityToDtoList(List<ConfigurationEntity> src);

}
