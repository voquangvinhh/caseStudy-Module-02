package com.example.casetest.service.category;

import java.util.List;
import java.util.Optional;

public interface GeneralService<T> {
    List<T> getAllCategory();
    void updateCategory(T t);
    void removeCategoryById(int id);
    Optional<T> getCategoryById(int id);
}
