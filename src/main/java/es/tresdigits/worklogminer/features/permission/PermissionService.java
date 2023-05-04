package es.tresdigits.worklogminer.features.permission;

import es.tresdigits.worklogminer.common.MapperBuilder;
import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.Pagination;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import es.tresdigits.worklogminer.common.specification.GenericSpecificationsBuilder;
import es.tresdigits.worklogminer.features.role.RoleEntity;
import es.tresdigits.worklogminer.features.rolepermission.IRolepermissionService;
import es.tresdigits.worklogminer.features.rolepermission.RolepermissionDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PermissionService implements IPermissionService {

  private final IRolepermissionService rolepermissionService;
  private final PermissionRepository permissionRepository;
  private final PermissionMapper permissionMapper;

  private final GenericSpecificationsBuilder<PermissionEntity> builder = new GenericSpecificationsBuilder<>();

  @Autowired
  public PermissionService(IRolepermissionService rolepermissionService,
      PermissionRepository permissionRepository) {
    this.rolepermissionService = rolepermissionService;
    this.permissionRepository = permissionRepository;
    this.permissionMapper = MapperBuilder.PERMISSION_MAPPER;
  }

  @Override
  public PaginatedResponse<PermissionDto> list(TableContextDto dto) {
    PaginatedResponse<PermissionDto> paginatedResponse = new PaginatedResponse<>();
    Pagination pagination = dto.getPagination();
    Map<String, String> relatedFields = PermissionDto.getRelatedFields();

    Sort.Direction direction = builder.generateDirection(dto);

    Page<PermissionEntity> a = permissionRepository.findAll(
        builder.getSpecificationFromFilters(genericToEntity(dto.getFilters(), relatedFields)),
        PageRequest.of(pagination.getPage(), pagination.getDelta(),
            Sort.by(direction,
                relatedFields.get(dto.getSort() != null ? dto.getSort().getField() : "code")))
    );

    paginatedResponse.setTotalRows(a.getTotalElements());
    paginatedResponse.setData(permissionMapper.entityListToDto(a.getContent()));

    return paginatedResponse;
  }

  @Override
  public PaginatedResponse<PermissionDto> listByRole(Integer id, TableContextDto dto) {
    PaginatedResponse<PermissionDto> paginatedPermissions = this.list(dto);
    RoleEntity roleEntity = new RoleEntity();
    roleEntity.setRolCode(id);
    List<RolepermissionDto> rolepermissionList = rolepermissionService.listByRole(roleEntity);

    for (PermissionDto permissionDto : paginatedPermissions.getData()) {
      permissionDto.setActive(false);
      for (RolepermissionDto rolepermission : rolepermissionList) {
        if (Objects.equals(permissionDto.getCode(), rolepermission.getCodper().getCode())) {
          permissionDto.setActive(true);
          break;
        }
      }
    }

    return paginatedPermissions;
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
      result.put(relatedFields.get("usecode"), filters.remove("usecode"));
      result.put(relatedFields.get("name"), filters.remove("name"));
      result.put(relatedFields.get("active"), filters.remove("active"));
    }

    return result;
  }

  @Override
  public List<PermissionDto> listActive() {
    return permissionMapper.entityListToDto(permissionRepository.findByPerActiveIsTrue());
  }

  @Override
  public PermissionDto get(Integer id) {
    Optional<PermissionEntity> permission = permissionRepository.findById(id);
    return permission.map(permissionMapper::entityToDto).orElseThrow(
        () -> new DataIntegrityViolationException("No existe un registro con ese código"));
  }

  @Override
  public PermissionDto create(PermissionDto permissionDto) {
    return permissionMapper.entityToDto(
        permissionRepository.save(permissionMapper.dtoToEntity(permissionDto)));
  }

  @Override
  public PermissionDto update(PermissionDto permissionDto) {
    if (permissionRepository.existsById(permissionDto.getCode())) {
      PermissionEntity result = permissionRepository.save(
          permissionMapper.dtoToEntity(permissionDto));
      return permissionMapper.entityToDto(result);
    }
    throw new DataIntegrityViolationException("No existe un registro con ese código");
  }

  @Override
  public PermissionDto manageRolePermissions(PermissionDto permissionDto, Integer rolCode) {
    if (permissionDto != null) {
      if (Boolean.TRUE.equals(permissionDto.getActive())) {
        rolepermissionService.create(rolCode, permissionDto.getCode());
      } else {
        rolepermissionService.delete(rolCode, permissionDto.getCode());
      }
    }
    return permissionDto;
  }

  @Override
  public void delete(Integer id) {
    permissionRepository.deleteById(id);
  }

  @Override
  public List<PermissionEntity> listByRoleName(String roleName) {
    return permissionRepository.findByPerRole(roleName);
  }
}
