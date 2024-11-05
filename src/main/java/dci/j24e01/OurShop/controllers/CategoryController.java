package dci.j24e01.OurShop.controllers;

import dci.j24e01.OurShop.models.Category;
import dci.j24e01.OurShop.services.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryDAO categoryDAO;

    @GetMapping("/categories")
    public String list(
            @RequestParam(required = false) Boolean success,
            @RequestParam(required = false) Boolean failure,
            Model model) {

        List<Category> categories = categoryDAO.getCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("success", success);
        model.addAttribute("failure", failure);

        return "categories_list";
    }

    @GetMapping("/categories/add")
    public String getAdd() {

        System.out.println(categoryDAO.getCategories());

        return "categories_add";
    }

    @PostMapping("/categories/add")
    public String postAdd(@ModelAttribute Category category) {

        Category insertedCategory = categoryDAO.addCategory(category);

        if (insertedCategory != null) {
            return "redirect:/categories?success=true";
        } else {
            return "redirect:/categories?failure=true";
        }
    }


    @GetMapping("/categories/delete")
    public String deleteCategory(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        if (categoryDAO.deleteCategory(id)) {
            redirectAttributes.addAttribute("categoryDeleted", true);
        } else {
            redirectAttributes.addAttribute("deletionFailed", true);
        }
        return "redirect:/categories";
    }
}

