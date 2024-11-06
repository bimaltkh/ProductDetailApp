package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_sample.ProductDetailActivity;
import com.example.mvvm_sample.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import pojo.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> products;
    Locale locale = Locale.US; // You can change this to any other locale as needed
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.title.setText(product.getTitle());
        holder.description.setText(currencyFormat.format(product.getPrice()));
        // Set other details as needed
        holder.itemView.setOnClickListener(v -> {
            // Display a toast with the name of the clicked product
            Context context = holder.itemView.getContext();
            Toast.makeText(context, product.getTitle(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("product_id", product.getId()); // Pass the product ID
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    public void updateProductList(List<Product> newProductList) {
        if (newProductList != null) {
            products.addAll(newProductList); // Append the new products
            notifyDataSetChanged(); // Notify the adapter that the data has changed
        }
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
    }
}
