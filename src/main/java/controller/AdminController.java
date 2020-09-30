package controller;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.CrudService;
import valid.ValidProduct;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CrudService crudService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
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
        crudService.saveValidProduct(validProduct);

        return "redirect:/product/list";
    }

    @GetMapping("/formForUpdateProduct")
    public String formForUpdateProduct(@RequestParam("productId") long productId, Model model) {

        Product product = crudService.getProduct(productId);
        model.addAttribute("product", product);

        return "product-update";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("product") Product product) {

        ValidProduct validProduct = new ValidProduct(product.getName(), product.getType().getName(), product.getBrand().getName(),
                product.getBrand().getSale(), product.getBrand().getCountry().getName(), product.getPrice(), product.getAmount());

        crudService.saveValidProduct(validProduct);

        return "redirect:/product/list";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("productId") long id) {

        crudService.deleteProduct(id);

        return "redirect:/product/list";
    }
}
