package dci.j24e01.OurShop.services;

import dci.j24e01.OurShop.models.Category;
import dci.j24e01.OurShop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    CategoryDAO categoryDAO;

    private final Connection connection;

    public ProductDAOImpl(DBConnectionManager connectionManager) {
        this.connection = connectionManager.getConnection();
    }

    @Override
    public List<Product> getProducts() {
        String sql = "SELECT * FROM products";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("category_id"),
                        categoryDAO.getCategoryById(resultSet.getLong("category_id")),
                        resultSet.getLong("stock")
                );
                products.add(product);
            }
            return products;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product getProductById(Long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return new Product(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getLong("category_id"),
                    categoryDAO.getCategoryById(resultSet.getLong("category_id")),
                    resultSet.getLong("stock")
            );
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> searchProducts(String searchTerm) {
        return List.of();
    }

    @Override
    public Product addProduct(Product product) {
        String sql = "INSERT INTO products (name, category_id, stock) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, product.name());
            preparedStatement.setLong(2, product.categoryId());
            preparedStatement.setLong(3, product.stock());

            int rowCount = preparedStatement.executeUpdate();
            if (rowCount != 1) {
                return null;
            }

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            Long lastInsertId = resultSet.getLong(1);

            return getProductById(lastInsertId);
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }
}
