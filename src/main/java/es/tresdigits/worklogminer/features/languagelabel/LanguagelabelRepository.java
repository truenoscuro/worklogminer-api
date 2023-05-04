package es.tresdigits.worklogminer.features.languagelabel;

import es.tresdigits.worklogminer.features.label.LabelEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguagelabelRepository extends JpaRepository<LanguagelabelEntity, Integer> {

  List<LanguagelabelEntity> findByLalCodlab(LabelEntity label);

}