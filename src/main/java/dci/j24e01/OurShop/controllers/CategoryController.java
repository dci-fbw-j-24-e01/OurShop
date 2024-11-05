package dci.j24e01.OurShop.controllers;

import dci.j24e01.OurShop.models.Category;
import dci.j24e01.OurShop.services.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryDAO categoryDAO;

    @GetMapping("/categories")
    public String list(
            @RequestParam(required = false) Boolean success,
            @RequestParam(required = false) Boolean failure,
            @RequestParam(required = false) Boolean editedSuccess,
            @RequestParam(required = false) Boolean editedFailure,
            Model model) {

        List<Category> categories = categoryDAO.getCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("success", success);
        model.addAttribute("failure", failure);
        model.addAttribute("editedSuccess", editedSuccess);
        model.addAttribute("editedFailure", editedFailure);

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

    @GetMapping("/categories/edit/{id}")
    public String getEdit(@PathVariable Long id, Model model) {

        Category category = categoryDAO.getCategoryById(id);

        if (category == null) {
            return "redirect:/categories?editedFailure=true";
        }

        model.addAttribute("category", category);
        return "categories_edit";
    }

    @PostMapping("/categories/edit/{id}")
    public String postEdit(@PathVariable Long id, @ModelAttribute Category category) {

        Category updatedCategory = categoryDAO.updateCategory(id, category);

        if (updatedCategory != null) {
            return "redirect:/categories?editedSuccess=true";
        } else {
            return "redirect:/categories?editedFailure=true";
        }
    }
}
