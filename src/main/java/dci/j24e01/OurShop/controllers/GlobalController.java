package dci.j24e01.OurShop.controllers;

import dci.j24e01.OurShop.models.NavLink;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalController {

    @ModelAttribute("navLinks")
    public List<NavLink> getNavLinks(){
        return List.of(
                new NavLink("Home", "/"),
                new NavLink("Categories", "/categories"),
                new NavLink("Products", "/products")
        );
    }

}
