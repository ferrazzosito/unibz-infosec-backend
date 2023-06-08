package it.unibz.infosec.examproject.product.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UnsafeProductRepositoryImpl implements IUnsafeProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    private static final Logger logger = Logger.getLogger("UnsafeProductRepositoryImpl");

    @Override
    @NonNull
    public List<Product> findByName(@NonNull String query) {
        final List<Product> results = new ArrayList<>();
        try (final PreparedStatement stmt = dataSource.getConnection().prepareStatement(
                "SELECT * FROM product WHERE name LIKE ?"
        )) {
            stmt.setString(1, "%" + query + "%");

            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("cost"),
                        rs.getLong("vendor_id")
                ));
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error during db query", e);
        }
        return results;
    }

    @NonNull
    public List<Product> findByNameAndVendorId(@NonNull String query, @NonNull Long vendorId) {
        final List<Product> results = new ArrayList<>();
        try (final PreparedStatement stmt = dataSource.getConnection().prepareStatement(
                "SELECT * FROM product WHERE name LIKE ? AND vendor_id = ?"
        )) {
            stmt.setString(1, "%" + query + "%");
            stmt.setLong(2, vendorId);

            final ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("cost"),
                        rs.getLong("vendor_id")
                ));
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error during db query", e);
        }
        return results;
    }
}
