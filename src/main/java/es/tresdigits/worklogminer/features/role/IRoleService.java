package es.tresdigits.worklogminer.features.role;

import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.SelectOption;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import java.util.List;
import java.util.Map;

public interface IRoleService {

  PaginatedResponse<RoleDto> list(TableContextDto dto);

  List<SelectOption> listSelectOptions();

  List<SelectOption> listSelectOptionsFiltered(Map<String, Object> filters);

  RoleDto findById(Integer id);

  void delete(Integer id);

  RoleDto create(RoleDto rol);

  RoleDto update(RoleDto rol);


}
