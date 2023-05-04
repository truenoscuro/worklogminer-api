package es.tresdigits.worklogminer.features.labelgroup;

import es.tresdigits.worklogminer.common.model.SelectOption;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface LabelGroupMapper {

  @Mapping(source = "lgrCode", target = "code")
  @Mapping(source = "lgrId", target = "id")
  @Mapping(source = "lgrCreuse", target = "creuse")
  @Mapping(source = "lgrCredat", target = "credat")
  @Mapping(source = "lgrModuse", target = "moduse")
  @Mapping(source = "lgrModdat", target = "moddat")
  LabelGroupDto entityToDto(LabelGroupEntity labelGroupEntity);

  @InheritInverseConfiguration
  LabelGroupEntity dtoToEntity(LabelGroupDto labelGroupDto);

  List<LabelGroupDto> entityListToDto(List<LabelGroupEntity> labelGroupEntityList);

  @Mapping(source = "lgrId", target = "label")
  @Mapping(source = "lgrCode", target = "value")
  SelectOption entityToSelectOption(LabelGroupEntity src);

  @InheritInverseConfiguration
  List<SelectOption> entityToSelectOptionList(List<LabelGroupEntity> src);
}
