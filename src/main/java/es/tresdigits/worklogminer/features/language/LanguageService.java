package es.tresdigits.worklogminer.features.language;

import es.tresdigits.worklogminer.common.MapperBuilder;
import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.Pagination;
import es.tresdigits.worklogminer.common.model.SelectOption;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import es.tresdigits.worklogminer.common.specification.GenericSpecificationsBuilder;
import es.tresdigits.worklogminer.features.languagelabel.ILanguagelabelService;
import es.tresdigits.worklogminer.features.languagelabel.LanguagelabelDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LanguageService implements ILanguageService {

  private final LanguageRepository languageRepository;
  private final LanguageMapper languageMapper;
  private final ILanguagelabelService languagelabelService;

  private final GenericSpecificationsBuilder<LanguageEntity> builder = new GenericSpecificationsBuilder<>();

  @Autowired
  public LanguageService(LanguageRepository languageRepository,
      ILanguagelabelService languagelabelService) {
    this.languageRepository = languageRepository;
    this.languagelabelService = languagelabelService;
    this.languageMapper = MapperBuilder.LANGUAGE_MAPPER;
  }

  @Override
  public PaginatedResponse<LanguageDto> list(TableContextDto dto) {
    PaginatedResponse<LanguageDto> paginatedResponse = new PaginatedResponse<>();
    Pagination pagination = dto.getPagination();
    Map<String, String> relatedFields = LanguageDto.getRelatedFields();

    Sort.Direction direction = builder.generateDirection(dto);

    Page<LanguageEntity> a = languageRepository.findAll(
        builder.getSpecificationFromFilters(genericToEntity(dto.getFilters(), relatedFields)),
        PageRequest.of(pagination.getPage(), pagination.getDelta(),
            Sort.by(direction,
                relatedFields.get(dto.getSort() != null ? dto.getSort().getField() : "code")))
    );

    paginatedResponse.setTotalRows(a.getTotalElements());
    paginatedResponse.setData(languageMapper.entityToDtoList(a.getContent()));

    return paginatedResponse;
  }

  @Override
  public List<SelectOption> listTableEditorSelectOptions() {
    List<LanguageEntity> languageList = languageRepository.findAll();

    return languageMapper.entityToSelectOptionList(languageList);
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
      result.put(relatedFields.get("description"), filters.remove("description"));
      result.put(relatedFields.get("order"), filters.remove("order"));
      result.put(relatedFields.get("active"), filters.remove("active"));
    }

    return result;
  }

  @Override
  public LanguageDto findById(Integer id) {
    Optional<LanguageEntity> pos = languageRepository.findById(id);
    return pos.map(languageMapper::entityToDto)
        .orElseThrow(() -> new DataIntegrityViolationException("The id is not valid"));
  }

  @Override
  public void delete(Integer id) {
    languageRepository.deleteById(id);
  }

  @Override
  public LanguageDto create(LanguageDto lang) {
    languageRepository.save(languageMapper.dtoToEntity(lang));
    return lang;
  }

  @Override
  public LanguageDto update(LanguageDto lang) {
    if (languageRepository.existsById(lang.getCode())) {
      languageRepository.save(languageMapper.dtoToEntity(lang));
      return lang;
    } else {
      throw new DataIntegrityViolationException("The id is not valid");
    }
  }

  /**
   * Metodo que construye un map Map<String, Map<String, String>> en funcion a los registros de labels de la DB.
   *
   * @return
   */
  @Override
  public Map<String, Map<String, String>> getTranslations(String langCode) {
    //Se instancia el map general y se llaman a todas las languagelabel.
    Map<String, Map<String, String>> translations = new HashMap<>();

    List<LanguagelabelDto> languagelabelDtos = languagelabelService.listWithRelationships();

    List<LanguageDto> languageDtos = languageMapper.entityToDtoList(
        languageRepository.findAll());

    Optional<LanguageDto> languageDto = languageDtos.stream()
        .filter(languageDto1 -> langCode.equals(languageDto1.getName()))
        .findFirst();

    //Se iteran los grupos de label.
    for (LanguagelabelDto group : languagelabelDtos) {
      //Se crea un map para las traducciones
      Map<String, String> groupTranslation = new HashMap<>();
      //Se iteran las labels
      for (LanguagelabelDto languageLabel : languagelabelDtos) {
        //Si el codigo del grupo coincide con el cdoigo del grupo de las labels se añaden el valor de language label y el codigo de label como key
        if (group.getLabelDto().getLabelGroupDto().getCode()
            .equals(languageLabel.getLabelDto().getLabelGroupDto().getCode())
            && languageLabel.getLanguageDto()
            .getName().equals(languageDto.isPresent() ? languageDto.get().getName() : "es")) {
          groupTranslation.put(languageLabel.getLabelDto().getId(), languageLabel.getValue());
        }
        if (group.getLabelDto().getLabelGroupDto().getCode().equals(27)) {
          generateAvailableLanguages(languageDtos, groupTranslation);
        }
      }
      //Se añade el map a traducciones con la key el grupo y las traducciones dentro
      translations.put(group.getLabelDto().getLabelGroupDto().getId(), groupTranslation);
    }

    return translations;
  }

  /**
   * Construye el diccionario con los idiomas disponibles en la aplicación
   */
  private void generateAvailableLanguages(List<LanguageDto> languageDtos, Map<String, String> groupTranslation) {
    for (LanguageDto lang : languageDtos) {
      groupTranslation.put(lang.getName(), lang.getDescription());
    }
  }
}
