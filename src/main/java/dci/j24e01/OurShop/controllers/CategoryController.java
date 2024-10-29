package dci.j24e01.OurShop.controllers;

import dci.j24e01.OurShop.models.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    @GetMapping("/categories/add")
    public String getAdd() {
        return "categories_add";
    }

    @PostMapping("/categories/add")
    public String postAdd(@ModelAttribute Category category) {
        System.out.println(category);
        return "redirect:/categories/add";
    }
}
