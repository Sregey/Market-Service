package com.d1l.dao;

import com.d1l.dao.util.DAOUtil;
import com.d1l.dao.util.PropertyName;
import com.d1l.model.Category;

import java.util.List;

public class CategoryDao {
    public static void addOrUpdateCategory(Category category) {
        DAOUtil.addOrUpdateEntity(category);
    }

    public static void deleteCategory(int id) {
        DAOUtil.deleteEntity(Category.class, id);
    }

    public static Category getCategoryById(int id) {
        return DAOUtil.getEntityBy(Category.class, PropertyName.ID, id);
    }

    public static List<Category> getCategoriesList() {
        return DAOUtil.getEntityList(Category.class);
    }
}
