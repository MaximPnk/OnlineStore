package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import valid.ValidUser;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showForm")
    public String registrationForm(Model model) {

        model.addAttribute("validUser", new ValidUser());

        return "registration-form";
    }

    @PostMapping("/registrationProcess")
    public String registrationProcess(@Valid @ModelAttribute("validUser") ValidUser validUser,
                                      BindingResult bindingResult, Model model) {

        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("registrationError", "Неправильно введены данные");
            return "registration-form";
        }

        String userName = validUser.getUserName();

        User existing = userService.findByUserName(userName);
        if (existing != null){
            model.addAttribute("crmUser", new ValidUser());
            model.addAttribute("registrationError", "Имя пользователя занято");
            return "registration-form";
        }

        userService.save(validUser);

        return "redirect:/login";
    }

}
