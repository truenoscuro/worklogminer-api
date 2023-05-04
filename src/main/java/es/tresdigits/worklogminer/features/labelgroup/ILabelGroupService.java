package es.tresdigits.worklogminer.features.labelgroup;

import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.SelectOption;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import java.util.List;
import java.util.Map;

public interface ILabelGroupService {

  PaginatedResponse<LabelGroupDto> list(TableContextDto dto);

  List<SelectOption> listSelectOptionsFiltered(Map<String, Object> filters);

  LabelGroupDto get(Integer id);

  LabelGroupDto create(LabelGroupDto labelGroupDto);

  LabelGroupDto update(LabelGroupDto labelGroupDto);

  void delete(Integer id);

}
