package es.tresdigits.worklogminer.features.language;


import es.tresdigits.worklogminer.common.model.SelectOption;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface LanguageMapper {

  @Mapping(source = "lanCode", target = "code")
  @Mapping(source = "lanName", target = "name")
  @Mapping(source = "lanDescription", target = "description")
  @Mapping(source = "lanOrder", target = "order")
  @Mapping(source = "lanActive", target = "active")
  @Mapping(source = "lanCreuse", target = "creuse")
  @Mapping(source = "lanCredat", target = "credat")
  @Mapping(source = "lanModuse", target = "moduse")
  @Mapping(source = "lanModdat", target = "moddat")
  LanguageDto entityToDto(LanguageEntity src);

  @InheritInverseConfiguration
  LanguageEntity dtoToEntity(LanguageDto src);

  List<LanguageDto> entityToDtoList(List<LanguageEntity> src);

  @Mapping(source = "lanName", target = "label")
  @Mapping(source = "lanCode", target = "value")
  SelectOption entityToSelectOption(LanguageEntity src);

  @InheritInverseConfiguration
  List<SelectOption> entityToSelectOptionList(List<LanguageEntity> src);

}
