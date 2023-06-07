package it.unibz.infosec.examproject.product.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import javax.sql.DataSource;
import java.util.List;

@SuppressWarnings("unchecked")
public class UnsafeProductRepositoryImpl implements IUnsafeProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @NonNull
    public List<Product> findByName(@NonNull String query) {
        return entityManager.createNativeQuery(
                String.format("SELECT * FROM product WHERE name LIKE '%%%s%%'", query), Product.class
        ).unwrap(Query.class).getResultList();
    }

    @NonNull
    public List<Product> findByNameAndVendorId(@NonNull String query, @NonNull Long vendorId) {
        return entityManager.createNativeQuery(
                String.format("SELECT * FROM product WHERE name LIKE '%%%s%%' AND " +
                        "vendor_id = '%s'", query, vendorId), Product.class
        ).unwrap(Query.class).getResultList();
    }
}
