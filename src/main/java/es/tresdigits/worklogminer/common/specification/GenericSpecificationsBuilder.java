package es.tresdigits.worklogminer.common.specification;

import static org.springframework.data.jpa.domain.Specification.where;

import es.tresdigits.worklogminer.common.model.Direction;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class GenericSpecificationsBuilder<T> {

  public Specification<T> getSpecificationFromFilters(Map<String, ?> filters) {
    Specification<T> specification = null;

    int aux = 0;
    for (Entry<String, ?> entry : filters.entrySet()) {
      if (entry.getValue() != null) {
        if (aux > 0) {
          specification = specification.and(createSpecification(entry.getKey(), entry.getValue()));
        } else {
          specification = where(createSpecification(entry.getKey(), entry.getValue()));
        }
        aux++;
      }
    }
    return specification;
  }

  public Sort.Direction generateDirection(TableContextDto dto) {
    return dto.getSort() != null && Direction.find(dto.getSort().getDirection()).isPresent()
        ? Direction.find(dto.getSort().getDirection()).get().getDirection()
        : Sort.Direction.ASC;
  }

  private Specification<T> createSpecification(String field, Object value) {
    if (value instanceof String) {
      return (root, query, criteriaBuilder) ->
          criteriaBuilder.like(root.get(field),
              "%" + value + "%");
    } else {
      return (root, query, criteriaBuilder) ->
          criteriaBuilder.equal(root.get(field), value);
    }
  }
}
