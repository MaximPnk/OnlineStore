package controller;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.CrudServiceImpl;
import valid.ValidProduct;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    CrudServiceImpl crudService;

    @GetMapping("/list")
    public String productList(Model model) {

        List<Product> products = crudService.getProducts();
        model.addAttribute("products", products);

        return "product-list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("product") String product, @RequestParam("type") String type,
                         @RequestParam("brand") String brand, @RequestParam("country") String country, Model model) {

        List<Product> products = crudService.searchProducts(product, type, brand, country);
        model.addAttribute("products", products);

        return "product-list";
    }

    @GetMapping("/formForAddProduct")
    public String formForAddProduct(Model model) {

        ValidProduct validProduct = new ValidProduct();

        model.addAttribute("validProduct", validProduct);

        return "product-form";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("validProduct") ValidProduct validProduct, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return "product-form";
        }
        crudService.saveProduct(validProduct);

        return "redirect:/product/list";
    }

    @GetMapping("/formForUpdateProduct")
    public String formForUpdateProduct(@RequestParam("productId") int productId, Model model) {

        Product product = crudService.getProduct(productId);
        model.addAttribute("product", product);

        return "product-update";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("productId") int id) {

        crudService.deleteProduct(id);

        return "redirect:/product/list";
    }
}
