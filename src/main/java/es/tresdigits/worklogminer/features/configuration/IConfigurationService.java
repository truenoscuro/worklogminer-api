package es.tresdigits.worklogminer.features.configuration;

import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.TableContextDto;


public interface IConfigurationService {

  PaginatedResponse<ConfigurationDto> list(TableContextDto dto);

  ConfigurationDto get(Integer id);

  ConfigurationDto update(ConfigurationDto configurationDto);

  ConfigurationEntity getByConKey(String conKey);
}
