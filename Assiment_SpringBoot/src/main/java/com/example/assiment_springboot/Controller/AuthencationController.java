package com.example.assiment_springboot.Controller;

import com.example.assiment_springboot.Request.AuthenRequest.AccountRequestRegister;
import com.example.assiment_springboot.Request.AuthenRequest.loginRequest;
import com.example.assiment_springboot.Service.AuthencationService;
import com.example.assiment_springboot.Service.CategorySevice;
import com.example.assiment_springboot.Service.ProductService;
import com.example.assiment_springboot.response.CategoryResponeName;
import com.example.assiment_springboot.response.loginResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.regex.Pattern;

@Controller
@RequestMapping("/auth/v1")
public class AuthencationController {

    public static final String REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@" + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
    public static final String PHONE_CHECK = "^(?:\\+84|0)(?:[1-9][0-9]{8})$";
    @Autowired
    private AuthencationService authencationService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategorySevice categorySevice;

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(emailAddress).matches();
    }



    @GetMapping("/landing_page")
    public String landingPage(Model model) {

        model.addAttribute("products", productService.listProduct());
        return "landingPage/HomeShop";
    }

    @GetMapping("/home_shop")
    public String HomeShop(Model model, HttpSession session) {
        loginResponse response = (loginResponse) session.getAttribute("account");

        model.addAttribute("products", productService.listProduct());
        model.addAttribute("category", categorySevice.getAllCategory());
        model.addAttribute("account", response);
        return "landingPage/HomeShop2";
    }

    @GetMapping("/register_form")
    public String registerForm(Model model) {

        AccountRequestRegister requestRegister = new AccountRequestRegister();
        model.addAttribute("account", requestRegister);
        return "authencation/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("account") AccountRequestRegister register, Model model, RedirectAttributes redirectAttributes) {
        if (!register.getPassword().equals(register.getConfirmPassword())) {
            model.addAttribute("messes", "Passwords do not match");
            return "authencation/register";
        }
        if (!patternMatches(register.getEmail(), REGEX_PATTERN)) {
            model.addAttribute("messes", "Email must to match form ...@gmail.com");
            return "authencation/register";
        }

        if (!patternMatches(register.getPhone(), PHONE_CHECK)) {
            model.addAttribute("messes", "phone do not match");
            return "authencation/register";
        }
        if(authencationService.checkPhone(register.getPhone()) == true){
            model.addAttribute("messes", "phone already exists!");
            return "authencation/register";
        }

        if(authencationService.checkMail(register.getEmail()) == true){
            model.addAttribute("messes", "Email already exists!");
            return "authencation/register";
        }

        authencationService.register(register);
        redirectAttributes.addFlashAttribute("successMessage", "Register Successful, login to go home");
        return "redirect:/auth/v1/login_form";

    }

    @GetMapping("/login_form")
    public String loginForm(Model model) {

        loginRequest loginRequest = new loginRequest();
        model.addAttribute("loginRequest", loginRequest);
        return "authencation/login_form";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute("loginRequest") loginRequest loginRequest, HttpSession session) {

        loginResponse response = authencationService.Login(loginRequest);
        if (response != null) {
            session.setAttribute("account", response);
            if ("user".equals(response.getRole())) {
                return "redirect:/auth/v1/home_shop";
            } else if ("admin".equals(response.getRole()) ) {
                return "redirect:/Con/v1/list_products";
            } else {
                model.addAttribute("messes", "Role is false");
                return "authencation/login_form";
            }


        }else {
            model.addAttribute("messes", "Email or password is not correct");
            return "authencation/login_form";
        }
        }




    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("account");
        return "redirect:/auth/v1/login_form";
    }
    }