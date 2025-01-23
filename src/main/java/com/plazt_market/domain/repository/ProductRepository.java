package com.plazt_market.domain.repository;

import com.plazt_market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int  CategoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int ProductId);
    Product save(Product product);
    void delete(int productId);

}
