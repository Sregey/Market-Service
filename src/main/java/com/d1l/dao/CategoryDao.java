package com.d1l.dao;

import com.d1l.model.Category;

import java.util.List;

public interface CategoryDao {
    void addOrUpdateCategory(Category category);
    void deleteCategory(int id);
    Category getCategoryById(int id);
    List<Category> getCategoriesList();
}
