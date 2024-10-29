package dci.j24e01.OurShop.services;

import dci.j24e01.OurShop.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired DBConnectionManager dbConnectionManager;

    @Override
    public List<Category> getCategories() {
        return List.of();
    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        return null;
    }

    @Override
    public boolean deleteCategory(Long id) {
        return false;
    }
}
