package interfaces;

import pojo.ProductResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface ProductApiService {
    @GET("products")
    Call<ProductResponse> getProducts(
            @Query("limit") int limit,
            @Query("skip") int skip
    );
}
