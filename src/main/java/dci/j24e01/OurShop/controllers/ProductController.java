package dci.j24e01.OurShop.controllers;

import dci.j24e01.OurShop.models.Category;
import dci.j24e01.OurShop.models.Product;
import dci.j24e01.OurShop.services.CategoryDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private CategoryDAO categoryDAO;

    @GetMapping("/products")
    public String list() {


        return "products_list.html";
    }


    @GetMapping("/products/add")
    public String getAdd(Model model) {
        List<Category> categories = categoryDAO.getCategories();
        model.addAttribute("categories", categories);


        return "products_add";
    }

    @PostMapping("/products/add")
    public String postAdd(@ModelAttribute Product product) {




            return "redirect:/products";


    }



}
