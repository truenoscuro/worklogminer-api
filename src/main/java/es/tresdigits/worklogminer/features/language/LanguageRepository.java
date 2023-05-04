package es.tresdigits.worklogminer.features.language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LanguageRepository extends JpaRepository<LanguageEntity, Integer>,
    JpaSpecificationExecutor<LanguageEntity> {

  LanguageEntity findByLanName(String lanName);

}