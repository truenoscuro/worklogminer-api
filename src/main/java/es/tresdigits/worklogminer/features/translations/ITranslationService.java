package es.tresdigits.worklogminer.features.translations;

import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.TableContextDto;

public interface ITranslationService {

  PaginatedResponse<Translation> listItems(TableContextDto dto);

  Translation update(Translation translation);

}
