package controller;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.CrudService;
import valid.ValidProduct;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    CrudService crudService;

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
}
