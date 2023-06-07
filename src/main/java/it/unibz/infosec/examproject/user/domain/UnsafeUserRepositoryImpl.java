package it.unibz.infosec.examproject.user.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UnsafeUserRepositoryImpl implements IUnsafeUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    private static final Logger logger = Logger.getLogger("UnsafeUserRepositoryImpl");

    @Override
    @NonNull
    public Optional<UserEntity> findByEmail(String email) {
        final Optional<UserEntity> user;
        try (final PreparedStatement stmt = dataSource.getConnection().prepareStatement(
                "SELECT * FROM users WHERE email = ?"
        )) {
            stmt.setString(1, email);

            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new UserEntity(
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("salt"),
                        new BigInteger(rs.getString("private_key")),
                        new BigInteger(rs.getString("public_key")),
                        new BigInteger(rs.getString("n_key")),
                        rs.getInt("balance"),
                        rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error during db query", e);
        }
        return Optional.empty();
    }

    @Override
    @NonNull
    public Optional<UserEntity> findById(Long id) {
        final Optional<UserEntity> user;
        try (final PreparedStatement stmt = dataSource.getConnection().prepareStatement(
                "SELECT * FROM users WHERE id = ?"
        )) {
            stmt.setLong(1, id);

            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new UserEntity(
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("salt"),
                        new BigInteger(rs.getString("private_key")),
                        new BigInteger(rs.getString("public_key")),
                        new BigInteger(rs.getString("n_key")),
                        rs.getInt("balance"),
                        rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error during db query", e);
        }
        return Optional.empty();
    }
}
