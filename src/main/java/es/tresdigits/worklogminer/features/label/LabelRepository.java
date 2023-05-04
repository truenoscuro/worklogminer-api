package es.tresdigits.worklogminer.features.label;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LabelRepository extends JpaRepository<LabelEntity, Integer>,
    JpaSpecificationExecutor<LabelEntity> {

}