package es.tresdigits.worklogminer.features.configuration;

import es.tresdigits.worklogminer.common.MapperBuilder;
import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.Pagination;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import es.tresdigits.worklogminer.common.specification.GenericSpecificationsBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService implements IConfigurationService {

  private final ConfigurationRepository configurationRepository;
  private final ConfigurationMapper configurationMapper;

  private final GenericSpecificationsBuilder<ConfigurationEntity> builder = new GenericSpecificationsBuilder<>();

  private static final String ERROR_MSG = "No existe un registro con ese código";

  @Autowired
  public ConfigurationService(ConfigurationRepository configurationRepository) {
    this.configurationRepository = configurationRepository;
    this.configurationMapper = MapperBuilder.CONFIGURATION_MAPPER;
  }

  @Override
  public PaginatedResponse<ConfigurationDto> list(TableContextDto dto) {
    PaginatedResponse<ConfigurationDto> paginatedResponse = new PaginatedResponse<>();
    Pagination pagination = dto.getPagination();
    Map<String, String> relatedFields = ConfigurationDto.getRelatedFields();

    Sort.Direction direction = builder.generateDirection(dto);

    Page<ConfigurationEntity> a = configurationRepository.findAll(
        builder.getSpecificationFromFilters(genericToEntity(dto.getFilters(), relatedFields)),
        PageRequest.of(pagination.getPage(), pagination.getDelta(),
            Sort.by(direction,
                relatedFields.get(dto.getSort() != null ? dto.getSort().getField() : "code")))
    );

    paginatedResponse.setTotalRows(a.getTotalElements());
    paginatedResponse.setData(configurationMapper.entityToDtoList(a.getContent()));

    return paginatedResponse;
  }

  /**
   * Método que devuelve un Map con los nombres de los campos de la entidad como clave y el
   * contenido de los filtros como valor. Se utiliza para generar una Specification
   *
   * @param filters
   * @param relatedFields
   * @return
   */
  private Map<String, ?> genericToEntity(Map<String, ?> filters,
      Map<String, String> relatedFields) {
    Map<String, Object> result = new HashMap<>();

    if (filters != null && !filters.isEmpty()) {
      result.put(relatedFields.get("code"), filters.remove("code"));
      result.put(relatedFields.get("creuse"), filters.remove("creuse"));
      result.put(relatedFields.get("credat"), filters.remove("credat"));
      result.put(relatedFields.get("moduse"), filters.remove("moduse"));
      result.put(relatedFields.get("moddat"), filters.remove("moddat"));
      result.put(relatedFields.get("label"), filters.remove("label"));
      result.put(relatedFields.get("key"), filters.remove("key"));
      result.put(relatedFields.get("value"), filters.remove("value"));
      result.put(relatedFields.get("validation"), filters.remove("validation"));
    }

    return result;
  }

  @Override
  public ConfigurationDto get(Integer id) {
    Optional<ConfigurationEntity> configuration = configurationRepository.findById(id);
    return configuration.map(configurationMapper::entityToDto).orElseThrow(
        () -> new DataIntegrityViolationException(ERROR_MSG));
  }

  @Override
  public ConfigurationDto update(ConfigurationDto configurationDto) {
    if (configurationRepository.existsById(configurationDto.getCode())) {
      ConfigurationEntity result = configurationRepository.save(
          configurationMapper.dtoToEntity(configurationDto));
      return configurationMapper.entityToDto(result);
    }
    throw new DataIntegrityViolationException(ERROR_MSG);
  }

  @Override
  public ConfigurationEntity getByConKey(String conKey) {
    Optional<ConfigurationEntity> configuration = configurationRepository.getByConKey(conKey);
    return configuration.orElseThrow(
        () -> new DataIntegrityViolationException(ERROR_MSG));
  }
}
