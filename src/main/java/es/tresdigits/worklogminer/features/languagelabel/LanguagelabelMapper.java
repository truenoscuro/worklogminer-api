package es.tresdigits.worklogminer.features.languagelabel;

import es.tresdigits.worklogminer.features.label.LabelMapper;
import es.tresdigits.worklogminer.features.language.LanguageMapper;
import java.util.List;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(uses = {LabelMapper.class, LanguageMapper.class})
public interface LanguagelabelMapper {

  @Named("regularMapping")
  @Mapping(source = "lalCode", target = "code")
  @Mapping(source = "lalCodlan.lanCode", target = "codlan")
  @Mapping(source = "lalCodlab.labCode", target = "codlab")
  @Mapping(source = "lalValue", target = "value")
  @Mapping(source = "lalCreuse", target = "creuse")
  @Mapping(source = "lalCredat", target = "credat")
  @Mapping(source = "lalModuse", target = "moduse")
  @Mapping(source = "lalModdat", target = "moddat")
  LanguagelabelDto entityToDto(LanguagelabelEntity langLab);

  @Named("relationshipMapping")
  @InheritConfiguration(name = "entityToDto")
  @Mapping(source = "lalCodlan", target = "languageDto")
  @Mapping(source = "lalCodlab", target = "labelDto")
  LanguagelabelDto entityToDtoWithRelationships(LanguagelabelEntity src);

  @InheritInverseConfiguration(name = "entityToDto")
  LanguagelabelEntity dtoToEntity(LanguagelabelDto langLab);

  @IterableMapping(qualifiedByName = "regularMapping")
  List<LanguagelabelDto> entityToDtoList(List<LanguagelabelEntity> src);

  @IterableMapping(qualifiedByName = "relationshipMapping")
  List<LanguagelabelDto> entityToDtoListWithRelationships(List<LanguagelabelEntity> src);
}
