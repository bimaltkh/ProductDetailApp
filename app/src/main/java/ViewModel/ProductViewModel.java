package ViewModel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import interfaces.ProductApiService;
import pojo.Product;
import pojo.ProductResponse;
import retroclient.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel {
    private MutableLiveData<List<Product>> productList;
    private int page = 0; // Start from the first page
    private int totalProducts = 0; // Track total number of products
    private boolean isLoading = false; // Prevent loading if already in progress

    public ProductViewModel() {
        productList = new MutableLiveData<>();
        fetchProducts(10, page); // Fetch initial page with a limit of 10 products
    }

    public LiveData<List<Product>> getProductList() {
        return productList;
    }

    public void fetchProducts(int limit, int skip) {
        if (isLoading) return; // Prevent further API calls while data is loading

        isLoading = true;
        ProductApiService apiService = RetrofitClient.getApiService();
        apiService.getProducts(limit, skip).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProductResponse> call, @NonNull Response<ProductResponse> response) {
                isLoading = false;
                if (response.isSuccessful() && response.body() != null) {
                    ProductResponse productResponse = response.body();
                    List<Product> currentProducts = productList.getValue();

                    if (productResponse != null) {
                        totalProducts = productResponse.getTotal(); // Update the total products
                        List<Product> newProducts = productResponse.getProducts();

                        if (skip == 0) {
                            // For the first page, replace the list
                            productList.postValue(newProducts);
                        } else {
                            // For subsequent pages, append the new items to the existing list
                            if (currentProducts != null) {
                                currentProducts.addAll(newProducts);
                                productList.postValue(currentProducts);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductResponse> call, @NonNull Throwable t) {
                isLoading = false;
                Log.e("ProductViewModel", "Error fetching products", t);
            }
        });
    }

    // Load more products on scroll
    public void loadMoreProducts(int limit) {
        if (totalProducts <= productList.getValue().size()) {
            return; // Don't load more if we've already fetched all products
        }
        page++; // Increment the page number
        fetchProducts(limit, page * limit); // Update skip by page number * limit
    }
}
