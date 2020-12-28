package com.upgrad.eshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String category;
    private double price;
    private String description;
    private String manufacturer;
    private int availableItems;
    private String imageUrl;

    public static ProductRequest getProductRequest
            (String name, String category, double price, String description,
             String manufacturer, int availableItems, String imageUrl) {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName(name);
        productRequest.setCategory(category);
        productRequest.setPrice(price);
        productRequest.setDescription(description);
        productRequest.setManufacturer(manufacturer);
        productRequest.setAvailableItems(availableItems);
        productRequest.setImageUrl(imageUrl);
        return productRequest;
    }
}
