package controller;

import dao.StartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

    @Autowired
    private StartDao startDAO;


    @GetMapping("/insertDefaultValues")
    public String hello() {

        startDAO.start();

        return "redirect:/product/list";
    }
}
