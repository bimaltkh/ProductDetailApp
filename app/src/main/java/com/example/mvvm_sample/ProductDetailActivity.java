package com.example.mvvm_sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.Locale;

import interfaces.ProductDetailApiService;
import pojo.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailActivity extends AppCompatActivity {

    TextView productTitle, productDescription, productPrice , productRating ,productCategory ,productStock , productBrand ,productWarranty , productReturnPolicy;
    ImageView productImage;
    Product product;
    Locale locale = Locale.US; // You can change this to any other locale as needed
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
    private static final String BASE_URL = "https://dummyjson.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Initialize UI components
        productTitle = findViewById(R.id.productTitle);
        productDescription = findViewById(R.id.productDescription);
        productPrice = findViewById(R.id.productPrice);
        productImage = findViewById(R.id.product_image);
        productRating =findViewById(R.id.productRating);
        productCategory=findViewById(R.id.productCategory);
        productStock=findViewById(R.id.productStock);
        productWarranty=findViewById(R.id.productWarranty);
        productBrand=findViewById(R.id.productBrand);
        productReturnPolicy=findViewById(R.id.productReturnPolicy);

        // Get the product ID passed via the Intent
        int productId = getIntent().getIntExtra("product_id", -1);

        if (productId != -1) {
            // Fetch the product details from the API using Retrofit
            fetchProductDetails(productId);
        }
    }

    private void fetchProductDetails(int productId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductDetailApiService apiService = retrofit.create(ProductDetailApiService.class);
        apiService.getProductDetails(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    product = response.body();
                    displayProductDetails();
                } else {
                    Toast.makeText(ProductDetailActivity.this, "Error fetching product details", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void displayProductDetails() {
        if (product != null) {
            productTitle.setText(product.getTitle());
            productDescription.setText(product.getDescription());
            productPrice.setText("Price: " +currencyFormat.format(product.getPrice()));
            productRating.setText("Rating: " +String.valueOf(product.getRating()));
            productCategory.setText("Category: " +product.getCategory());
            productStock.setText("Stock Available: " +String.valueOf(product.getStock()));
            productBrand.setText("Brand: " +product.getBrand());
            productWarranty.setText("Warranty: " +product.getWarrantyInformation());
            productReturnPolicy.setText("Return Policy: " +product.getReturnPolicy());

            // You can use Glide or Picasso to load the product image
            Glide.with(this)
                    .load(product.getImages().get(0)) // Assuming the first image in the list
                    .into(productImage);
        }
    }
}
