package es.tresdigits.worklogminer.features.labelgroup;

import es.tresdigits.worklogminer.common.MapperBuilder;
import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.Pagination;
import es.tresdigits.worklogminer.common.model.SelectOption;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import es.tresdigits.worklogminer.common.specification.GenericSpecificationsBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LabelGroupService implements ILabelGroupService {

  private final LabelGroupRepository labelGroupRepository;
  private final LabelGroupMapper labelGroupMapper;

  private final GenericSpecificationsBuilder<LabelGroupEntity> builder = new GenericSpecificationsBuilder<>();

  @Autowired
  public LabelGroupService(LabelGroupRepository labelGroupRepository) {
    this.labelGroupRepository = labelGroupRepository;
    this.labelGroupMapper = MapperBuilder.LABEL_GROUP_MAPPER;
  }

  @Override
  public PaginatedResponse<LabelGroupDto> list(TableContextDto dto) {
    PaginatedResponse<LabelGroupDto> paginatedResponse = new PaginatedResponse<>();
    Pagination pagination = dto.getPagination();
    Map<String, String> relatedFields = LabelGroupDto.getRelatedFields();

    Sort.Direction direction = builder.generateDirection(dto);

    Page<LabelGroupEntity> a = labelGroupRepository.findAll(
        builder.getSpecificationFromFilters(genericToEntity(dto.getFilters(), relatedFields)),
        PageRequest.of(pagination.getPage(), pagination.getDelta(),
            Sort.by(direction,
                relatedFields.get(dto.getSort() != null ? dto.getSort().getField() : "code")))
    );

    paginatedResponse.setTotalRows(a.getTotalElements());
    paginatedResponse.setData(labelGroupMapper.entityListToDto(a.getContent()));

    return paginatedResponse;
  }

  @Override
  public List<SelectOption> listSelectOptionsFiltered(Map<String, Object> filters) {
    Map<String, String> relatedFields = LabelGroupDto.getRelatedFields();
    List<LabelGroupEntity> labelGroupEntityList = labelGroupRepository.findAll(
        builder.getSpecificationFromFilters(genericToEntity(filters, relatedFields)));
    return labelGroupMapper.entityToSelectOptionList(labelGroupEntityList);
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
      result.put(relatedFields.get("id"), filters.remove("id"));
    }

    return result;
  }

  @Override
  public LabelGroupDto get(Integer id) {
    Optional<LabelGroupEntity> labelGroup = this.labelGroupRepository.findById(
        id);
    return labelGroup.map(labelGroupMapper::entityToDto).orElseThrow(
        () -> new DataIntegrityViolationException("No existe un registro con ese código"));
  }

  @Override
  public LabelGroupDto create(LabelGroupDto labelGroupDto) {
    return labelGroupMapper.entityToDto(
        this.labelGroupRepository.save(labelGroupMapper.dtoToEntity(labelGroupDto)));
  }

  @Override
  public LabelGroupDto update(LabelGroupDto labelGroupDto) {
    if (labelGroupRepository.existsById(labelGroupDto.getCode())) {
      LabelGroupEntity result = labelGroupRepository.save(
          labelGroupMapper.dtoToEntity(labelGroupDto));
      return labelGroupMapper.entityToDto(result);
    }
    throw new DataIntegrityViolationException("No existe un registro con ese código");
  }

  @Override
  public void delete(Integer id) {
    labelGroupRepository.deleteById(id);
  }
}
