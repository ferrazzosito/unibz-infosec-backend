package it.unibz.infosec.examproject.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @NonNull
    Optional<UserEntity> findById(@NonNull Long id);
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
}
