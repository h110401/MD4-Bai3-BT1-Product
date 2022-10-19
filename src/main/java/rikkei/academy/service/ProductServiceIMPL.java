package rikkei.academy.service;

import rikkei.academy.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceIMPL implements IProductService {

    private static final Map<Integer, Product> products = new HashMap<>();

    static {
        products.put(1, new Product(1, "Hoodie", 200, "Gray", "VN"));
        products.put(2, new Product(2, "T-Shirt", 100, "Gray", "EN"));
        products.put(3, new Product(3, "Hoodie", 150, "Yellow", "VN"));
        products.put(4, new Product(4, "Jacket", 300, "Leather", "EN"));
        products.put(5, new Product(5, "Jogger", 180, "Black", "VN"));
    }


    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }
}
