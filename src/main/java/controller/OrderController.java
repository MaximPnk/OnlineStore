package controller;

import entity.Product;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CrudService;
import service.OrderService;
import service.UserService;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CrudService crudService;

    @Autowired
    UserService userService;

    @GetMapping("/basket")
    public String basket(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userService.findByUserName(name);
        model.addAttribute("user", user);

        return "basket";
    }

    @GetMapping("/order")
    public String order(@RequestParam("productId") long productId) {

        Product product = crudService.getProduct(productId);

        orderService.addProductInBasket(product);

        return "redirect:/product/list";
    }
}
