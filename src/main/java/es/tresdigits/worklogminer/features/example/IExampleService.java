package es.tresdigits.worklogminer.features.example;

import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.SelectOption;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import java.util.List;

public interface IExampleService {

  PaginatedResponse<ExampleDto> list(TableContextDto dto);

  ExampleDto findById(Integer id);

  void delete(Integer id);

  ExampleDto create(ExampleDto pos);

  ExampleDto update(ExampleDto pos);

  List<SelectOption> getDropdownColor();

  List<SelectOption> getDropdownSize();

}
