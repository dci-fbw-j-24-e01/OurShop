package dci.j24e01.OurShop.services;

import dci.j24e01.OurShop.models.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getCategories();

    Category getCategoryById(Long id);

    Category addCategory(Category category);

    Category updateCategory(Long id, Category category);

    boolean deleteCategory(Long id);
}
