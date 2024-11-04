package dci.j24e01.OurShop.services;

import dci.j24e01.OurShop.models.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getProducts();

    List<Product> getProductsPaginated(int page, int size);

    int getTotalPages(int size);
    Product getProductById(Long id);

    List<Product> searchProducts(String searchTerm);

    Product addProduct(Product product);

    Product updateProduct(Long id, Product product);

    boolean deleteProduct(Long id);
}
