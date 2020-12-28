package com.upgrad.eshop.utils;

import org.springframework.stereotype.Component;

@Component
public class FieldsValidator {
    public boolean isNotNullAndEmpty(String field) {
        return field != null && !field.isEmpty();
    }

    public boolean isNotNullAndEmpty(Double field) {
        return field != null;
    }

    public boolean isNotNullAndEmpty(Integer field) {
        return field != null;
    }
}
