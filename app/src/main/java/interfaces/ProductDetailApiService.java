package interfaces;

import pojo.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductDetailApiService {
    @GET("products/{id}")
    Call<Product> getProductDetails(@Path("id") int productId);
}

