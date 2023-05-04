package es.tresdigits.worklogminer.features.configuration;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, Integer>,
    JpaSpecificationExecutor<ConfigurationEntity> {

  Optional<ConfigurationEntity> getByConKey(String conKey);
}
