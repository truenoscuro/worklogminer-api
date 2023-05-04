package es.tresdigits.worklogminer.common.specification;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class JoinGenericSpecificationBuilder<T, K> extends GenericSpecificationsBuilder<T> {

  public Specification<T> joinSpecification(Specification<T> specification,
      String relatedPropName, String fieldToJoin, Object value) {
    return specification != null
        ? specification.and((root, query, criteriaBuilder) -> {
      Join<T, K> join = root.join(relatedPropName);
      return criteriaBuilder.equal(join.get(fieldToJoin), value);
    })
        : (root, query, criteriaBuilder) ->
        {
          Join<T, K> join = root.join(relatedPropName);
          return criteriaBuilder.equal(join.get(fieldToJoin), value);
        };
  }
}
