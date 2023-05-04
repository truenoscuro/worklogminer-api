package es.tresdigits.worklogminer.features.user;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService implements IUserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  private final GenericSpecificationsBuilder<UserEntity> builder = new GenericSpecificationsBuilder<>();

  @Autowired
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    userMapper = MapperBuilder.USER_MAPPER;
  }

  @Override
  public PaginatedResponse<UserDto> list(TableContextDto dto) {
    PaginatedResponse<UserDto> paginatedResponse = new PaginatedResponse<>();
    Pagination pagination = dto.getPagination();
    Map<String, String> relatedFields = UserDto.getRelatedFields();

    Sort.Direction direction = builder.generateDirection(dto);

    Page<UserEntity> a = userRepository.findAll(
        builder.getSpecificationFromFilters(genericToEntity(dto.getFilters(), relatedFields)),
        PageRequest.of(pagination.getPage(), pagination.getDelta(),
            Sort.by(direction,
                relatedFields.get(dto.getSort() != null ? dto.getSort().getField() : "code")))
    );

    paginatedResponse.setTotalRows(a.getTotalElements());
    paginatedResponse.setData(userMapper.entityToDtoList(a.getContent()));

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
      result.put(relatedFields.get("surname"), filters.remove("surname"));
      result.put(relatedFields.get("email"), filters.remove("email"));
      result.put(relatedFields.get("codLan"), filters.remove("codLan"));
      result.put(relatedFields.get("codRol"), filters.remove("codRol"));
      result.put(relatedFields.get("active"), filters.remove("active"));
    }

    return result;
  }

  @Override
  public UserDto listUserById(Integer id) {
    Optional<UserEntity> user = userRepository.findById(id);

    return user.map(userMapper::entityToDto)
        .orElseThrow(() -> new DataIntegrityViolationException("The id is not valid"));
  }

  @Override
  public UserDto create(UserDto userDto) {
    userRepository.save(userMapper.dtoToEntity(userDto));
    return userDto;
  }

  @Override
  public UserDto update(UserDto userDto) {
    if (userRepository.existsById(userDto.getCode())) {
      return userMapper.entityToDto(userRepository.save(userMapper.dtoToEntity(userDto)));
    } else {
      throw new DataIntegrityViolationException("The id is not valid");
    }
  }

  @Override
  public void delete(Integer id) {
    userRepository.deleteById(id);
  }

  @Override
  public Optional<UserEntity> getByEmail(String email) {
    return userRepository.findByUseEmail(email);
  }

  @Override
  public UserInfo getUserInfo(String useEmail) {
    Optional<UserEntity> currentUser = getByEmail(useEmail);
    if (currentUser.isPresent()) {
      return UserInfo.builder()
          .displayname(currentUser.get().getUseName())
          .email(currentUser.get().getUseEmail())
          .build();
    } else {
      throw new DataIntegrityViolationException("User not found");
    }
  }

}