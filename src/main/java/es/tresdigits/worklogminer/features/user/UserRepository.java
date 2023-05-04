package es.tresdigits.worklogminer.features.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Integer>,
    JpaSpecificationExecutor<UserEntity> {

  Optional<UserEntity> findByUseEmail(String useEmail);

}