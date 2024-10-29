package dci.j24e01.OurShop.services;

import dci.j24e01.OurShop.models.Category;
import dci.j24e01.OurShop.models.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getProducts();

    Category getProductById(Long id);

    List<Product> searchProducts(String searchTerm);


    Category addProduct(Product product);

    Category updateProduct(Long id, Product product);

    boolean deleteProduct(Long id);
}
