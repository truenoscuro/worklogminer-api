package es.tresdigits.worklogminer.features.language;

import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.SelectOption;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import java.util.List;
import java.util.Map;

public interface ILanguageService {

  PaginatedResponse<LanguageDto> list(TableContextDto dto);

  List<SelectOption> listTableEditorSelectOptions();

  LanguageDto findById(Integer id);

  void delete(Integer id);

  LanguageDto create(LanguageDto lang);

  LanguageDto update(LanguageDto lang);

  Map<String, Map<String, String>> getTranslations(String langCode);
}
