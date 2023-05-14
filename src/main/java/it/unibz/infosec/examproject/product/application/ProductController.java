package it.unibz.infosec.examproject.product.application;


import it.unibz.infosec.examproject.product.domain.ManageProducts;
import it.unibz.infosec.examproject.product.domain.Product;
import it.unibz.infosec.examproject.product.domain.SearchProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/products")
public class ProductController {

    private final ManageProducts manageProducts;
    private final SearchProducts searchProducts;

    @Autowired
    public ProductController (ManageProducts manageProducts, SearchProducts searchProducts) {
        this.manageProducts = manageProducts;
        this.searchProducts = searchProducts;
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long id) {
        return manageProducts.readProduct(id);
    }

    @PostMapping("/create")
    public Product createNewProduct(@RequestBody CreateProductDTO dto) {
        return manageProducts.createProduct(dto.getName(), dto.getCost(), dto.getVendorId());
    }

    @PostMapping("/update/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody UpdateProductDTO dto){
        return manageProducts.updateProduct(id, dto.getName(), dto.getCost());
    }

    @DeleteMapping("/delete/{id}")
    public Product deleteProduct(@PathVariable("id") Long id)  {
        return manageProducts.deleteProduct(id);
    }

    @GetMapping("/getAll")
    public List<Product> findAll(){
        return searchProducts.findAll();
    }
}