package es.tresdigits.worklogminer.features.translations;

import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.Pagination;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import es.tresdigits.worklogminer.common.specification.JoinGenericSpecificationBuilder;
import es.tresdigits.worklogminer.features.label.LabelEntity;
import es.tresdigits.worklogminer.features.label.LabelRepository;
import es.tresdigits.worklogminer.features.language.LanguageRepository;
import es.tresdigits.worklogminer.features.languagelabel.LanguagelabelEntity;
import es.tresdigits.worklogminer.features.languagelabel.LanguagelabelRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TranslationService implements ITranslationService {

  private final LabelRepository labelRepository;
  private final LanguageRepository languageRepository;
  private final LanguagelabelRepository languagelabelRepository;

  private final JoinGenericSpecificationBuilder<LabelEntity, LanguagelabelEntity> builder = new JoinGenericSpecificationBuilder<>();

  @Autowired
  public TranslationService(LabelRepository labelRepository, LanguageRepository languageRepository,
      LanguagelabelRepository languagelabelRepository) {
    this.labelRepository = labelRepository;
    this.languageRepository = languageRepository;
    this.languagelabelRepository = languagelabelRepository;
  }

  @Override
  public PaginatedResponse<Translation> listItems(TableContextDto dto) {

    PaginatedResponse<Translation> paginatedResponse = new PaginatedResponse<>();
    Pagination pagination = dto.getPagination();
    Map<String, String> relatedFields = Translation.getRelatedFields();

    Sort.Direction direction = builder.generateDirection(dto);

    // Eliminamos entries con valores nulos
    dto.getFilters().values().removeIf(val -> val == null || val.equals(""));

    // Generamos los filtros y eliminamos correspondientes a la entidad del dto
    // TENER EN CUENTA que no se eliminan los campos correspondientes a los idiomas ya que son dinámicos y no contemplan en el genericToEntity()
    Map<String, ?> filters = genericToEntity(dto.getFilters(), relatedFields);

    // Generamos la Specification para los filtros de labelGroupName y labelName
    Specification<LabelEntity> specification = builder.getSpecificationFromFilters(filters);

    // Comprobamos si existen filtros para los valores de los idiomas en el dto.
    if (!dto.getFilters().isEmpty()) {
      // Si tenemos filtros de labelName y labelGroupName, se le concatenará el filtro para los valores
      if (specification != null) {
        specification = specification.and(filterLanguageLabels(dto.getFilters()));
      } else {
        specification = filterLanguageLabels(dto.getFilters());
      }
    }

    Page<LabelEntity> a = labelRepository.findAll(
        specification,
        PageRequest.of(pagination.getPage(), pagination.getDelta(),
            Sort.by(direction,
                relatedFields.get(dto.getSort() != null ? dto.getSort().getField() : "code")))
    );

    paginatedResponse.setTotalRows(a.getTotalElements());
    // Mapeamos las entidades para que sean legibles por el front
    paginatedResponse.setData(a.getContent().stream().map(
        label -> {
          Translation translation = new Translation();
          translation.setCode(label.getLabCode());
          translation.setLabelName(label.getLabId());
          translation.setLabelGroupName(label.getLabCodlgr().getLgrId());
          translation.setValues(getLabelLanguages(label));
          return translation;
        }
    ).toList());

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
      result.put(relatedFields.get("labelGroupName"), filters.remove("labelGroupName"));
      result.put(relatedFields.get("labelName"), filters.remove("labelName"));
    }

    return result;
  }

  @Override
  public Translation update(Translation translation) {

    // Filtramos los valores para solo procesar aquellos que sean nuevos LanguageLabel o modificaciones de LanguageLabel existentes
    List<TranslationValue> translationValues = translation.getValues().stream().filter(
        translationValue -> translationValue.getCode() != null || (
            translationValue.getValue() != null && !translationValue.getValue().isEmpty())
    ).toList();

    // Iteraremos sobre los LanguageLabel filtrados previamente
    for (TranslationValue translationValue : translationValues) {
      // Comprobamos si el LanguageLabel existe
      if (translationValue.getCode() != null) {
        // Si existe, combrobamos si tiene valor. Si lo tiene, actualizaremos. Si no tiene valor, lo eliminaremos
        if (translationValue.getValue() != null) {
          // Actualizamos LabelLanguage
          updateLanguageLabel(translationValue);
        } else {
          // Eliminamos LabelLanguage
          languagelabelRepository.deleteById(translationValue.getCode());
        }
      } else {
        // Como no existe el LanguageLabel en la base de datos, lo creamos
        createLanguageLabel(translation.getCode(), translationValue);
      }
    }

    return translation;
  }

  /**
   * Actualiza una LanguageLabel a partir de un TranslationValue
   *
   * @param translationValue
   */
  private void updateLanguageLabel(TranslationValue translationValue) {
    Optional<LanguagelabelEntity> languageLabel = languagelabelRepository.findById(
        translationValue.getCode());
    if (Boolean.TRUE.equals(languageLabel.isPresent())) {
      languageLabel.get().setLalValue(translationValue.getValue());
      languagelabelRepository.save(languageLabel.get());
    }
  }

  /**
   * Crea un LanguageLabel a partir del id de la Label y los campos de un TranslationValue
   *
   * @param labelCode
   * @param translationValue
   */
  private void createLanguageLabel(Integer labelCode, TranslationValue translationValue) {
    LanguagelabelEntity languageLabel = new LanguagelabelEntity();
    languageLabel.setLalValue(translationValue.getValue());
    languageLabel.setLalCodlan(languageRepository.findByLanName(translationValue.getLang()));
    LabelEntity label = new LabelEntity();
    label.setLabCode(labelCode);
    languageLabel.setLalCodlab(label);
    translationValue.setCode(languagelabelRepository.save(languageLabel).getLalCode());
  }

  /**
   * Devuelve los LanguageLabel de una Label en formato de lista de TranslationValue
   *
   * @param label
   * @return
   */
  private List<TranslationValue> getLabelLanguages(LabelEntity label) {
    return languagelabelRepository.findByLalCodlab(label).stream().map(
        languageLabel -> new TranslationValue(languageLabel.getLalCode(),
            languageLabel.getLalCodlan().getLanName(), languageLabel.getLalValue())
    ).toList();
  }


  /**
   * Filtra Labels en base su relación con LanguagelableEntity, que contiene los valores de esa
   * label por cada idioma
   *
   * @param filters Map<String, ?> con el lanName del idioma como key y el valor de la Label como
   *                value
   * @return Specification<LabelEntity> con los filtros aplicados
   */
  private Specification<LabelEntity> filterLanguageLabels(Map<String, ?> filters) {
    return ((root, query, criteriaBuilder) -> {
      Join<LabelEntity, LanguagelabelEntity> join = root.join("languageLabels");
      List<Predicate> operations = new ArrayList<>();
      query.distinct(true);

      for (Entry<String, ?> entry : filters.entrySet()) {
        operations.add(
            criteriaBuilder.and(
                criteriaBuilder.equal(join.get("lalCodlan"),
                    languageRepository.findByLanName(entry.getKey()).getLanCode()),
                (entry.getValue() instanceof String)
                    ? criteriaBuilder.like(join.get("lalValue"), "%" + entry.getValue() + "%")
                    : criteriaBuilder.equal(join.get("lalValue"), entry.getValue())
            )
        );
      }

      return criteriaBuilder.or(operations.toArray(new Predicate[0]));
    });
  }

}
