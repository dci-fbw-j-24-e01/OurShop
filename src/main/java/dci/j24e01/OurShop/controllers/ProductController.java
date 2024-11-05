package dci.j24e01.OurShop.controllers;

import dci.j24e01.OurShop.models.Category;
import dci.j24e01.OurShop.models.Product;
import dci.j24e01.OurShop.services.CategoryDAO;
import dci.j24e01.OurShop.services.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    ProductDAO productDAO;

    @GetMapping("/products")
    public String list( @RequestParam(required = false) Boolean success,
                        @RequestParam(required = false) Boolean failure,
                        @RequestParam(required = false) Boolean deletedSuccess,
                        @RequestParam(required = false) Boolean deletedFailure,
                        Model model
    ) {
        List<Product> products = productDAO.getProducts();
        model.addAttribute("products", products);
        model.addAttribute("success", success);
        model.addAttribute("failure", failure);
        model.addAttribute("deletedSuccess", deletedSuccess);
        model.addAttribute("deletedFailure", deletedFailure);


        return "products_list";
    }

    @GetMapping("/products/add")
    public String getAdd(Model model) {

        List<Category> categories = categoryDAO.getCategories();
        model.addAttribute("categories", categories);

        return "products_add";
    }

    @PostMapping("/products/add")
    public String postAdd(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        Product insertedProduct = productDAO.addProduct(product);

        if (insertedProduct != null) {
            redirectAttributes.addFlashAttribute("success", true);
        } else {
            redirectAttributes.addFlashAttribute("failure", true);
        }
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {

        List<Category> categories = categoryDAO.getCategories();
        model.addAttribute("categories", categories);
        Product product = productDAO.getProductById(id);
        model.addAttribute("product", product);
        return "products_add";
    }

    @PostMapping("/products/editingDetails/{id}")
    public String updateProductDetails(@RequestParam Long id, @ModelAttribute Product product,
                                       RedirectAttributes redirectAttributes) {

        Product updatedProduct = productDAO.updateProduct(id, product);

        if (updatedProduct != null) {
            redirectAttributes.addFlashAttribute("editSuccess", true);
        } else {
            redirectAttributes.addFlashAttribute("editFailure", true);
        }

        return "redirect:/products";

    }

    @GetMapping("/products/delete/{id}")
    public String delete(@PathVariable Long id) {
        boolean deletedProduct = productDAO.deleteProduct(id);

        if (deletedProduct) {
            return "redirect:/products?deletedSuccess=true";
        } else {
            return "redirect:/products?deletedFailure=true";
        }
    }
}
