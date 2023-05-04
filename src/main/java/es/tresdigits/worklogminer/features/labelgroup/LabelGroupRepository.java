package es.tresdigits.worklogminer.features.labelgroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelGroupRepository extends JpaRepository<LabelGroupEntity, Integer>,
    JpaSpecificationExecutor<LabelGroupEntity> {

}
