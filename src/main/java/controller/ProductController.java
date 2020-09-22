package controller;

import dao.ProductDAO;
import entity.Brand;
import entity.Country;
import entity.Product;
import entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductDAO productDAO;

    @GetMapping("/list")
    public String productList(Model model) {

        List<Product> products = productDAO.getProducts();
        model.addAttribute("products", products);

        return "product-list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("searchValue") String value, Model model) {

        List<Product> products = productDAO.searchProducts(value);
        model.addAttribute("products", products);

        return "product-list";
    }

    @GetMapping("/formForAddProduct")
    public String formForAddProduct(Model model) {

        Product product = new Product();
        Brand brand = new Brand();
        Country country = new Country();
        Type type = new Type();
        model.addAttribute("product", product);
        model.addAttribute("brand", brand);
        model.addAttribute("country", country);
        model.addAttribute("type", type);

        return "product-form";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return "product-form";
        }

        productDAO.saveProduct(product);

        return "redirect:/product/list";
    }

    @GetMapping("/formForUpdateProduct")
    public String updateProduct() {
        return "redirect:/product/list";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("productId") int id) {

        productDAO.deleteProduct(id);

        return "redirect:/product/list";
    }
}
