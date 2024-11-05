package dci.j24e01.OurShop.services;

import dci.j24e01.OurShop.models.Category;
import dci.j24e01.OurShop.models.Product;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDAOImpl implements CategoryDAO {

    private final Connection connection;

    public CategoryDAOImpl(DBConnectionManager connectionManager) {
        this.connection = connectionManager.getConnection();
    }

    @Override
    public List<Category> getCategories() {
        String sql = "SELECT * FROM categories";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);

            List<Category> categories = new ArrayList<>();
            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("slug")
                );
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Category> getCategoriesPaginated(int page, int size) {
        List<Category> categories = getCategories();
        if (categories == null) {
            return List.of();
        }

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, categories.size());

        if (fromIndex > categories.size()) {
            return List.of();
        }

        return categories.subList(fromIndex, toIndex);
    }

    @Override
    public int getTotalPages(int size) {
        List<Category> allProducts = getCategories();
        if (allProducts == null || allProducts.isEmpty()) {
            return 0;
        }
        return (int) Math.ceil((double) allProducts.size() / size);
    }

    @Override
    public Category getCategoryById(Long id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return new Category(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("slug")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category addCategory(Category category) {
        String sql = "INSERT INTO categories (name, slug) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, category.name());
            preparedStatement.setString(2, category.slug());

            int rowCount = preparedStatement.executeUpdate();
            if (rowCount != 1) {
                return null;
            }

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            Long lastInsertId = resultSet.getLong(1);

            return getCategoryById(lastInsertId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        return null;
    }

    @Override
    public boolean deleteCategory(Long id) {
        String sql = "DELETE FROM categories WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}