package es.tresdigits.worklogminer.features.label;

import es.tresdigits.worklogminer.features.labelgroup.LabelGroupMapper;
import java.util.List;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(uses = {LabelGroupMapper.class})
public interface LabelMapper {

    @Named("regularMapping")
    @Mapping(source = "labCode", target = "code")
    @Mapping(source = "labCodlgr.lgrCode", target = "codlgr")
    @Mapping(source = "labId", target = "id")
    @Mapping(source = "labCreuse", target = "creuse")
    @Mapping(source = "labCredat", target = "credat")
    @Mapping(source = "labModuse", target = "moduse")
    @Mapping(source = "labModdat", target = "moddat")
    LabelDto entityToDto(LabelEntity label);

    @InheritConfiguration(name = "entityToDto")
    @Mapping(source = "labCodlgr", target = "labelGroupDto")
    LabelDto entityToDtoWithRelationships(LabelEntity label);

    @InheritInverseConfiguration(name = "entityToDto")
    LabelEntity dtoToEntity(LabelDto label);

    @IterableMapping(qualifiedByName = "regularMapping")
    List<LabelDto> entityToDtoList(List<LabelEntity> label);


}
