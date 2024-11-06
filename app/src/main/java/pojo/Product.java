package pojo;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    private int id;
    private String title;
    private String description;
    private String category;
    private double price;
    private double discountPercentage;
    private double rating;
    private int stock;
    private List<String> tags;
    private String brand;
    private String sku;
    private double weight;
    private Dimensions dimensions;
    private String warrantyInformation;
    private String shippingInformation;
    private String availabilityStatus;
    private List<Review> reviews;
    private String returnPolicy;
    private int minimumOrderQuantity;
    private Meta meta;
    private List<String> images;
    private String thumbnail;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getRating() {
        return rating;
    }

    public int getStock() {
        return stock;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getBrand() {
        return brand;
    }

    public String getSku() {
        return sku;
    }

    public double getWeight() {
        return weight;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public String getWarrantyInformation() {
        return warrantyInformation;
    }

    public String getShippingInformation() {
        return shippingInformation;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getReturnPolicy() {
        return returnPolicy;
    }

    public int getMinimumOrderQuantity() {
        return minimumOrderQuantity;
    }

    public Meta getMeta() {
        return meta;
    }

    public List<String> getImages() {
        return images;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    // Nested class for dimensions
    public static class Dimensions {
        private double width;
        private double height;
        private double depth;

        // Getters and Setters
    }
    @NonNull
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", discountPercentage=" + discountPercentage +
                ", rating=" + rating +
                ", stock=" + stock +
                ", tags=" + tags +
                ", brand='" + brand + '\'' +
                ", sku='" + sku + '\'' +
                ", weight=" + weight +
                ", dimensions=" + dimensions +
                ", warrantyInformation='" + warrantyInformation + '\'' +
                ", shippingInformation='" + shippingInformation + '\'' +
                ", availabilityStatus='" + availabilityStatus + '\'' +
                ", reviews=" + reviews +
                ", returnPolicy='" + returnPolicy + '\'' +
                ", minimumOrderQuantity=" + minimumOrderQuantity +
                ", meta=" + meta +
                ", images=" + images +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }

    // Nested class for reviews
    public static class Review implements Serializable {
        private int rating;
        private String comment;
        private String date;
        private String reviewerName;
        private String reviewerEmail;

        // Getters and Setters
    }

    // Nested class for meta information
    public static class Meta implements Serializable {
        private String createdAt;
        private String updatedAt;
        private String barcode;
        private String qrCode;

        // Getters and Setters
    }

    // Getters and Setters for Product fields
}
