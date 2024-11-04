package dci.j24e01.OurShop.controllers;

import dci.j24e01.OurShop.models.Category;
import dci.j24e01.OurShop.models.Product;
import dci.j24e01.OurShop.services.CategoryDAO;
import dci.j24e01.OurShop.services.ProductDAO;
import dci.j24e01.OurShop.services.ProductDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    CategoryDAO categoryDAO;
    @Autowired
    private ProductDAOImpl productDAOImpl;
    @Autowired
    ProductDAO productDAO;

    @GetMapping("/products")
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       Model model) {
        List<Product> products = productDAOImpl.getProductsPaginated(page, size);

        int totalPages = productDAOImpl.getTotalPages(size);


        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "products_list";
    }

    @GetMapping("/products/add")
    public String getAdd(Model model) {

        List<Category> categories = categoryDAO.getCategories();
        model.addAttribute("categories", categories);

        return "products_add";
    }

    @PostMapping("/products/add")
    public String postAdd(@ModelAttribute Product product) {
        Product insertedProduct = productDAO.addProduct(product);

        if (insertedProduct != null) {
            return "redirect:/products?success=true";
        } else {
            return "redirect:/products?failure=true";
        }
    }
}