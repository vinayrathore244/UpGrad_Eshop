package com.upgrad.eshop.dtos;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class SearchRequest {
    private String name;
    private String category;
    private Integer pageNo = 0;
    private Integer pageSize = 10;
    private String sortBy = "";
    private Sort.Direction direction = Sort.Direction.DESC;

    public SearchRequest() {
        if (sortBy.isBlank()) {
            this.sortBy = "productId";
        }
    }
}
