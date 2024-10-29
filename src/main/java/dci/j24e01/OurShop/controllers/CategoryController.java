package dci.j24e01.OurShop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    @GetMapping("/categories/add")
    public String add() {
        return "categories_add";
    }
}
