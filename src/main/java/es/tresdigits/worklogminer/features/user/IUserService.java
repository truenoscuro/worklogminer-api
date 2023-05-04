package es.tresdigits.worklogminer.features.user;

import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import java.util.Optional;

public interface IUserService {

  PaginatedResponse<UserDto> list(TableContextDto dto);

  UserDto listUserById(Integer id);

  UserDto create(UserDto userDto);

  UserDto update(UserDto userDto);

  void delete(Integer id);

  Optional<UserEntity> getByEmail(String email);

  UserInfo getUserInfo(String useEmail);
}