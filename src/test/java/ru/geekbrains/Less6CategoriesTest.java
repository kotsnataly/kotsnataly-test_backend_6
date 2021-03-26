package ru.geekbrains;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.geekbrains.java4.lesson6.db.dao.CategoriesMapper;
import ru.geekbrains.java4.lesson6.db.model.Categories;
import ru.geekbrains.java4.lesson6.db.model.CategoriesExample;
import ru.geekbrains.util.DbUtils;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Less6CategoriesTest {
    static Categories categories;
    static CategoriesExample categoriesExample;
    static CategoriesMapper categoriesMapper = DbUtils.getCategoriesMapper();

    @BeforeAll
    static void createTestCategory() {
        categories = new Categories();
        categories.setTitle("Test category");
        categories.setId(10000);
        categoriesMapper.insert(categories);
    }

    @Test
    void getTest() {

    }
    @Test //убедиться что категорий с ID < 100 - количество 8.
    void getCategoriesIdLessThan100() {
        List<Categories> catList = new ArrayList<>();
        categoriesExample = new CategoriesExample();
        categoriesExample.createCriteria().andIdLessThan(100);
        catList = categoriesMapper.selectByExample(categoriesExample);
        assertThat(catList.size() == 8);

    }

    @Test // проверить что имя категории соответствует ID.
    void getCategoriesTitleIdCheck() {
        List<Categories> catList = new ArrayList<>();
        categoriesExample = new CategoriesExample();
        categoriesExample.createCriteria().andIdEqualTo(16);
        catList = categoriesMapper.selectByExample(categoriesExample);
        assertThat(catList.get(0).getTitle().equals("Bug"));

    }

    @Test // проверить что имя категории соответствует ID.
    void getCategories() {
        List<Categories> catList = new ArrayList<>();
        categoriesExample = new CategoriesExample();
        categoriesExample.createCriteria().andIdEqualTo(16);
        catList = categoriesMapper.selectByExample(categoriesExample);
        assertThat(catList.get(0).getTitle().equals("Bug"));

    }

    @Test //убедиться что нет категорий с пустыми названиями
    void getCategoriesCheckTitleNotEmpty() {
        List<Categories> catList = new ArrayList<>();
        categoriesExample = new CategoriesExample();
        categoriesExample.createCriteria().andTitleIsNull();
        catList = categoriesMapper.selectByExample(categoriesExample);
        assertThat(catList.size() == 0);

    }

    @Test //убедиться что нет null ID
    void getCategoriesCheckNullId() {
        List<Categories> catList = new ArrayList<>();
        categoriesExample = new CategoriesExample();
        categoriesExample.createCriteria().andIdIsNull();
        catList = categoriesMapper.selectByExample(categoriesExample);
        assertThat(catList.size() == 0);
    }


    @Test
    void deleteCategory() {
        categoriesExample = new CategoriesExample();
        categoriesExample.createCriteria().andIdEqualTo(10080);
        categoriesMapper.deleteByExample(categoriesExample);
    }

}
