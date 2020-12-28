package com.upgrad.eshop.utils;

import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.exceptions.APIException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ProductComparator {
    public static Comparator<Product> getComparatorByProperty (String property) throws APIException {
        Map<String, Comparator<Product>> comparatorMap = new HashMap<>();
        comparatorMap.put("productId", Comparator.comparing(Product::getProductId));
        comparatorMap.put("name", Comparator.comparing(Product::getName));
        comparatorMap.put("category", Comparator.comparing(Product::getCategory));
        comparatorMap.put("price", Comparator.comparing(Product::getPrice));
        comparatorMap.put("description", Comparator.comparing(Product::getDescription));
        comparatorMap.put("manufacturer", Comparator.comparing(Product::getManufacturer));
        comparatorMap.put("availableItems", Comparator.comparing(Product::getAvailableItems));
        comparatorMap.put("imageUrl", Comparator.comparing(Product::getImageUrl));
        comparatorMap.put("created", Comparator.comparing(Product::getCreated));
        comparatorMap.put("updated", Comparator.comparing(Product::getUpdated));
        if (!comparatorMap.containsKey(property)) {
            throw new APIException("Property " + property + " doesn't exist for Product");
        }
        return comparatorMap.get(property);
    }
}
