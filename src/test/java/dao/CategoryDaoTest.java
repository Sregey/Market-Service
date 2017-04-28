package dao;

import com.d1l.dao.CategoryDao;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryDaoTest {
    private CategoryDao categoryDao;
    private Category category;

    private static final int CATEGORY_ID = 105011;
    private static final String CATEGORY_NAME = "testCategory";

    @Before
    public void setUp() throws Exception {
        categoryDao = DaoFactory.getInstance().getCategoruDao();
        category = new Category();
        category.setId(CATEGORY_ID);
        category.setName(CATEGORY_NAME);
    }

    @Test
    public void deleteCategory() throws Exception {
        categoryDao.deleteCategory(CATEGORY_ID);
        assertNull(categoryDao.getCategoryById(CATEGORY_ID));
    }

    @Test
    public void addOrUpdateCategory() throws Exception {
        categoryDao.addOrUpdateCategory(category);
        Category newCategory = categoryDao.getCategoryById(CATEGORY_ID);
        assertEquals(newCategory, category);
    }

    @Test
    public void getCategoryByID() throws Exception {
        categoryDao.addOrUpdateCategory(category);
        assertNotNull(categoryDao.getCategoryById(CATEGORY_ID));
    }

    @Test
    public void getCategoryList() throws Exception {
        categoryDao.addOrUpdateCategory(category);
        assertNotNull(categoryDao.getCategoriesList());
    }
}