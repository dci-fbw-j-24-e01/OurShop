package dci.j24e01.OurShop.controllers;

import dci.j24e01.OurShop.models.NavLink;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class AppController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @ModelAttribute("navLinks")
    public List<NavLink> getNavLinks(){
        return List.of(
                new NavLink("Home", "/"),
                new NavLink("Categories", "/categories"),
                new NavLink("Products", "/products")
        );
    }
}
