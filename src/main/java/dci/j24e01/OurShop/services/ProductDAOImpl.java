package dci.j24e01.OurShop.services;

import dci.j24e01.OurShop.models.Category;
import dci.j24e01.OurShop.models.Product;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Category getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> searchProducts(String searchTerm) {
        return List.of();
    }

    @Override
    public Category addProduct(Product product) {
        return null;
    }

    @Override
    public Category updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }
}
