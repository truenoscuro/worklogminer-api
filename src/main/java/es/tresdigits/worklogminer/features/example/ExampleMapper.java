package es.tresdigits.worklogminer.features.example;


import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ExampleMapper {

  @Mapping(source = "exaCode", target = "code")
  @Mapping(source = "exaName", target = "name")
  @Mapping(source = "exaDescription", target = "description")
  @Mapping(source = "exaDate", target = "date")
  @Mapping(source = "exaSize", target = "size")
  @Mapping(source = "exaColor", target = "color")
  @Mapping(source = "exaCreuse", target = "creuse")
  @Mapping(source = "exaCredat", target = "credat")
  @Mapping(source = "exaModuse", target = "moduse")
  @Mapping(source = "exaModdat", target = "moddat")
  ExampleDto entityToDto(ExampleEntity src);

  @InheritInverseConfiguration
  ExampleEntity dtoToEntity(ExampleDto src);

  List<ExampleDto> entityToDtoList(List<ExampleEntity> src);
}
