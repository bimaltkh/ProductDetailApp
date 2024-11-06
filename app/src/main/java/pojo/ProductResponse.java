package pojo;

import androidx.annotation.NonNull;

import java.util.List;

public class ProductResponse {
    private int total;  // Total number of products available
    private int skip;   // Number of products already fetched
    private int limit;  // Limit of products per page

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getTotal() {
        return total;
    }

    public int getSkip() {
        return skip;
    }

    public int getLimit() {
        return limit;
    }
    @NonNull
    @Override
    public String toString() {
        return "ProductResponse{" +
                "products=" + products +
                '}';
    }
}
