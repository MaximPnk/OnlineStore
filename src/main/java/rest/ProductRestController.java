package rest;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CrudService;
import valid.ValidProduct;

import java.util.List;

@RestController
@RequestMapping("/rest/")
public class ProductRestController {

    @Autowired
    CrudService crudService;

    @GetMapping("products/")
    public List<Product> getProducts() {

        List<Product> products = crudService.getProducts();

        return products;
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable long id) {

        Product product = crudService.getProduct(id);

        if (product == null) {
            throw new ProductNotFoundException("Продукт с id = " + id + " не найден");
        }

        return product;
    }

    /*
    JSON для post, put
    {
            "name": "a",
            "typeName": "b",
            "brandName": "c",
            "brandSale": 50,
            "countryName": "asd",
            "price": 123,
            "amount": 321
    }
    */

    @PostMapping("products/")
    public ValidProduct addProduct(@RequestBody ValidProduct product) {

        crudService.saveValidProduct(product);

        return product;
    }

    @PutMapping("products/")
    public ValidProduct updProduct(@RequestBody ValidProduct product) {

        crudService.saveValidProduct(product);

        return product;
    }

    @DeleteMapping(value = "products/{id}")
    public String delProduct(@PathVariable long id) {

        Product product = crudService.getProduct(id);
        if (product == null) {
            throw new ProductNotFoundException("Продукт с id = " + id + " не найден");
        }
        crudService.deleteProduct(id);

        return "Deleted";
    }
}
