package it.unibz.infosec.examproject.product.application;


import it.unibz.infosec.examproject.product.domain.ManageProducts;
import it.unibz.infosec.examproject.product.domain.Product;
import it.unibz.infosec.examproject.product.domain.SearchProducts;
import it.unibz.infosec.examproject.user.domain.Role;
import it.unibz.infosec.examproject.user.domain.UserEntity;
import it.unibz.infosec.examproject.user.domain.UserRepository;
import it.unibz.infosec.examproject.util.RESTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/products")
public class ProductController {

    private final UserRepository userRepository;
    private final ManageProducts manageProducts;
    private final SearchProducts searchProducts;

    @Autowired
    public ProductController(ManageProducts manageProducts, SearchProducts searchProducts, UserRepository userRepository) {
        this.manageProducts = manageProducts;
        this.searchProducts = searchProducts;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long id) {
        return manageProducts.readProduct(id);
    }

    @PostMapping("/create")
    public Product createNewProduct(@RequestBody CreateProductDTO dto) {
        final UserEntity loggedUser = RESTUtils.getLoggedUser(userRepository);
        if (loggedUser.getRole() != Role.VENDOR) {
            throw new IllegalArgumentException("Wrong user type for this operation");
        }
        return manageProducts.createProduct(dto.getName(), dto.getCost(), loggedUser.getId());
    }

    @PostMapping("/update/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody UpdateProductDTO dto) {
        return manageProducts.updateProduct(
                id,
                RESTUtils.getLoggedUser(userRepository).getId(),
                dto.getName(),
                dto.getCost()
        );
    }

    @GetMapping("/delete/{id}")
    public Product deleteProduct(@PathVariable("id") Long id)  {
        return manageProducts.deleteProduct(id);
    }

    @GetMapping("/getAll")
    public List<Product> findAll(){
        return searchProducts.findAll();
    }

    @GetMapping("/mine")
    public List<Product> getAllForVendor() {
        final UserEntity loggedUser = RESTUtils.getLoggedUser(userRepository);
        if (loggedUser.getRole() != Role.VENDOR) {
            throw new IllegalArgumentException("Wrong user type for this operation");
        }
        return manageProducts.getByVendor(loggedUser.getId());
    }
}