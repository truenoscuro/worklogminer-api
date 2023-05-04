package es.tresdigits.worklogminer.features.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExampleRepository extends JpaRepository<ExampleEntity, Integer>,
    JpaSpecificationExecutor<ExampleEntity> {

}