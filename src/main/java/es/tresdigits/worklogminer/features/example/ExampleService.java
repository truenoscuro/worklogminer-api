package es.tresdigits.worklogminer.features.example;

import es.tresdigits.worklogminer.common.MapperBuilder;
import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.Pagination;
import es.tresdigits.worklogminer.common.model.SelectOption;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import es.tresdigits.worklogminer.common.specification.GenericSpecificationsBuilder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ExampleService implements IExampleService {

  private final ExampleRepository exampleRepository;
  private final ExampleMapper exampleMapper;

  private final GenericSpecificationsBuilder<ExampleEntity> builder = new GenericSpecificationsBuilder<>();

  @Autowired
  public ExampleService(ExampleRepository exampleRepository) {
    this.exampleRepository = exampleRepository;
    exampleMapper = MapperBuilder.POSITION_MAPPER;
  }

  @Override
  public PaginatedResponse<ExampleDto> list(TableContextDto dto) {
    PaginatedResponse<ExampleDto> paginatedResponse = new PaginatedResponse<>();
    Pagination pagination = dto.getPagination();
    Map<String, String> relatedFields = ExampleDto.getRelatedFields();

    Sort.Direction direction = builder.generateDirection(dto);

    LocalDateTime dateStart = dto.getFilters().get("dateStart") != null
        ? LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) dto.getFilters().get("dateStart")),
        ZoneOffset.UTC)
        : null;

    LocalDateTime dateEnd = dto.getFilters().get("dateEnd") != null
        ? LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) dto.getFilters().get("dateEnd")),
        ZoneOffset.UTC)
        : null;

    Specification<ExampleEntity> betweenDates = dateStart == null || dateEnd == null
        ? null
        : Specification.where((root, query, criteriaBuilder) ->
            criteriaBuilder.between(root.get("exaDate"), dateStart, dateEnd));

    Page<ExampleEntity> a = exampleRepository.findAll(
        Specification.allOf(betweenDates,
            builder.getSpecificationFromFilters(genericToEntity(dto.getFilters(), relatedFields))),
        PageRequest.of(pagination.getPage(), pagination.getDelta(),
            Sort.by(direction,
                relatedFields.get(dto.getSort() != null ? dto.getSort().getField() : "code")))
    );

    paginatedResponse.setTotalRows(a.getTotalElements());
    paginatedResponse.setData(exampleMapper.entityToDtoList(a.getContent()));

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
      result.put(relatedFields.get("name"), filters.remove("name"));
      result.put(relatedFields.get("description"), filters.remove("description"));
      result.put(relatedFields.get("date"), filters.remove("date"));
      result.put(relatedFields.get("size"), filters.remove("size"));
      result.put(relatedFields.get("color"), filters.remove("color"));
    }

    return result;
  }

  @Override
  public ExampleDto findById(Integer id) {
    Optional<ExampleEntity> pos = exampleRepository.findById(id);
    return pos.map(exampleMapper::entityToDto)
        .orElseThrow(() -> new DataIntegrityViolationException("The id is not valid"));
  }

  @Override
  public void delete(Integer id) {
    exampleRepository.deleteById(id);
  }

  @Override
  public ExampleDto create(ExampleDto rq) {
    exampleRepository.save(exampleMapper.dtoToEntity(rq));
    return rq;
  }

  @Override
  public ExampleDto update(ExampleDto rq) {
    if (exampleRepository.existsById(rq.getCode())) {
      exampleRepository.save(exampleMapper.dtoToEntity(rq));
      return rq;
    } else {
      throw new DataIntegrityViolationException("The id is not valid");
    }
  }

  @Override
  public List<SelectOption> getDropdownColor() {
    List<SelectOption> options = new ArrayList<>();

    options.add(new SelectOption("EXAMPLE.SIZE_SMALL", 1));
    options.add(new SelectOption("EXAMPLE.SIZE_LARGE", 2));

    return options;
  }

  @Override
  public List<SelectOption> getDropdownSize() {
    List<SelectOption> options = new ArrayList<>();

    options.add(new SelectOption("EXAMPLE.COLOR_BLACK", 1));
    options.add(new SelectOption("EXAMPLE.COLOR_RED", 2));

    return options;
  }
}
