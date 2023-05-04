package es.tresdigits.worklogminer.features.screen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<ScreenEntity, Integer> {

}
