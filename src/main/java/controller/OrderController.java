package controller;

import entity.Order;
import entity.Product;
import entity.ProductOrder;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CrudService;
import service.OrderService;
import service.UserService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CrudService crudService;

    @Autowired
    UserService userService;

    @GetMapping("/basket")
    public String basket(Model model) {

        List<ProductOrder> currentProducts = orderService.showCurrent();
        model.addAttribute("currentProducts", currentProducts);

        BigDecimal sum = currentProducts.stream().map(ProductOrder::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        model.addAttribute("sum", sum);

        orderService.showCompleted();

        return "basket";
    }

    @GetMapping("/history")
    public String history(Model model) {

        List<Order> orders = orderService.showCompleted();
        model.addAttribute("orders", orders);

        return "history";
    }

    @GetMapping("/order")
    public String order(@RequestParam("productId") long productId) {

        Product product = crudService.getProduct(productId);
        orderService.addProductInBasket(product);

        return "redirect:/product/list";
    }

    @GetMapping("/buy")
    public String buy() {

        orderService.buyProducts();

        return "redirect:/product/list";
    }

    @GetMapping("/delete")
    public String deleteFromBasket(@RequestParam("productOrderId") long productOrderId) {

        orderService.deleteProductOrder(productOrderId);

        return "redirect:/order/basket";
    }
}
