package es.tresdigits.worklogminer.features.role;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RoleService implements IRoleService {

  private final RoleRepository roleRepository;
  private final RoleMapper roleMapper;

  private final GenericSpecificationsBuilder<RoleEntity> builder = new GenericSpecificationsBuilder<>();

  @Autowired
  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
    roleMapper = MapperBuilder.ROLE_MAPPER;
  }

  @Override
  public PaginatedResponse<RoleDto> list(TableContextDto dto) {
    PaginatedResponse<RoleDto> paginatedResponse = new PaginatedResponse<>();
    Pagination pagination = dto.getPagination();
    Map<String, String> relatedFields = RoleDto.getRelatedFields();

    Sort.Direction direction = builder.generateDirection(dto);

    Page<RoleEntity> a = roleRepository.findAll(
        builder.getSpecificationFromFilters(genericToEntity(dto.getFilters(), relatedFields)),
        PageRequest.of(pagination.getPage(), pagination.getDelta(),
            Sort.by(direction,
                relatedFields.get(dto.getSort() != null ? dto.getSort().getField() : "code")))
    );

    paginatedResponse.setTotalRows(a.getTotalElements());
    paginatedResponse.setData(roleMapper.entityToDtoList(a.getContent()));

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
      result.put(relatedFields.get("active"), filters.remove("active"));
    }

    return result;
  }

  @Override
  public List<SelectOption> listSelectOptions() {
    List<RoleEntity> roleList = roleRepository.findAll();
    return roleMapper.entityToSelectOptionList(roleList);
  }

  @Override
  public List<SelectOption> listSelectOptionsFiltered(Map<String, Object> filters) {
    Map<String, String> relatedFields = RoleDto.getRelatedFields();
    List<RoleEntity> roleEntityList = roleRepository.findAll(
        builder.getSpecificationFromFilters(genericToEntity(filters, relatedFields)));
    return roleMapper.entityToSelectOptionList(roleEntityList);
  }

  @Override
  public RoleDto findById(Integer id) {
    Optional<RoleEntity> rol = roleRepository.findById(id);
    return rol.map(roleMapper::entityToDto).orElse(null);
  }

  @Override
  public void delete(Integer id) {
    roleRepository.deleteById(id);
  }

  @Override
  public RoleDto create(RoleDto rol) {
    roleRepository.save(roleMapper.dtoToEntity(rol));
    return rol;
  }

  @Override
  public RoleDto update(RoleDto rol) {
    if (roleRepository.existsById(rol.getCode())) {
      roleRepository.save(roleMapper.dtoToEntity(rol));
      return rol;
    } else {
      return null;
    }
  }
}
