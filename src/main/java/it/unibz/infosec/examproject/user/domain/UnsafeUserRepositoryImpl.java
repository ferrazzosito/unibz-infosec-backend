package it.unibz.infosec.examproject.user.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.query.Query;
import org.hibernate.query.TupleTransformer;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.logging.Logger;

public class UnsafeUserRepositoryImpl implements IUnsafeUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @NonNull
    public Optional<UserEntity> findByEmail(String email) {
        return Optional.ofNullable((UserEntity) entityManager.createNativeQuery(
                String.format("SELECT * FROM users where email = '%s'", email), UserEntity.class
        ).unwrap(Query.class).getSingleResultOrNull());
    }

    @Override
    @NonNull
    public Optional<UserEntity> findById(Long id) {
        Logger.getLogger("UnsafeUserRepositoryImpl", "id = " + id.toString());
        return Optional.ofNullable((UserEntity) entityManager.createNativeQuery(
                String.format("SELECT * FROM users where id = '%s'", id.toString())
        ).unwrap(Query.class).getSingleResultOrNull());
    }
}
