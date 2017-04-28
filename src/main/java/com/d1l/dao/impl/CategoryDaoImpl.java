package com.d1l.dao.impl;

import com.d1l.dao.CategoryDao;
import com.d1l.dao.util.DAOUtil;
import com.d1l.dao.util.PropertyName;
import com.d1l.model.Category;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    public void addOrUpdateCategory(Category category) {
        DAOUtil.addOrUpdateEntity(category);
    }

    public void deleteCategory(int id) {
        DAOUtil.deleteEntity(Category.class, id);
    }

    public Category getCategoryById(int id) {
        return DAOUtil.getEntityBy(Category.class, PropertyName.ID, id);
    }

    public List<Category> getCategoriesList() {
        return DAOUtil.getEntityList(Category.class);
    }
}
